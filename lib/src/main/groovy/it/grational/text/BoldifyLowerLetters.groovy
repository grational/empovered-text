package it.grational.text

class BoldifyLowerLetters extends Unicalc {
	static final Integer offset = 119737
	static final List codePointRange = 97..122
	final protected TextFilter origin

	BoldifyLowerLetters(TextFilter origin = new Unchanged()) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}

}
