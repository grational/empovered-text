package it.grational.text

import spock.lang.*

class EmphasisUSpec extends Specification {

	def "Should transform a regular text into an italic one"() {
		given:
			String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú"

		and:
			String expected = "𝑎𝑏𝑐𝑑𝑒𝑓𝑔ℎ𝑖𝑗𝑘𝑙𝑚𝑛𝑜𝑝𝑞𝑟𝑠𝑡𝑢𝑣𝑤𝑥𝑦𝑧𝐴𝐵𝐶𝐷𝐸𝐹𝐺𝐻𝐼𝐽𝐾𝐿𝑀𝑁𝑂𝑃𝑄𝑅𝑆𝑇𝑈𝑉𝑊𝑋𝑌𝑍0123456789𝑎̀𝑒̀𝑖̀𝑜̀𝑢̀𝑎́𝑒́𝑖́𝑜́𝑢́"

		expect:
			new Emphasis().filter(input) == expected
	}

}
