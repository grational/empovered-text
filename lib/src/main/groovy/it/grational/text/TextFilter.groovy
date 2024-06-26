package it.grational.text

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.NodeTraversor
import it.grational.html.Strong
import it.grational.html.Emphasis
import it.grational.html.Underline
import it.grational.html.TagSurround
import it.grational.html.TextBuilder

interface TextFilter {
	String filter(String input)

	class EmpoveredText implements TextFilter { // {{{

		// fields {{{
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
		// }}}

		@Override
		String filter(String input) { // {{{
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

			return new CompactInternalSpaces (
				new CompactEmptyLines (
					new RemoveTrailingSpaces()
				)
			).filter (
				doc.wholeText().trim()
			)
		} // }}}

	} // }}}

	class LaidOutText implements TextFilter { // {{{

		// fields {{{
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
		// }}}

		@Override
		String filter(String input) { // {{{
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
		} // }}}

	} // }}}

}
// vim: fdm=marker
