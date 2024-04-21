package it.grational.text

import spock.lang.*

class CompactInternalSpacesUSpec extends Specification {

	def "Should be able to compact multiple horizontal spaces into one"() {
		given:
			String input = '''\
			|<ul>
			| <li>
			|  <strong>strong		  content</strong>
			|  <em>non-splittable   content</em>
			|  <u>underline      content</u>
			| </li>
			|</ul>'''.stripMargin()

		and:
			String expected = '''\
			|<ul>
			| <li>
			|  <strong>strong content</strong>
			|  <em>non-splittable content</em>
			|  <u>underline content</u>
			| </li>
			|</ul>'''.stripMargin()

		expect:
			new CompactInternalSpaces().filter(input) == expected
	}

}
