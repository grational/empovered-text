package it.grational.html

import org.jsoup.nodes.Node

class TagSurround implements TagConverter {
	private final String tagName
	private final String prefix
	private final String postfix
	private final TagConverter origin

	TagSurround (
		String tagName,
		String surround,
		TagConverter origin = new NoConversion()
	) {
		this (
			tagName,
			surround,
			surround,
			origin,
		)
	}

	TagSurround (
		String tagName,
		String prefix,
		String postfix,
		TagConverter origin = new NoConversion()
	) {
		this.tagName = tagName
		this.prefix = prefix
		this.postfix = postfix
		this.origin = origin
	}

	@Override
	void convert(Node tag) {
		if ( tag.nodeName() == tagName )
			tag.before(prefix).after(postfix)
		else
			this.origin.convert(tag)
	}
}
