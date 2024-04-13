package it.grational.html

import spock.lang.*
import org.jsoup.nodes.Element
import it.grational.text.TextFilter

class TextBuilderUSpec extends Specification {

	@Shared String ls = System.lineSeparator()
	@Shared String strongContent = '𝐬𝐭𝐫𝐨𝐧𝐠 𝐜𝐨𝐧𝐭𝐞𝐧𝐭'
	@Shared String emphasisContent = '𝑒𝑚𝑝ℎ𝑎𝑠𝑖𝑠 𝑐𝑜𝑛𝑡𝑒𝑛𝑡'
	@Shared String underlineContent = 'u̲n̲d̲e̲r̲l̲i̲n̲e̲ ̲c̲o̲n̲t̲e̲n̲t̲'
	@Shared String emphasisUnderlineContent = '𝑒̲𝑚̲𝑝̲̲ℎ̲𝑎̲𝑠̲𝑖̲𝑠̲ ̲𝑢̲𝑛̲𝑑̲𝑒̲𝑟̲𝑙̲𝑖̲𝑛̲𝑒̲ ̲𝑐̲𝑜̲𝑛̲𝑡̲𝑒̲𝑛̲𝑡̲'
	@Shared String strongUnderlineContent = '𝐬̲𝐭̲𝐫̲𝐨̲𝐧̲𝐠̲ ̲𝐮̲𝐧̲𝐝̲𝐞̲𝐫̲𝐥̲𝐢̲𝐧̲𝐞̲ ̲𝐜̲𝐨̲𝐧̲𝐭̲𝐞̲𝐧̲𝐭̲'
	@Shared String strongEmphasisContent = '𝒔𝒕𝒓𝒐𝒏𝒈 𝒆𝒎𝒑𝒉𝒂𝒔𝒊𝒔 𝒄𝒐𝒏𝒕𝒆𝒏𝒕'
	@Shared String strongEmphasisUnderlineContent = '𝒔̲𝒕̲𝒓̲𝒐̲𝒏̲𝒈̲ ̲𝒆̲𝒎̲𝒑̲𝒉̲𝒂̲𝒔̲𝒊̲𝒔̲ ̲𝒖̲𝒏̲𝒅̲𝒆̲𝒓̲𝒍̲𝒊̲𝒏̲𝒆̲ ̲𝒄̲𝒐̲𝒏̲𝒕̲𝒆̲𝒏̲𝒕̲'

	@Shared TextFilter strongFilter
	@Shared TextFilter emphasisFilter
	@Shared TextFilter underlineFilter
	@Shared TextFilter strongEmphasisFilter

	@Shared TextBuilder textBuilder

	def setupSpec() {
		strongFilter = Stub() {
			filter(emphasisContent) >> strongEmphasisContent
			filter(underlineContent) >> strongUnderlineContent
			filter(_) >> strongContent
		}
		emphasisFilter = Stub() {
			filter(underlineContent) >> emphasisUnderlineContent
			filter(_) >> emphasisContent
		}
		underlineFilter = Stub() {
			filter(_) >> underlineContent
		}
		strongEmphasisFilter = Stub() {
			filter(underlineContent) >> strongEmphasisUnderlineContent
			filter(_) >> strongEmphasisContent
		}

		textBuilder = new TextBuilder (
			strongFilter,
			emphasisFilter,
			underlineFilter,
			strongEmphasisFilter
		)
	}

	@Unroll
	def "Should be capable of altering the text inside the supported html tags"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> tagContent
			}

		when:
			textBuilder.tail(tag, 0)

		then:
			1 * tag.text(expected)

		where:
			tagName     | tagContent                          || expected
			'strong'    | 'strong content'                    || strongContent
			'em'        | 'emphasis content'                  || emphasisContent
			'u'         | 'underline content'                 || underlineContent
			'emu'       | 'emphasis underline content'        || emphasisUnderlineContent
			'strongu'   | 'strong underline content'          || strongUnderlineContent
			'strongem'  | 'strong emphasis content'           || strongEmphasisContent
			'strongemu' | 'strong emphasis underline content' || strongEmphasisUnderlineContent
	}

	def "Should dump all the text when a root tag is encountered"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				wholeText() >> tagContent
			}

		when:
			textBuilder.tail(tag, 0)

		then:
			textBuilder.toString() == tagContent

		where:
			tagName  | tagContent
			'root'   | '<p><strong>strong content</strong> <em>emphasis content</em> <u>underline content</u></p>'
	}

	def "Should be able to trim and remove multiple empty lines and trailing spaces from the output"() {
		given:
			TextBuilder tb = new TextBuilder (
				strongFilter,
				emphasisFilter,
				underlineFilter,
				strongEmphasisFilter
			)
		and:
			Element tag = Mock(Element) {
				nodeName() >> 'root'
				wholeText() >> '''
				|
				|
				|<ul>
				| <li>
				|  <strong>strong   content</strong>
				|  
				|  
				|  <em>emphasis  content</em>
				|		
				|		
				|  <u>underline content</u>
				| </li>
				|</ul>
				|
				|
				|
				|'''.stripMargin()
			}
		and:
			String expected = '''\
			|<ul>
			| <li>
			| <strong>strong content</strong>
			|
			| <em>emphasis content</em>
			|
			| <u>underline content</u>
			| </li>
			|</ul>'''.stripMargin()

		when:
			tb.tail(tag, 0)

		then:
			tb.toString() == expected
	}

}
