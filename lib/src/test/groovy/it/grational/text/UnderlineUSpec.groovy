package it.grational.text

import spock.lang.*

class UnderlineUSpec extends Specification {

	def "Should transform a regular unicode text in a underlined one"() {
		given:
			String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú"

		and:
			String expected = "a̲b̲c̲d̲e̲f̲g̲h̲i̲j̲k̲l̲m̲n̲o̲p̲q̲r̲s̲t̲u̲v̲w̲x̲y̲z̲A̲B̲C̲D̲E̲F̲G̲H̲I̲J̲K̲L̲M̲N̲O̲P̲Q̲R̲S̲T̲U̲V̲W̲X̲Y̲Z̲0̲1̲2̲3̲4̲5̲6̲7̲8̲9̲à̲è̲ì̲ò̲ù̲á̲é̲í̲ó̲ú̲"

		expect:
			new Underline().filter(input) == expected
	}

}
