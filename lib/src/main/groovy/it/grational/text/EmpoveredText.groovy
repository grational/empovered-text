package it.grational.text

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.NodeTraversor
import it.grational.html.TextBuilder
import it.grational.html.RenameTags
import it.grational.html.RemoveTags
import it.grational.html.FirstTag

class EmpoveredText implements TextFilter {

	private static final String rootTag = 'body'
	private static final List tagsToRemove = [ 'a', 'ul' ]
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
				rootTag
			).filter()
		)

		return visitor.toString()
	}
}
