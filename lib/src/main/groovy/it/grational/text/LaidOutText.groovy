package it.grational.text

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.NodeTraversor
import it.grational.html.TextBuilder
import it.grational.html.TagsSurround
import it.grational.html.NoConversion

class LaidOutText implements TextFilter {

	private static final String ls = System.lineSeparator()
	private static final Map tags = [
		paragraph: [
			query: 'p',
			surround: ls
		],
		list: [
			query: 'li',
			prefix: "${ls} * ",
			postfix: ls
		]
	]

	@Override
	String filter(String input) {
		TextBuilder visitor = new TextBuilder (
			new NoConversion()
		)

		Document doc = Jsoup.parse(input)

		NodeTraversor.traverse (
			visitor,
				new TagsSurround (
					new TagsSurround (
						doc,
						tags.paragraph.query,
						tags.paragraph.surround
					),
					tags.list.query,
					tags.list.prefix,
					tags.list.postfix
			).filter()
		)

		return doc.wholeText()
			.trim() // remove leading and trailing spaces for the entire block
			.replaceAll(/\p{Zs}+${ls}/,ls) // remove line trailing spaces
			.replaceAll(/(\p{Zs})\p{Zs}+/,'$1') // compact more spaces into one
			.replaceAll(/(\p{Zs}*${ls}){3,}/,"${ls}${ls}") // compact 3+ newlines into 2
	}
}
