package it.grational.text

import spock.lang.*

class CompactEmptyLinesUSpec extends Specification {

	def "Should be able to compact multiple horizontal spaces into one"() {
		given:
			String input = '''\
			|
			|
			|<ul>
			| <li>
			|  <strong>strong content</strong>
			|  
			|  
			|  <em>emphasis content</em>
			|		
			|		
			|  <u>underline content</u>
			| </li>
			|</ul>
			|
			|
			|
			|'''.stripMargin()

		and:
			String expected = '''\
			|
			|
			|<ul>
			| <li>
			|  <strong>strong content</strong>
			|
			|  <em>emphasis content</em>
			|
			|  <u>underline content</u>
			| </li>
			|</ul>
			|
			|'''.stripMargin()

		expect:
			new CompactEmptyLines().filter(input) == expected
	}

}
