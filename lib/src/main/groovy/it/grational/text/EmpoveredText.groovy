package it.grational.text

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.NodeTraversor
import it.grational.html.TextBuilder
import it.grational.html.RenameTags
import it.grational.html.RemoveTags
import it.grational.html.FirstTag
import it.grational.html.TagsSurround

class EmpoveredText implements TextFilter {

	private static final String rootTag = 'body'
	private static final List tagsToRemove = [ 'a', 'ul' ]
	private static final String ls = System.lineSeparator()
	private static final Map tags = [
		strongEmphasisUnderline: [
			query: 'strong em u, em strong u, em strongu u, em u strong, u strong em, u em strong',
			name: 'strongemu'
		],
		strongEmphasis: [
			query: 'strong em, em strong',
			name: 'strongem'
		],
		strongUnderline: [
			query: 'strong u, u strong',
			name: 'strongu'
		],
		emphasisUnderline: [
		 query: 'em u, u em',
		 name: 'emu'
		],
		paragraph: [
			query: 'p',
			surround: ls
		],
		list: [
			query: 'li',
			prefix: "${ls} • ",
			postfix: ls
		]
	]

	@Override
	String filter(String input) {
		TextBuilder visitor = new TextBuilder (
			new Strong(),
			new Emphasis(),
			new Underline(),
			new StrongEmphasis(),
			rootTag
		)

		NodeTraversor.traverse (
			visitor,
			new FirstTag (
				new TagsSurround (
					new TagsSurround (
						new RenameTags (
							new RenameTags (
								new RenameTags (
									new RenameTags (
										new RemoveTags (
											Jsoup.parse(input),
											tagsToRemove,
										),
										tags.strongEmphasisUnderline.query,
										tags.strongEmphasisUnderline.name
									),
									tags.strongEmphasis.query,
									tags.strongEmphasis.name
								),
								tags.strongUnderline.query,
								tags.strongUnderline.name
							),
							tags.emphasisUnderline.query,
							tags.emphasisUnderline.name
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
