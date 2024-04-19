package it.grational.text

class ItalifyBoldLetters extends Unicalc {
	static final Integer offset = 104
	static final List codePointRange = (119808..119833) + (119834..119859)
	
	final protected TextFilter origin

	ItalifyBoldLetters (
		TextFilter origin = new Unchanged()
	) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}

}
