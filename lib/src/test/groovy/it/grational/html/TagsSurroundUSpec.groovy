package it.grational.html

import spock.lang.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class TagsSurroundUSpec extends Specification {

	def "Should be capable of adding a prefix and a postfix to the elements selected with a css selection query"() {
		given:
			String enrichedText = '<p>This is a <strong>bold text</strong> with a <a href="https://www.polito.it">link</a></p>'
		and:
			Element original = Jsoup.parse(enrichedText) 
			Element mutable = original.clone()
		and:
			String query = 'strong, a'
		and:
			String prefix = 'prefixed '
			String postfix = ' postfixed'
		and:
			String expected =
			'''<html>
			| <head></head>
			| <body>
			|  <p>This is a prefixed <strong>bold text</strong> postfixed with a prefixed <a href="https://www.polito.it">link</a> postfixed</p>
			| </body>
			|</html>'''.stripMargin()

		when:
			Element output = new TagsSurround (
				mutable,
				query,
				prefix,
				postfix
			).filter()
		then:
			output.html() == expected

		when:
			def domFilter = Stub(HtmlFilter) {
				filter() >> original
			}
		and:
			output = new TagsSurround (
				domFilter,
				query,
				prefix,
				postfix
			).filter()
		then:
			output.html() == expected
	}
	

}
