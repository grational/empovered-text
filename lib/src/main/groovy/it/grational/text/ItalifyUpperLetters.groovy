package it.grational.text

class ItalifyUpperLetters extends Unicalc {
	static final Integer offset = 119795
	static final List codePointRange = 65..90
	
	final protected TextFilter origin

	ItalifyUpperLetters (
		TextFilter origin = new Unchanged()
	) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}

}
