package it.grational.html

import org.jsoup.nodes.Element

class RenameTags implements HtmlFilter {

	private final HtmlFilter origin
	private final Element element
	private final String query
	private final String name
	

	RenameTags (
		Element element,
		String query,
		String name
	) {
		this.element = element
		this.query = query
		this.name = name
	}

	RenameTags (
		HtmlFilter origin,
		String query,
		String name
	) {
		this.origin = origin
		this.query = query
		this.name = name
	}

	@Override
	Element filter() {
		Element elem = element
			?: origin.filter()

		elem
			.select(this.query)
			.tagName(this.name)

		return elem
	}

}
