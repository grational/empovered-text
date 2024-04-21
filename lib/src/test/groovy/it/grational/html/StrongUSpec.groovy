package it.grational.html

import spock.lang.*
import static support.TestSet.*
import org.jsoup.nodes.Element

class StrongUSpec extends Specification {

	@Shared String tagName = 'strong'

	def "Should transform a regular unicode text into a bold one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allRegular
			}

		when:
			new Strong().convert(tag)

		then:
			1 * tag.text(allBold)
	}

	def "Should transform an emphasis unicode text into a bold italic one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allItalic
			}

		when:
			new Strong().convert(tag)

		then:
			1 * tag.text(allBoldItalic)
	}

	def "Should transform a regular text with underscore into a bold one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allUnderline
			}

		when:
			new Strong().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allBoldUnderline)
			)
	}

	def "Should transform an italic text with underscore into a bold italic one with underscore"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allItalicUnderline
			}

		when:
			new Strong().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allBoldItalicUnderline)
			)
	}

	def "Should leave a bold text as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBold
			}

		when:
			new Strong().convert(tag)

		then:
			1 * tag.text(allBold)
	}

	def "Should leave a bold text with underscore as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldUnderline
			}

		when:
			new Strong().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allBoldUnderline)
			)
	}

	def "Should leave a bold italic text with underscore as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldItalicUnderline
			}

		when:
			new Strong().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allBoldItalicUnderline)
			)
	}

	def "Should avoid to transform text if the tag is not its own"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> 'notStrong'
				text() >> allRegular
			}

		when:
			new Strong().convert(tag)

		then:
			0 * tag.text(_)
	}

}
