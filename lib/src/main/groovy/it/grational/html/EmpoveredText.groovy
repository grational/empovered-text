package it.grational.html

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.NodeTraversor
import it.grational.text.TextFilter

class EmpoveredText implements TextFilter {

	private static final String ls = System.lineSeparator()
	private static final Map tags = [
		paragraph: [
			name: 'p',
			surround: ls
		],
		list: [
			name: 'li',
			prefix: "${ls} • ",
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
					tags.paragraph.surround,
					new Strong (
						new Emphasis (
							new Underline()
						)
					)
				)
			)
		)

		Document doc = Jsoup.parse(input)

		NodeTraversor.traverse (
			visitor,
			doc
		)

		return doc.wholeText()
			.trim() // remove leading and trailing spaces for the entire block
			.replaceAll(/\p{Zs}+${ls}/,ls) // remove line trailing spaces
			.replaceAll(/(\p{Zs})\p{Zs}+/,'$1') // compact more spaces into one
			.replaceAll(/(\p{Zs}*${ls}){3,}/,"${ls}${ls}") // compact 3+ newlines into 2
	}

}
