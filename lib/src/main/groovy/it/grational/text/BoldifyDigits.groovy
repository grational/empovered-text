package it.grational.text

class BoldifyDigits extends Unicalc {
	static final Integer offset = 120734
	static final List codePointRange = 48..57
	final protected TextFilter origin

	BoldifyDigits(TextFilter origin = new Unchanged()) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}
}
