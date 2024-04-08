package it.grational.text

import spock.lang.*

class StrongEmphasisUSpec extends Specification {

	def "Should transform a regular unicode text in a bold italic one"() {
		given:
			String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú"

		and:
			String expected = "𝒂𝒃𝒄𝒅𝒆𝒇𝒈𝒉𝒊𝒋𝒌𝒍𝒎𝒏𝒐𝒑𝒒𝒓𝒔𝒕𝒖𝒗𝒘𝒙𝒚𝒛𝑨𝑩𝑪𝑫𝑬𝑭𝑮𝑯𝑰𝑱𝑲𝑳𝑴𝑵𝑶𝑷𝑸𝑹𝑺𝑻𝑼𝑽𝑾𝑿𝒀𝒁𝟎𝟏𝟐𝟑𝟒𝟓𝟔𝟕𝟖𝟗𝒂̀𝒆̀𝒊̀𝒐̀𝒖̀𝒂́𝒆́𝒊́𝒐́𝒖́"

		expect:
			new StrongEmphasis().filter(input) == expected
	}

}
