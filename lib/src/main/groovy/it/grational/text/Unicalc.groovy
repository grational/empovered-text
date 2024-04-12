package it.grational.text

import static java.lang.Character.*

abstract class Unicalc implements TextFilter {

	protected String calibrateOffset (
		String input,
		Tuple2 lower,
		Tuple2 upper,
		Tuple2 digit
	) {
		int lowerOffset = charOffset(lower)
		int upperOffset = charOffset(upper)
		int digitOffset = charOffset(digit)
		return alphaOffset (
			input,
			lowerOffset,
			upperOffset,
			digitOffset
		)
	}

	protected int charOffset(Tuple2 t, int index = 0) {
		codePointAt(t.first, index) - codePointAt(t.second, index)
	}

	protected String alphaOffset (
		String input,
		int lowerOffset,
		int upperOffset,
		int digitOffset
	) {
		input.collect { String s ->
			char c = s.charAt(0)
			int codePoint = codePointAt(s,0)
			String result
			if ( isLowerCaseCharsButPlanckConstant(c) ) {
				result = codePoints2String(codePoint + lowerOffset)
			} else if ( isUpperCase(c) ) {
				result = codePoints2String(codePoint + upperOffset)
			} else if ( isDigit(c) ) {
				result = codePoints2String(codePoint + digitOffset)
			} else {
				result = s
			}
			return result
		}.join()
	}

	protected String codePoints2String(int cp) {
		new String(toChars(cp))
	}

	protected boolean isLowerCaseCharsButPlanckConstant(char c) {
		isLowerCase(c) && (c != 'ℎ')
	}

}
