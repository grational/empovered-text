package it.grational.text

import spock.lang.*

class RemoveTrailingSpacesUSpec extends Specification {

	def "Should be able to remove trailing spaces"() {
		given:
			String input = '''\
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
			|</ul>'''.stripMargin()

		and:
			String expected = '''\
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
			|</ul>'''.stripMargin()

		expect:
			new RemoveTrailingSpaces().filter(input) == expected
	}

}
