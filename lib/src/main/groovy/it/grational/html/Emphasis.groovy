package it.grational.html

import it.grational.text.*
import java.text.Normalizer

class Emphasis extends GeneralTagConverter {
	private final TagConverter origin

	Emphasis(TagConverter origin = new NoConversion()) {
		this.origin = origin
	}

	@Override
	protected TagConverter origin() { this.origin }

	@Override
	protected List<String> tagNames() { [ 'em', 'italic', 'i' ] }

	@Override
	protected String convert(String input) {
		new ItalifyBoldLetters (
			new ItalifyUpperLetters (
				new ItalifyLowerH (
					new ItalifyLowerLetters()
				)
			)
		).filter (
			Normalizer.normalize (
				input,
				Normalizer.Form.NFD
			)
		)
	}
}
