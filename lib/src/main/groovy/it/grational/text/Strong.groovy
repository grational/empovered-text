package it.grational.text

import java.text.Normalizer

class Strong extends Unicalc {
	@Override
	String filter(String input) {
		calibrateOffset (
			Normalizer.normalize(input, Normalizer.Form.NFD),
			new Tuple2("𝐚","a"),
			new Tuple2("𝐀", "A"),
			new Tuple2("𝟎", "0")
		)
	}
}
