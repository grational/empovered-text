package it.grational.html

import spock.lang.*
import static support.TestSet.*
import org.jsoup.nodes.Element

class DeleteUSpec extends Specification {

	@Shared List<String> tagNames = [ 'del', 'strike', 's' ]

	def "Should transform accented letters just adding the deletion char as the last combining char"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> input
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(expected)

		where:
			tagName  | input        || expected
			'del'    | 'àèìòùáéíóú' || 'à̶è̶ì̶ò̶ù̶á̶é̶í̶ó̶ú̶'
			'del'    | '𝑎̀𝑒̀𝑖̀𝑜̀𝑢̀𝑎́𝑒́𝑖́𝑜́𝑢́' || '𝑎̶̀𝑒̶̀𝑖̶̀𝑜̶̀𝑢̶̀𝑎̶́𝑒̶́𝑖̶́𝑜̶́𝑢̶́'
			'del'    | '𝐚̀𝐞̀𝐢̀𝐨̀𝐮̀𝐚́𝐞́𝐢́𝐨́𝐮́' || '𝐚̶̀𝐞̶̀𝐢̶̀𝐨̶̀𝐮̶̀𝐚̶́𝐞̶́𝐢̶́𝐨̶́𝐮̶́'
			'del'    | '𝒂̀𝒆̀𝒊̀𝒐̀𝒖̀𝒂́𝒆́𝒊́𝒐́𝒖́' || '𝒂̶̀𝒆̶̀𝒊̶̀𝒐̶̀𝒖̶̀𝒂̶́𝒆̶́𝒊̶́𝒐̶́𝒖̶́'

			'strike' | 'àèìòùáéíóú' || 'à̶è̶ì̶ò̶ù̶á̶é̶í̶ó̶ú̶'
			'strike' | '𝑎̀𝑒̀𝑖̀𝑜̀𝑢̀𝑎́𝑒́𝑖́𝑜́𝑢́' || '𝑎̶̀𝑒̶̀𝑖̶̀𝑜̶̀𝑢̶̀𝑎̶́𝑒̶́𝑖̶́𝑜̶́𝑢̶́'
			'strike' | '𝐚̀𝐞̀𝐢̀𝐨̀𝐮̀𝐚́𝐞́𝐢́𝐨́𝐮́' || '𝐚̶̀𝐞̶̀𝐢̶̀𝐨̶̀𝐮̶̀𝐚̶́𝐞̶́𝐢̶́𝐨̶́𝐮̶́'
			'strike' | '𝒂̀𝒆̀𝒊̀𝒐̀𝒖̀𝒂́𝒆́𝒊́𝒐́𝒖́' || '𝒂̶̀𝒆̶̀𝒊̶̀𝒐̶̀𝒖̶̀𝒂̶́𝒆̶́𝒊̶́𝒐̶́𝒖̶́'

			's'      | 'àèìòùáéíóú' || 'à̶è̶ì̶ò̶ù̶á̶é̶í̶ó̶ú̶'
			's'      | '𝑎̀𝑒̀𝑖̀𝑜̀𝑢̀𝑎́𝑒́𝑖́𝑜́𝑢́' || '𝑎̶̀𝑒̶̀𝑖̶̀𝑜̶̀𝑢̶̀𝑎̶́𝑒̶́𝑖̶́𝑜̶́𝑢̶́'
			's'      | '𝐚̀𝐞̀𝐢̀𝐨̀𝐮̀𝐚́𝐞́𝐢́𝐨́𝐮́' || '𝐚̶̀𝐞̶̀𝐢̶̀𝐨̶̀𝐮̶̀𝐚̶́𝐞̶́𝐢̶́𝐨̶́𝐮̶́'
			's'      | '𝒂̀𝒆̀𝒊̀𝒐̀𝒖̀𝒂́𝒆́𝒊́𝒐́𝒖́' || '𝒂̶̀𝒆̶̀𝒊̶̀𝒐̶̀𝒖̶̀𝒂̶́𝒆̶́𝒊̶́𝒐̶́𝒖̶́'
	}

	def "Should transform a regular text into an deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allRegular
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allDelete)

		where:
			tagName << tagNames
	}

	def "Should transform an italic text into the equivalent deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allItalic
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allItalicDelete)

		where:
			tagName << tagNames
	}

	def "Should transform a bold text into the equivalent deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBold
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allBoldDelete)

		where:
			tagName << tagNames
	}

	def "Should transform an underlined text into the equivalent deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allUnderline
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allUnderlineDelete)

		where:
			tagName << tagNames
	}

	def "Should transform a bold italic text into the equivalent deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldItalic
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allBoldItalicDelete)

		where:
			tagName << tagNames
	}

	def "Should transform an italic underlined text into the equivalent deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allItalicUnderline
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allItalicUnderlineDelete)

		where:
			tagName << tagNames
	}

	def "Should transform a bold underlined text into the equivalent deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldUnderline
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allBoldUnderlineDelete)

		where:
			tagName << tagNames
	}

	def "Should transform a bold italic underlined text into the equivalent deleted one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldItalicUnderline
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(allBoldItalicUnderlineDelete)

		where:
			tagName << tagNames
	}

	def "Should leave the text as is if the tag name is not its own"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> 'notU'
				text() >> allBoldItalic
			}

		when:
			new Delete().convert(tag)

		then:
			0 * tag.text(_)

		where:
			tagName << tagNames
	}

	def "Should leave deleted text as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> input
			}

		when:
			new Delete().convert(tag)

		then:
			1 * tag.text(input)

		where: 
			tagName    | input
				'del'    | allDelete
				'del'    | allItalicDelete
				'del'    | allBoldDelete
				'del'    | allBoldItalicDelete
				'strike' | allDelete
				'strike' | allItalicDelete
				'strike' | allBoldDelete
				'strike' | allBoldItalicDelete
				's'      | allDelete
				's'      | allItalicDelete
				's'      | allBoldDelete
				's'      | allBoldItalicDelete
	}

}
