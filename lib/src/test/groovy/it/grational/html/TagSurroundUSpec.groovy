package it.grational.html

import spock.lang.*
import static support.TestSet.*
import org.jsoup.nodes.Node

class TagSurroundUSpec extends Specification {

	@Shared String tagName = 'p'
	@Shared String ls = System.lineSeparator()

	def "Should surround a paragraph tagged text with newlines"() {
		given:
			Node tagMock = Mock(Node)

		when:
			new TagSurround (
				tagName,
				ls
			).convert(tagMock)

		then:
			1 * tagMock.nodeName() >> tagName
			1 * tagMock.before(ls) >> tagMock
			1 * tagMock.after(ls) >> tagMock
	}

	def "Should NOT surround a paragraph when the tagName is not corresponding"() {
		given:
			Node tagMock = Mock(Node)

		when:
			new TagSurround (
				'anotherTag',
				ls
			).convert(tagMock)

		then:
			1 * tagMock.nodeName() >> tagName
			0 * tagMock.before(ls)
			0 * tagMock.after(ls)
	}

}
