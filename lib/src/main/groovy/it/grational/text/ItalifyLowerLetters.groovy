package it.grational.text

class ItalifyLowerLetters extends Unicalc {
	static final Integer offset = 119789
	static final List codePointRange = (97..103) + (105..122)
	
	final protected TextFilter origin

	ItalifyLowerLetters (
		TextFilter origin = new Unchanged()
	) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}

}
