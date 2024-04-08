package it.grational.html

import spock.lang.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class RemoveTagsUSpec extends Specification {

	def "Should be capable of removing the tags passed as parameters"() {
		given:
			String enrichedText = 'This is a <strong>bold text</strong> with a <a href="https://www.polito.it">link</a><p>'
		and:
			Element element = Jsoup.parse(enrichedText) 
		and:
			List tagsToRemove = [ 'a', 'p' ]
		and:
			String expected =
			'''<html>
			| <head></head>
			| <body>
			|  This is a <strong>bold text</strong> with a link
			| </body>
			|</html>'''.stripMargin()

		when:
			Element output = new RemoveTags (
				element,
				tagsToRemove
			).filter()
		then:
			output.html() == expected

		when:
			def domFilter = Stub(HtmlFilter) {
				filter() >> element
			}
		and:
			output = new RemoveTags (
				domFilter,
				tagsToRemove
			).filter()
		then:
			output.html() == expected
	}
	

}
