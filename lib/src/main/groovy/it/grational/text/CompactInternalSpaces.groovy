package it.grational.text

class CompactInternalSpaces implements TextFilter {
	private static final String ls = System.lineSeparator()
	private static final Integer nonBreakableSpaceCode = 160
	private static final String nonBreakableSpace = ' '
	private static final String regularSpace = ' '
	private final TextFilter origin

	CompactInternalSpaces(TextFilter origin = new Unchanged()) {
		this.origin = origin
	}

	@Override
	String filter(String input) {
		return this.origin.filter(input)
			.replaceAll(/(?<=\S)[\p{Zs}\p{Blank}]{2,}/) { String match ->
				match.codePoints().collect().contains(160)
					? nonBreakableSpace
					: regularSpace
			}
	}
}
