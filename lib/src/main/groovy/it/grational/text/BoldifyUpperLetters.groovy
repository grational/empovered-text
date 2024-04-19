package it.grational.text

class BoldifyUpperLetters extends Unicalc {
	static final Integer offset = 119743
	static final List codePointRange = 65..90
	final protected TextFilter origin

	BoldifyUpperLetters(TextFilter origin = new Unchanged()) {
		this.origin = origin
	}

	protected TextFilter origin() { this.origin }
}
