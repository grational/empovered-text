package it.grational.html

import org.jsoup.nodes.Element

class RemoveTags implements HtmlFilter {

	private final Element element
	private final HtmlFilter origin
	private final List<String> tagsToRemove

	RemoveTags (
		Element element,
		List<String> tags
	) {
		this.element = element
		this.tagsToRemove = tags
	}

	RemoveTags (
		HtmlFilter origin,
		List<String> tags
	) {
		this.origin = origin
		this.tagsToRemove = tags
	}

	@Override
	Element filter() {
		Element elem = element 
			?: origin.filter()

		elem
			.select(cssSelector())
			.unwrap()

		return elem
	}

	private String cssSelector() {
		this.tagsToRemove.join(',')
	}
}
