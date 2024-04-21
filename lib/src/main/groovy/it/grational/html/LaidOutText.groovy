package it.grational.html

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.NodeTraversor
import it.grational.text.TextFilter
import it.grational.text.CompactEmptyLines
import it.grational.text.CompactInternalSpaces
import it.grational.text.RemoveTrailingSpaces

class LaidOutText implements TextFilter {

	private static final String ls = System.lineSeparator()
	private static final Map tags = [
		paragraph: [
			name: 'p',
			surround: ls
		],
		list: [
			name: 'li',
			prefix: "${ls} * ",
			postfix: ls
		]
	]

	@Override
	String filter(String input) {
		TextBuilder visitor = new TextBuilder (
			new TagSurround (
				tags.list.name,
				tags.list.prefix,
				tags.list.postfix,
				new TagSurround (
					tags.paragraph.name,
					tags.paragraph.surround
				)
			)
		)

		Document doc = Jsoup.parse(input)

		NodeTraversor.traverse (
			visitor,
			doc
		)

		return new CompactInternalSpaces (
			new CompactEmptyLines (
				new RemoveTrailingSpaces()
			)
		).filter (
			doc.wholeText().trim()
		)
	}
}
