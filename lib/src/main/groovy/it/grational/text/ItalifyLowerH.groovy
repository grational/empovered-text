package it.grational.text

import java.text.Normalizer
import java.util.regex.Pattern

class ItalifyLowerH extends Unicalc {
	static final List codePointRange = [ 104 ]
	static final Integer offset = 8358
	
	final protected TextFilter origin

	ItalifyLowerH (
		TextFilter origin = new Unchanged()
	) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}

}
