package it.grational.text

import java.util.regex.Pattern

class BoldifyItalicLetters extends Unicalc {
	static final Integer offset = 52
	static final List codePointRange = (119860..119892) + (119894..119911)
	final protected TextFilter origin

	BoldifyItalicLetters(TextFilter origin = new Unchanged()) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}

}
