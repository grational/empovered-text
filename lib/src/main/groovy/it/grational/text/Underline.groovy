package it.grational.text

class Underline implements TextFilter { // {{{

	@Override
	String filter(String input) {
		String modifier = extractModifier("a̲")
		appendModifier(input, modifier)
	}

	private String extractModifier(String input) {
		int codePoint = Character.codePointAt(input,1)
		return new String(Character.toChars(codePoint))
	}

	private String appendModifier (
		String input,
		String modifier
	) {
		input.collect { String s ->
			"${s}${modifier}"
		}.join()
	}

} // }}}
