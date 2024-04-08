package it.grational.html

import org.jsoup.nodes.Element

class FirstTag implements HtmlFilter {

	private final HtmlFilter origin
	private final Element element
	private final String tag
	
	FirstTag (
		Element element,
		String tag
	) {
		this.element = element
		this.tag = tag
	}

	FirstTag (
		HtmlFilter origin,
		String tag
	) {
		this.origin = origin
		this.tag = tag
	}

	@Override
	Element filter() {
		Element elem = element
			?: origin.filter()

		elem
			.select(tag)
			.first()
	}

}
