package it.grational.html

import it.grational.text.*
import java.text.Normalizer

class Strong extends GeneralTagConverter {
	private final TagConverter origin

	Strong(TagConverter origin = new NoConversion()) {
		this.origin = origin
	}

	@Override
	protected TagConverter origin() { this.origin }

	@Override
	protected List<String> tagNames() { [ 'strong', 'bold', 'b' ] }

	@Override
	protected String convert(String input) {
		new BoldifyItalicH (
			new BoldifyItalicLetters (
				new BoldifyDigits (
					new BoldifyUpperLetters (
						new BoldifyLowerLetters()
					)
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
