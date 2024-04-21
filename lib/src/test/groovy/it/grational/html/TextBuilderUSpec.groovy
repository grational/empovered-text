package it.grational.html

import spock.lang.*
import org.jsoup.nodes.Element
import it.grational.html.TagConverter

// https://en.wikipedia.org/wiki/Mathematical_Alphanumeric_Symbols
class TextBuilderUSpec extends Specification {

	@Shared String ls = System.lineSeparator()
	@Shared String strongTag = 'strong'
	@Shared String emphasisTag = 'em'
	@Shared String underlineTag = 'u'
	@Shared String defaultRootTag = 'body'

	@Shared String content = 'content'
	@Shared String underlineContent = 'c̲o̲n̲t̲e̲n̲t̲'
	@Shared String emphasisContent = '𝑐𝑜𝑛𝑡𝑒𝑛𝑡'
	@Shared String emphasisUnderlineContent = '𝑐̲𝑜̲𝑛̲𝑡̲𝑒̲𝑛̲𝑡̲'
	@Shared String strongContent = '𝐜𝐨𝐧𝐭𝐞𝐧𝐭'
	@Shared String strongEmphasisContent = '𝒄𝒐𝒏𝒕𝒆𝒏𝒕'
	@Shared String strongUnderlineContent = '𝐜̲𝐨̲𝐧̲𝐭̲𝐞̲𝐧̲𝐭̲'
	@Shared String strongEmphasisUnderlineContent = '𝒄̲𝒐̲𝒏̲𝒕̲𝒆̲𝒏̲𝒕̲'

	@Shared TagConverter tagConvertersChain

	@Shared TextBuilder textBuilder

	def setupSpec() {
		tagConvertersChain = Stub() {
			convert(_ as Element) >> { Element tag ->
				
				String result
				if ( tag.nodeName() == strongTag ) {
					switch(tag.text()) {
						case content:
							result = strongContent; break
						case underlineContent:
							result = strongUnderlineContent; break
						case emphasisContent:
							result = strongEmphasisContent; break
						case emphasisUnderlineContent:
							result = strongEmphasisUnderlineContent; break
						default:
							result = tag.text()
					}
				}

				if ( tag.nodeName() == emphasisTag ) {
					switch(tag.text()) {
						case content:
							result = emphasisContent; break
						case underlineContent:
							result = emphasisUnderlineContent; break
						case strongContent:
							result = strongEmphasisContent; break
						case strongUnderlineContent:
							result = strongEmphasisUnderlineContent; break
						default:
							result = tag.text()
					}
				}

				if ( tag.nodeName() == underlineTag ) {
					switch(tag.text()) {
						case content:
							result = underlineContent; break
						case emphasisContent:
							result = emphasisUnderlineContent; break
						case strongContent:
							result = strongUnderlineContent; break
						case strongEmphasisContent:
							result = strongEmphasisUnderlineContent; break
						default:
							result = tag.text()
					}
				}

				if ( result ) tag.text(result)
			}
		}

		textBuilder = new TextBuilder (
			tagConvertersChain
		)
	}

	@Unroll
	def "Should be capable of altering the text inside the supported html tags"() {
		given:
			Element tag = Mock(Element) {
				nodeName() >> tagName
				text() >> tagContent
			}

		when:
			textBuilder.tail(tag, 0)

		then:
			1 * tag.text(expected)

		where:
			tagName      | tagContent               || expected
			strongTag    | content                  || strongContent
			strongTag    | strongContent            || strongContent
			strongTag    | emphasisContent          || strongEmphasisContent
			strongTag    | underlineContent         || strongUnderlineContent
			strongTag    | strongEmphasisContent    || strongEmphasisContent
			strongTag    | strongUnderlineContent   || strongUnderlineContent
			strongTag    | emphasisUnderlineContent || strongEmphasisUnderlineContent
			emphasisTag  | content                  || emphasisContent
			emphasisTag  | strongContent            || strongEmphasisContent
			emphasisTag  | emphasisContent          || emphasisContent
			emphasisTag  | underlineContent         || emphasisUnderlineContent
			emphasisTag  | strongEmphasisContent    || strongEmphasisContent
			emphasisTag  | strongUnderlineContent   || strongEmphasisUnderlineContent
			emphasisTag  | emphasisUnderlineContent || emphasisUnderlineContent
			underlineTag | content                  || underlineContent
			underlineTag | strongContent            || strongUnderlineContent
			underlineTag | emphasisContent          || emphasisUnderlineContent
			underlineTag | underlineContent         || underlineContent
			underlineTag | strongEmphasisContent    || strongEmphasisUnderlineContent
			underlineTag | strongUnderlineContent   || strongUnderlineContent
			underlineTag | emphasisUnderlineContent || emphasisUnderlineContent
	}

}
