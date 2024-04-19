package it.grational.text

import spock.lang.*

class ItalifyLowerHUSpec extends Specification {

	@Unroll
	def "Should transform a regular h into its italic equivalent (actually, the planck constant)"() {
		expect:
			new ItalifyLowerH().filter(input) == expected

		where:
			input << [
				'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú',
				'a̲b̲c̲d̲e̲f̲g̲h̲i̲j̲k̲l̲m̲n̲o̲p̲q̲r̲s̲t̲u̲v̲w̲x̲y̲z̲A̲B̲C̲D̲E̲F̲G̲H̲I̲J̲K̲L̲M̲N̲O̲P̲Q̲R̲S̲T̲U̲V̲W̲X̲Y̲Z̲0̲1̲2̲3̲4̲5̲6̲7̲8̲9̲à̲è̲ì̲ò̲ù̲á̲é̲í̲ó̲ú̲',
			]
			expected << [
				'abcdefgℎijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú',
				"a̲b̲c̲d̲e̲f̲g̲ℎ̲i̲j̲k̲l̲m̲n̲o̲p̲q̲r̲s̲t̲u̲v̲w̲x̲y̲z̲A̲B̲C̲D̲E̲F̲G̲H̲I̲J̲K̲L̲M̲N̲O̲P̲Q̲R̲S̲T̲U̲V̲W̲X̲Y̲Z̲0̲1̲2̲3̲4̲5̲6̲7̲8̲9̲à̲è̲ì̲ò̲ù̲á̲é̲í̲ó̲ú̲",
			]
	}

	@Unroll
	def "Should leave bold text as is"() {
		expect:
			new ItalifyLowerH().filter(input) == input

		where:
			input << [
				'𝐚𝐛𝐜𝐝𝐞𝐟𝐠𝐡𝐢𝐣𝐤𝐥𝐦𝐧𝐨𝐩𝐪𝐫𝐬𝐭𝐮𝐯𝐰𝐱𝐲𝐳𝐀𝐁𝐂𝐃𝐄𝐅𝐆𝐇𝐈𝐉𝐊𝐋𝐌𝐍𝐎𝐏𝐐𝐑𝐒𝐓𝐔𝐕𝐖𝐗𝐘𝐙𝟎𝟏𝟐𝟑𝟒𝟓𝟔𝟕𝟖𝟗𝐚̀𝐞̀𝐢̀𝐨̀𝐮̀𝐚́𝐞́𝐢́𝐨́𝐮́',
				'𝐚̲𝐛̲𝐜̲𝐝̲𝐞̲𝐟̲𝐠̲𝐡̲𝐢̲𝐣̲𝐤̲𝐥̲𝐦̲𝐧̲𝐨̲𝐩̲𝐪̲𝐫̲𝐬̲𝐭̲𝐮̲𝐯̲𝐰̲𝐱̲𝐲̲𝐳̲𝐀̲𝐁̲𝐂̲𝐃̲𝐄̲𝐅̲𝐆̲𝐇̲𝐈̲𝐉̲𝐊̲𝐋̲𝐌̲𝐍̲𝐎̲𝐏̲𝐐̲𝐑̲𝐒̲𝐓̲𝐔̲𝐕̲𝐖̲𝐗̲𝐘̲𝐙̲𝟎̲𝟏̲𝟐̲𝟑̲𝟒̲𝟓̲𝟔̲𝟕̲𝟖̲𝟗̲𝐚̲̀𝐞̲̀𝐢̲̀𝐨̲̀𝐮̲̀𝐚̲́𝐞̲́𝐢̲́𝐨̲́𝐮̲́',
				'𝑎𝑏𝑐𝑑𝑒𝑓𝑔𝒉𝑖𝑗𝑘𝑙𝑚𝑛𝑜𝑝𝑞𝑟𝑠𝑡𝑢𝑣𝑤𝑥𝑦𝑧𝐴𝐵𝐶𝐷𝐸𝐹𝐺𝐻𝐼𝐽𝐾𝐿𝑀𝑁𝑂𝑃𝑄𝑅𝑆𝑇𝑈𝑉𝑊𝑋𝑌𝑍0123456789𝑎̀𝑒̀𝑖̀𝑜̀𝑢̀𝑎́𝑒́𝑖́𝑜́𝑢́',
				'𝑎̲𝑏̲𝑐̲𝑑̲𝑒̲𝑓̲𝑔̲ℎ̲𝑖̲𝑗̲𝑘̲𝑙̲𝑚̲𝑛̲𝑜̲𝑝̲𝑞̲𝑟̲𝑠̲𝑡̲𝑢̲𝑣̲𝑤̲𝑥̲𝑦̲𝑧̲𝐴̲𝐵̲𝐶̲𝐷̲𝐸̲𝐹̲𝐺̲𝐻̲𝐼̲𝐽̲𝐾̲𝐿̲𝑀̲𝑁̲𝑂̲𝑃̲𝑄̲𝑅̲𝑆̲𝑇̲𝑈̲𝑉̲𝑊̲𝑋̲𝑌̲𝑍̲0̲1̲2̲3̲4̲5̲6̲7̲8̲9̲𝑎̲̀𝑒̲̀𝑖̲̀𝑜̲̀𝑢̲̀𝑎̲́𝑒̲́𝑖̲́𝑜̲́𝑢̲́',
				'𝒂̲𝒃̲𝒄̲𝒅̲𝒆̲𝒇̲𝒈̲𝒉̲𝒊̲𝒋̲𝒌̲𝒍̲𝒎̲𝒏̲𝒐̲𝒑̲𝒒̲𝒓̲𝒔̲𝒕̲𝒖̲𝒗̲𝒘̲𝒙̲𝒚̲𝒛̲𝑨̲𝑩̲𝑪̲𝑫̲𝑬̲𝑭̲𝑮̲𝑯̲𝑰̲𝑱̲𝑲̲𝑳̲𝑴̲𝑵̲𝑶̲𝑷̲𝑸̲𝑹̲𝑺̲𝑻̲𝑼̲𝑽̲𝑾̲𝑿̲𝒀̲𝒁̲𝟎̲𝟏̲𝟐̲𝟑̲𝟒̲𝟓̲𝟔̲𝟕̲𝟖̲𝟗̲𝒂̲̀𝒆̲̀𝒊̲̀𝒐̲̀𝒖̲̀𝒂̲́𝒆̲́𝒊̲́𝒐̲́𝒖̲́',
			]
	}

}
