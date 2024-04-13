package it.grational.text

import spock.lang.*

class UnchangedUSpec extends Specification {

	def "Should simply maintain the text as is"() {
		given:
			String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú"

		and:
			String expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789àèìòùáéíóú"

		expect:
			new Unchanged().filter(input) == expected
	}

}
