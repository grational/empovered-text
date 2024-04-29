package it.grational.html

import static java.lang.Character.*

abstract class AppendCombining extends GeneralTagConverter { // {{{
	static final private List<Byte> combiningTypes = [
		COMBINING_SPACING_MARK,
		ENCLOSING_MARK,
		NON_SPACING_MARK
	]

	abstract protected int combiningCode()

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
			return ( code == combiningCode() )
				? thisCombiningNext()
				: ( type in combiningTypes )
				? otherCombiningNext()
				: nonCombiningNext()
		}

		@Override
		void process(int code, StringBuilder sb) {}

		@Override
		void last(StringBuilder sb) {}

		abstract State thisCombiningNext()
		abstract State otherCombiningNext()
		abstract State nonCombiningNext()
	}

	class Start extends GeneralState {
		State thisCombiningNext() { new Unbook() }
		State otherCombiningNext() { new Unbook() }
		State nonCombiningNext() { new Book() }
	}

	class Print extends GeneralState {
		State thisCombiningNext() { new Unbook() }
		State otherCombiningNext() { new Book() }
		State nonCombiningNext() { this }

		@Override
		void process(int code, StringBuilder sb) {
			sb.appendCodePoint(combiningCode())
			sb.appendCodePoint(code)
		}

		@Override
		void last(StringBuilder sb) {
			sb.appendCodePoint(combiningCode())
		}
	}

	class Unbook extends GeneralState {
		State thisCombiningNext() { this }
		State otherCombiningNext() { this }
		State nonCombiningNext() { new Book() }

		@Override
		void process(int code, StringBuilder sb) {
			sb.appendCodePoint(code)
		}
	}

	class Book extends GeneralState {
		State thisCombiningNext() { new Unbook() }
		State otherCombiningNext() { this }
		State nonCombiningNext() { new Print() }

		@Override
		void process(int code, StringBuilder sb) {
			sb.appendCodePoint(code)
		}

		@Override
		void last(StringBuilder sb) {
			sb.appendCodePoint(combiningCode())
		}
	}

} // }}}
