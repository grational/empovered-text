package it.grational.text

import java.text.Normalizer

class StrongEmphasis extends Unicalc {
	@Override
	String filter(String input) {
		calibrateOffset (
			Normalizer.normalize(input, Normalizer.Form.NFD),
			new Tuple2("𝒂", "a"),
			new Tuple2("𝑨", "A"),
			new Tuple2("𝟎", "0")
		)
	}
}
