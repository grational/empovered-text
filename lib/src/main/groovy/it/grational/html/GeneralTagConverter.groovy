package it.grational.html

import org.jsoup.nodes.Node

abstract class GeneralTagConverter implements TagConverter {

	@Override
	void convert(Node tag) {
		if ( tag.nodeName() == tagName() )
			tag.text(convert(tag.text()))
		else
			origin().convert(tag)
	}

	abstract protected String tagName()
	abstract protected String convert(String input)
	abstract protected TagConverter origin()
}
