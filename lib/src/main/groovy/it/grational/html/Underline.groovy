package it.grational.html

import static java.lang.Character.*

class Underline extends GeneralTagConverter { // {{{
	static final private int lowLineCode = 818

	static final private List<Byte> combiningTypes = [
		COMBINING_SPACING_MARK,
		ENCLOSING_MARK,
		NON_SPACING_MARK
	]

	private final TagConverter origin

	Underline(TagConverter origin = new NoConversion()) {
		this.origin = origin
	}

	@Override
	protected TagConverter origin() { this.origin }

	@Override
	protected List<String> tagNames() { [ 'u', 'ins' ] }

	@Override
	String convert(String input) {
		State state = new Start()
		StringBuilder sb = new StringBuilder()
		input.codePoints().each { int code ->
			state = state.transition(code, getType(code) as Byte)
			state.process(code, sb)
		}
		state.last(sb)
		return sb.toString()
	}

	interface State {
		State transition(int code, Byte type)
		void process(int code, StringBuilder sb)
		void last(StringBuilder sb)
	}

	abstract class GeneralState implements State {
		@Override
		State transition(int code, Byte type) {
			return ( code == lowLineCode )
				? lowLineNext()
				: ( type in combiningTypes )
				? combiningNext()
				: nonCombiningNext()
		}

		@Override
		void process(int code, StringBuilder sb) {}

		@Override
		void last(StringBuilder sb) {}

		abstract State lowLineNext()
		abstract State combiningNext()
		abstract State nonCombiningNext()
	}

	class Start extends GeneralState {
		State lowLineNext() { new UnbookLowLine() }
		State combiningNext() { new UnbookLowLine() }
		State nonCombiningNext() { new BookLowLine() }
	}

	class PrintLowLine extends GeneralState {
		State lowLineNext() { new UnbookLowLine() }
		State combiningNext() { new BookLowLine() }
		State nonCombiningNext() { this }

		@Override
		void process(int code, StringBuilder sb) {
			sb.appendCodePoint(lowLineCode)
			sb.appendCodePoint(code)
		}

		@Override
		void last(StringBuilder sb) {
			sb.appendCodePoint(lowLineCode)
		}
	}

	class UnbookLowLine extends GeneralState {
		State lowLineNext() { this }
		State combiningNext() { this }
		State nonCombiningNext() { new BookLowLine() }

		@Override
		void process(int code, StringBuilder sb) {
			sb.appendCodePoint(code)
		}
	}

	class BookLowLine extends GeneralState {
		State lowLineNext() { new UnbookLowLine() }
		State combiningNext() { this }
		State nonCombiningNext() { new PrintLowLine() }

		@Override
		void process(int code, StringBuilder sb) {
			sb.appendCodePoint(code)
		}

		@Override
		void last(StringBuilder sb) {
			sb.appendCodePoint(lowLineCode)
		}
	}

} // }}}
