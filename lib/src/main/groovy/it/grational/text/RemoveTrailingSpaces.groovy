package it.grational.text

class RemoveTrailingSpaces implements TextFilter {
	private static final String ls = System.lineSeparator()
	private final TextFilter origin

	RemoveTrailingSpaces(TextFilter origin = new Unchanged()) {
		this.origin = origin
	}

	@Override
	String filter(String input) {
		return this.origin.filter(input)
			.replaceAll (
				/[\p{Zs}\p{Blank}]+${ls}/,
				ls
			)
	}
}
