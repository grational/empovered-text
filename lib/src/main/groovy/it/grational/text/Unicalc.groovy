package it.grational.text

abstract class Unicalc implements TextFilter {

	@Override
	String filter(String input) {
		StringBuilder sb = new StringBuilder()
		origin()
			.filter(input)
			.codePoints()
			.each { int cp ->
				sb.appendCodePoint (
					(cp in codePointRange() )
						? cp + offset()
						: cp
				)
			}
		return sb.toString()
	}

	protected Integer offset() { offset }
	protected List codePointRange() { codePointRange }
	abstract protected TextFilter origin()

}
