package it.grational.html

import spock.lang.*
import static support.TestSet.*
import org.jsoup.nodes.Element

class UnderlineUSpec extends Specification {

	@Shared String tagName = 'u'

	def "Should transform accented letters just adding a lowline char as the last combining char"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> input
			}

		when:
			new Underline().convert(tag)

		then:
			1 * tag.text(expected)

		where:
			input        || expected
			'àèìòùáéíóú' || 'à̲è̲ì̲ò̲ù̲á̲é̲í̲ó̲ú̲'
			'𝑎̀𝑒̀𝑖̀𝑜̀𝑢̀𝑎́𝑒́𝑖́𝑜́𝑢́' || '𝑎̲̀𝑒̲̀𝑖̲̀𝑜̲̀𝑢̲̀𝑎̲́𝑒̲́𝑖̲́𝑜̲́𝑢̲́'
			'𝐚̀𝐞̀𝐢̀𝐨̀𝐮̀𝐚́𝐞́𝐢́𝐨́𝐮́' || '𝐚̲̀𝐞̲̀𝐢̲̀𝐨̲̀𝐮̲̀𝐚̲́𝐞̲́𝐢̲́𝐨̲́𝐮̲́'
			'𝒂̀𝒆̀𝒊̀𝒐̀𝒖̀𝒂́𝒆́𝒊́𝒐́𝒖́' || '𝒂̲̀𝒆̲̀𝒊̲̀𝒐̲̀𝒖̲̀𝒂̲́𝒆̲́𝒊̲́𝒐̲́𝒖̲́'
	}

	def "Should transform a regular text into an underlined one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allRegular
			}

		when:
			new Underline().convert(tag)

		then:
			1 * tag.text(allUnderline)
	}

	def "Should transform an italic text into the equivalent underlined one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allItalic
			}

		when:
			new Underline().convert(tag)

		then:
			1 * tag.text(allItalicUnderline)
	}

	def "Should transform a bold text into the equivalent underlined one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBold
			}

		when:
			new Underline().convert(tag)

		then:
			1 * tag.text(allBoldUnderline)
	}

	def "Should transform a bold italic text into the equivalent underlined one"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> allBoldItalic
			}

		when:
			new Underline().convert(tag)

		then:
			1 * tag.text(allBoldItalicUnderline)
	}

	def "Should leave the text as is if the tag name is not its own"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> 'notU'
				text() >> allBoldItalic
			}

		when:
			new Underline().convert(tag)

		then:
			0 * tag.text(_)
	}

	def "Should leave underlined text as is"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> input
			}

		when:
			new Underline().convert(tag)

		then:
			1 * tag.text(input)

		where: 
			input << [
				'a̲b̲c̲d̲e̲f̲g̲h̲i̲j̲k̲l̲m̲n̲o̲p̲q̲r̲s̲t̲u̲v̲w̲x̲y̲z̲A̲B̲C̲D̲E̲F̲G̲H̲I̲J̲K̲L̲M̲N̲O̲P̲Q̲R̲S̲T̲U̲V̲W̲X̲Y̲Z̲0̲1̲2̲3̲4̲5̲6̲7̲8̲9̲à̲è̲ì̲ò̲ù̲á̲é̲í̲ó̲ú̲',
				'𝑎̲𝑏̲𝑐̲𝑑̲𝑒̲𝑓̲𝑔̲ℎ̲𝑖̲𝑗̲𝑘̲𝑙̲𝑚̲𝑛̲𝑜̲𝑝̲𝑞̲𝑟̲𝑠̲𝑡̲𝑢̲𝑣̲𝑤̲𝑥̲𝑦̲𝑧̲𝐴̲𝐵̲𝐶̲𝐷̲𝐸̲𝐹̲𝐺̲𝐻̲𝐼̲𝐽̲𝐾̲𝐿̲𝑀̲𝑁̲𝑂̲𝑃̲𝑄̲𝑅̲𝑆̲𝑇̲𝑈̲𝑉̲𝑊̲𝑋̲𝑌̲𝑍̲0̲1̲2̲3̲4̲5̲6̲7̲8̲9̲𝑎̲̀𝑒̲̀𝑖̲̀𝑜̲̀𝑢̲̀𝑎̲́𝑒̲́𝑖̲́𝑜̲́𝑢̲́',
				'𝐚̲𝐛̲𝐜̲𝐝̲𝐞̲𝐟̲𝐠̲𝐡̲𝐢̲𝐣̲𝐤̲𝐥̲𝐦̲𝐧̲𝐨̲𝐩̲𝐪̲𝐫̲𝐬̲𝐭̲𝐮̲𝐯̲𝐰̲𝐱̲𝐲̲𝐳̲𝐀̲𝐁̲𝐂̲𝐃̲𝐄̲𝐅̲𝐆̲𝐇̲𝐈̲𝐉̲𝐊̲𝐋̲𝐌̲𝐍̲𝐎̲𝐏̲𝐐̲𝐑̲𝐒̲𝐓̲𝐔̲𝐕̲𝐖̲𝐗̲𝐘̲𝐙̲𝟎̲𝟏̲𝟐̲𝟑̲𝟒̲𝟓̲𝟔̲𝟕̲𝟖̲𝟗̲𝐚̲̀𝐞̲̀𝐢̲̀𝐨̲̀𝐮̲̀𝐚̲́𝐞̲́𝐢̲́𝐨̲́𝐮̲́',
				'𝒂̲𝒃̲𝒄̲𝒅̲𝒆̲𝒇̲𝒈̲𝒉̲𝒊̲𝒋̲𝒌̲𝒍̲𝒎̲𝒏̲𝒐̲𝒑̲𝒒̲𝒓̲𝒔̲𝒕̲𝒖̲𝒗̲𝒘̲𝒙̲𝒚̲𝒛̲𝑨̲𝑩̲𝑪̲𝑫̲𝑬̲𝑭̲𝑮̲𝑯̲𝑰̲𝑱̲𝑲̲𝑳̲𝑴̲𝑵̲𝑶̲𝑷̲𝑸̲𝑹̲𝑺̲𝑻̲𝑼̲𝑽̲𝑾̲𝑿̲𝒀̲𝒁̲𝟎̲𝟏̲𝟐̲𝟑̲𝟒̲𝟓̲𝟔̲𝟕̲𝟖̲𝟗̲𝒂̲̀𝒆̲̀𝒊̲̀𝒐̲̀𝒖̲̀𝒂̲́𝒆̲́𝒊̲́𝒐̲́𝒖̲́',
			]
	}

}
