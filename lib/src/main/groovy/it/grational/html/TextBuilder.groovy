package it.grational.html

import org.jsoup.nodes.Node
import org.jsoup.select.NodeVisitor

class TextBuilder implements NodeVisitor {
	private static final String ls = System.lineSeparator()
	private final TagConverter converter

	TextBuilder(TagConverter converter) {
		this.converter = converter
	}

	@Override
	void head(Node tag, int depth) {}

	@Override
	void tail(Node tag, int depth) {
		converter.convert(tag)
	}

}
