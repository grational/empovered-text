package it.grational.text

import spock.lang.*

class StrongUSpec extends Specification {

	def "Should transform a regular unicode text in a bold one"() {
		given:
			String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú"

		and:
			String expected = "𝐚𝐛𝐜𝐝𝐞𝐟𝐠𝐡𝐢𝐣𝐤𝐥𝐦𝐧𝐨𝐩𝐪𝐫𝐬𝐭𝐮𝐯𝐰𝐱𝐲𝐳𝐀𝐁𝐂𝐃𝐄𝐅𝐆𝐇𝐈𝐉𝐊𝐋𝐌𝐍𝐎𝐏𝐐𝐑𝐒𝐓𝐔𝐕𝐖𝐗𝐘𝐙𝟎𝟏𝟐𝟑𝟒𝟓𝟔𝟕𝟖𝟗𝐚̀𝐞̀𝐢̀𝐨̀𝐮̀𝐚́𝐞́𝐢́𝐨́𝐮́"

		expect:
			new Strong().filter(input) == expected
	}

}
