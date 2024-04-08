package it.grational.html

import spock.lang.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class RenameTagsUSpec extends Specification {

	@Shared String enrichedText = 'This is a <strong>bold text</strong>, this instead is a <strong><em>bold italic one</em></strong>, this is even an <strong><em><u>underlined bold italic text</u></em></strong>'

	def "Should be capable of renaming the tags selected using the css query language"() {
		given:
			def domFilter = Stub(HtmlFilter) {
				filter() >> Jsoup.parse(enrichedText) 
			}
		and:
			String tagQuery = 'strong'
		and:
			String newName = 'bold'
		and:
			String expected =
			'''<html>
			| <head></head>
			| <body>
			|  This is a <bold>
			|   bold text
			|  </bold>, this instead is a <bold>
			|   <em>bold italic one</em>
			|  </bold>, this is even an <bold>
			|   <em><u>underlined bold italic text</u></em>
			|  </bold>
			| </body>
			|</html>'''.stripMargin()

		when:
			Element output = new RenameTags (
				domFilter,
				tagQuery,
				newName
			).filter()

		then:
			output.html() == expected
	}
	
	def "Should be capable of using more comples queries"() {
		given:
			Element element =  Jsoup.parse(enrichedText) 
		and:
			String tagQuery = 'strong em, strong u'
		and:
			String newName = 'multiple'
		and:
			String expected =
			'''<html>
			| <head></head>
			| <body>
			|  This is a <strong>bold text</strong>, this instead is a <strong><multiple>
			|    bold italic one
			|   </multiple></strong>, this is even an <strong><multiple>
			|    <multiple>
			|     underlined bold italic text
			|    </multiple>
			|   </multiple></strong>
			| </body>
			|</html>'''.stripMargin()

		when:
			Element output = new RenameTags (
				element,
				tagQuery,
				newName
			).filter()

		then:
			output.html() == expected
	}

}
