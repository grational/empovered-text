package it.grational.html

import static java.lang.Character.*

class Underline extends AppendCombining { // {{{
	static final private int lowLineCode = 818

	private final TagConverter origin

	Underline(TagConverter origin = new NoConversion()) {
		this.origin = origin
	}

	@Override
	protected TagConverter origin() { this.origin }

	@Override
	protected List<String> tagNames() { [ 'u', 'ins' ] }

	@Override
	protected int combiningCode() { lowLineCode }

} // }}}
