package it.grational.html

import spock.lang.*
import static support.TestSet.*
import org.jsoup.nodes.Element

class EmphasisUSpec extends Specification {

	@Shared List<String> tagNames = [ 'em', 'italic', 'i' ]

	def "Should transform a regular text into an italic one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allRegular
			}

		when:
			new Emphasis().convert(tag)

		then:
			1 * tag.text(allItalic)

		where:
			tagName << tagNames
	}

	def "Should transform a bold text into a bold italic one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBold
			}

		when:
			new Emphasis().convert(tag)

		then:
			1 * tag.text(allBoldItalic)

		where:
			tagName << tagNames
	}

	def "Should transform a regular text with underscore into an italic one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allUnderline
			}

		when:
			new Emphasis().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allItalicUnderline)
			)

		where:
			tagName << tagNames
	}

	def "Should transform a bold text with underscore into its italic equivalent"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldUnderline
			}

		when:
			new Emphasis().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allBoldItalicUnderline)
			)

		where:
			tagName << tagNames
	}

	def "Should leave an italic text as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allItalic
			}

		when:
			new Emphasis().convert(tag)

		then:
			1 * tag.text(allItalic)

		where:
			tagName << tagNames
	}

	def "Should leave an italic text underlined as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allItalicUnderline
			}

		when:
			new Emphasis().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allItalicUnderline)
			)

		where:
			tagName << tagNames
	}

	def "Should leave a bold italic text with underscore as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldItalicUnderline
			}

		when:
			new Emphasis().convert(tag)

		then:
			1 * tag.text (
				normalizeCombiningChars(allBoldItalicUnderline)
			)

		where:
			tagName << tagNames
	}

	def "Should avoid to transform text if the tag is not its own"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> 'unrecognized'
				text() >> allRegular
			}

		when:
			new Emphasis().convert(tag)

		then:
			0 * tag.text(_)
	}

}
