package it.grational.text

import java.text.Normalizer
import java.util.regex.Pattern

class BoldifyItalicH extends Unicalc {
	static final Integer offset = 111483
	static final List codePointRange = [ 8462 ]
	
	final protected TextFilter origin

	BoldifyItalicH(TextFilter origin = new Unchanged()) {
		this.origin = origin
	}

	protected TextFilter origin() {
		this.origin
	}

}
