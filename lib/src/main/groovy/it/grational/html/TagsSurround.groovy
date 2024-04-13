package it.grational.html

import org.jsoup.nodes.Element

class TagsSurround implements HtmlFilter {

	private final HtmlFilter origin
	private final Element element
	private final String query
	private final String prefix
	private final String postfix

	TagsSurround (
		Element element,
		String query,
		String surround
	) {
		this (
			element,
			query,
			surround,
			surround
		)
	}

	TagsSurround (
		Element element,
		String query,
		String prefix,
		String postfix
	) {
		this.element = element
		this.query = query
		this.prefix = prefix
		this.postfix = postfix
	}

	TagsSurround (
		HtmlFilter origin,
		String query,
		String surround
	) {
		this (
			origin,
			query,
			surround,
			surround
		)
	}

	TagsSurround (
		HtmlFilter origin,
		String query,
		String prefix,
		String postfix
	) {
		this.origin = origin
		this.query = query
		this.prefix = prefix
		this.postfix = postfix
	}

	@Override
	Element filter() {
		Element elem = element
			?: origin.filter()

		elem
			.select(this.query)
			.before(this.prefix)
			.after(this.postfix)

		return elem
	}

}
