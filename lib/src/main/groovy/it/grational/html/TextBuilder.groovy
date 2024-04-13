package it.grational.html

import org.jsoup.Jsoup
import org.jsoup.nodes.Node
import org.jsoup.nodes.Document
import org.jsoup.safety.Safelist
import org.jsoup.select.NodeVisitor
import it.grational.text.TextFilter

class TextBuilder implements NodeVisitor {
	private StringBuilder buffer = new StringBuilder()
	private static final String ls = System.lineSeparator()
	private final TextFilter strong
	private final TextFilter emphasis
	private final TextFilter underline
	private final TextFilter strongEmphasis
	private final String rootTag

	TextBuilder (
		TextFilter strong,
		TextFilter emphasis,
		TextFilter underline,
		TextFilter strongEmphasis,
		String rootTag = 'root'
	) {
		this.strong = strong
		this.emphasis = emphasis
		this.underline = underline
		this.strongEmphasis = strongEmphasis
		this.rootTag = rootTag
	}

	@Override
	void head(Node tag, int depth) {}

	@Override
	void tail(Node tag, int depth) {
		String name = tag.nodeName()
		if ( name.equals('strongemu') ) {
			tag.text (
				strongEmphasis.filter (
					underline.filter(tag.text())
				)
			)
		} else if ( name.equals('emu') ) {
			tag.text (
				emphasis.filter (
					underline.filter(tag.text())
				)
			)
		} else if ( name.equals('strongu') ) {
			tag.text (
				strong.filter(
					underline.filter(tag.text())
				)
			)
		} else if ( name.equals('strongem') ) {
			tag.text (
				strongEmphasis.filter(tag.text())
			)
		} else if ( name.equals('strong') ) {
			tag.text (
				strong.filter(tag.text())
			)
		} else if ( name.equals('em') ) {
			tag.text (
				emphasis.filter(tag.text())
			)
		} else if ( name.equals('u') ) {
			tag.text (
				underline.filter(tag.text())
			)
		} else if ( name.equals(rootTag) ) {
			buffer.append(tag.wholeText())
		}
	}

	@Override
	String toString() {
		return buffer.toString()
			.replaceAll(/\p{Zs}/,' ')
			.replaceAll(/ +${ls}/,ls)
			.replaceAll(/ {2,}/,' ')
			.replaceAll("([ \t]*${ls}){3,}","${ls}${ls}")
			.trim()
	}

}
