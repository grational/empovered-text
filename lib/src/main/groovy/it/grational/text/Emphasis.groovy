package it.grational.text

import java.text.Normalizer

class Emphasis extends Unicalc {
	@Override
	String filter(String input) {
		calibrateOffset (
			Normalizer.normalize (
				this.switchHForPlanckConstant(input),
				Normalizer.Form.NFD
			),
			new Tuple2("𝑎","a"),
			new Tuple2("𝐴","A"),
			new Tuple2("0","0")
		)
	}

	private String switchHForPlanckConstant(String input) {
		input.replaceAll('h','ℎ')
	}

}
