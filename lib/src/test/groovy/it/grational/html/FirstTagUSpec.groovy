package it.grational.html

import spock.lang.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class FirstTagUSpec extends Specification {

	def "Should be capable of returning the element associated with the fitst tag passed"() {
		given:
			String enrichedText = 'This is a <strong>bold text</strong> with a <a href="https://www.polito.it">link</a><p><ul><li><strong>first</strong> element</li><second element</li></ul><p>last paragraph</p>'
		and:
			Element element = Jsoup.parse(enrichedText) 
		and:
			String selectedTag = 'li'
		and:
			String expected = '<li><strong>first</strong> element</li>'

		when:
			Element output = new FirstTag (
				element,
				selectedTag
			).filter()
		then:
			output.toString() == expected

		when:
			def domFilter = Stub(HtmlFilter) {
				filter() >> element
			}
		and:
			output = new FirstTag (
				domFilter,
				selectedTag
			).filter()
		then:
			output.toString() == expected

	}

}
