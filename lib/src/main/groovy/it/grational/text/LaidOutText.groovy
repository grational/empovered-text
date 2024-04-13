package it.grational.text

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.NodeTraversor
import it.grational.html.TextBuilder
import it.grational.html.TagsSurround
import it.grational.html.RemoveTags
import it.grational.html.FirstTag

class LaidOutText implements TextFilter {

	private static final String ls = System.lineSeparator()
	private static final String rootTag = 'body'
	private static final List tagsToRemove = [
		'strong',
		'em',
		'u',
		'a',
		'ul'
	]
	private static final Map tags = [
		paragraph: [
			query: 'p',
			surround: ls
		],
		list: [
			query: 'li',
			prefix: "${ls} * ",
			postfix: ls
		]
	]

	@Override
	String filter(String input) {
		TextBuilder visitor = new TextBuilder (
			new Unchanged(),
			new Unchanged(),
			new Unchanged(),
			new Unchanged(),
			rootTag
		)

		NodeTraversor.traverse (
			visitor,
			new FirstTag (
				new TagsSurround (
					new TagsSurround (
						new RemoveTags (
							Jsoup.parse(input),
							tagsToRemove,
						),
						tags.paragraph.query,
						tags.paragraph.surround
					),
					tags.list.query,
					tags.list.prefix,
					tags.list.postfix
				),
				rootTag
			).filter()
		)

		return visitor.toString()
	}
}
