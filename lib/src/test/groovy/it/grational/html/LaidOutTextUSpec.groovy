package it.grational.html
/*
 *                       _oo0oo_
 *                      o8888888o
 *                      88" . "88
 *                      (| -_- |)
 *                      0\  =  /0
 *                    ___/`---'\___
 *                  .' \\|     |// '.
 *                 / \\|||  :  |||// \
 *                / _||||| -:- |||||- \
 *               |   | \\\  -  /// |   |
 *               | \_|  ''\---/''  |_/ |
 *               \  .-\__  '-'  ___/-. /
 *             ___'. .'  /--.--\  `. .'___
 *          ."" '<  `.___\_<|>_/___.' >' "".
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /
 *     =====`-.____`.___ \_____/___.-`___.-'=====
 *                       `=---='
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   Buddha bless this code to make it (almost) bug-free
 */

import spock.lang.*

class LaidOutTextUSpec extends Specification {

	@Shared LaidOutText sieve = new LaidOutText()

	def "Shoul leave regular text as is"() {
		given:
			String input = "Informazioni Azienda ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C.L'azienda ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C., con partita IVA 01639160090 e codice fiscale italico 01639160090, ha come attività prevalente Servizi dei saloni di barbiere e parrucchiere ed è registrata con il codice Ateco prevalente: 96.02.01 . L'ultimo fatturato disponibile è del [anno] ed è di {fatturato}. La sua forma giuridica è Società in accomandita semplice e la sua sede legale si trova in VIA NIELLA 29 R - 17100 SAVONA, in provincia di SV.ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C. ha raggiunto un fatturato nel 2021 di € 0 e nel 2022 di € 0 con una differenza di € 0,00 La visura camerale per verificare soci, cariche e qualifiche; La visura storica, con cui ottenere tutte le modifiche che sono avvenute nel tempo;"

		when:
			String output = sieve.filter(input)

		then:
			output == input
	}

	def "Should transform a complex enriched text into its 'laid out' version"() {
		given:
			String input = """<p><strong>Informazioni Azienda <em>ENZA <u>BUTTIGLIERI</u></em> S.A.S. DI <u>VINCENZA BUTTIGLIERI E C.</u></strong></p><p>L'azienda&nbsp;ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C., con partita IVA <strong>01639160090</strong> e codice fiscale <em>italico <u>01639160090</u></em>, ha come attivit\u00e0 prevalente Servizi dei saloni di barbiere e parrucchiere ed \u00e8 registrata con il codice Ateco prevalente: 96.02.01. L'ultimo fatturato disponibile \u00e8 del [anno] ed \u00e8 di {fatturato}. La sua forma giuridica \u00e8 Societ\u00e0 in accomandita semplice e la sua sede legale si trova in VIA NIELLA 29 R - 17100 SAVONA, in provincia di SV.</p><p>ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C. <u>ha raggiunto un fatturat</u>o nel 2021 di \u20ac&nbsp;0&nbsp;e nel 2022 di \u20ac&nbsp;0&nbsp;con una differenza di \u20ac&nbsp;0,00</p><ul><li>&nbsp;La&nbsp;<a href="https://www.tuttovisure.it/camera-di-commercio/visura-camerale?param1=visura_camerale_ordine_opzioniProdotto_tipoDiVisura_0&amp;param2=visura_camerale_ordine_opzioniProdotto_visuraSu_0&amp;partitaIva=01639160090&amp;PIvaID=visura_camerale_ordine_datiProdotto_partitaIva" rel="noopener noreferrer" target="_blank"><strong>visura camerale</strong></a>&nbsp;per verificare soci, cariche e qualifiche;</li><li>&nbsp;La&nbsp;<a href="https://www.tuttovisure.it/catasto/visura-catastale?param1=visura_catastale_ordine_opzioniProdotto_visuraPer_2&amp;param2=visura_catastale_ordine_opzioniProdotto_ricerca_0&amp;codiceFiscaleID=visura_catastale_ordine_datiProdotto_codiceFiscaleAzienda&amp;codiceFiscale=01639160090" rel="noopener noreferrer" target="_blank"><strong>visura storica</strong></a>, con cui ottenere tutte le modifiche che sono avvenute nel tempo;</li></ul>"""
		and:
			String beautified = """\
			|  <p><strong>Informazioni Azienda <em>ENZA <u>BUTTIGLIERI</u></em> S.A.S. DI <u>VINCENZA BUTTIGLIERI E C.</u></strong></p>
			|  <p>L'azienda&nbsp;ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C., con partita IVA <strong>01639160090</strong> e codice fiscale <em>italico <u>01639160090</u></em>, ha come attivit\u00e0 prevalente Servizi dei saloni di barbiere e parrucchiere ed \u00e8 registrata con il codice Ateco prevalente: 96.02.01. L'ultimo fatturato disponibile \u00e8 del [anno] ed \u00e8 di {fatturato}. La sua forma giuridica \u00e8 Societ\u00e0 in accomandita semplice e la sua sede legale si trova in VIA NIELLA 29 R - 17100 SAVONA, in provincia di SV.</p>
			|  <p>ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C. <u>ha raggiunto un fatturat</u>o nel 2021 di \u20ac&nbsp;0&nbsp;e nel 2022 di \u20ac&nbsp;0&nbsp;con una differenza di \u20ac&nbsp;0,00</p>
			|  <ul>
			|    <li>&nbsp;La&nbsp;<a href="https://www.tuttovisure.it/camera-di-commercio/visura-camerale?param1=visura_camerale_ordine_opzioniProdotto_tipoDiVisura_0&amp;param2=visura_camerale_ordine_opzioniProdotto_visuraSu_0&amp;partitaIva=01639160090&amp;PIvaID=visura_camerale_ordine_datiProdotto_partitaIva/" rel="noopener" target="_blank"><strong>visura camerale</strong></a>&nbsp;per verificare soci, cariche e qualifiche;
			|    </li>
			|    <li>&nbsp;La&nbsp;<a href="https://www.tuttovisure.it/catasto/visura-catastale?param1=visura_catastale_ordine_opzioniProdotto_visuraPer_2&amp;param2=visura_catastale_ordine_opzioniProdotto_ricerca_0&amp;codiceFiscaleID=visura_catastale_ordine_datiProdotto_codiceFiscaleAzienda&amp;codiceFiscale=01639160090/" rel="noopener" target="_blank"><strong>visura storica</strong></a>, con cui ottenere tutte le modifiche che sono avvenute nel tempo;
			|    </li>
			|  </ul>""".stripMargin()

		and:
			String expected = """\
			|Informazioni Azienda ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C.
			|
			|L'azienda ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C., con partita IVA 01639160090 e codice fiscale italico 01639160090, ha come attività prevalente Servizi dei saloni di barbiere e parrucchiere ed è registrata con il codice Ateco prevalente: 96.02.01. L'ultimo fatturato disponibile è del [anno] ed è di {fatturato}. La sua forma giuridica è Società in accomandita semplice e la sua sede legale si trova in VIA NIELLA 29 R - 17100 SAVONA, in provincia di SV.
			|
			|ENZA BUTTIGLIERI S.A.S. DI VINCENZA BUTTIGLIERI E C. ha raggiunto un fatturato nel 2021 di € 0 e nel 2022 di € 0 con una differenza di € 0,00
			|
			| * La visura camerale per verificare soci, cariche e qualifiche;
			|
			| * La visura storica, con cui ottenere tutte le modifiche che sono avvenute nel tempo;""".stripMargin()

		when:
			String output = sieve.filter(input)
		then:
			output == expected

		when:
			output = sieve.filter(beautified)
		then:
			output == expected
	}
}
