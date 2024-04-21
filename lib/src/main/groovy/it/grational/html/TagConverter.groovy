package it.grational.html

import org.jsoup.nodes.Node

interface TagConverter {
	void convert(Node tag)
}
