package it.grational.html

import static java.lang.Character.*

class Delete extends AppendCombining { // {{{
	static final private int lowLineCode = 822

	private final TagConverter origin

	Delete(TagConverter origin = new NoConversion()) {
		this.origin = origin
	}

	@Override
	protected TagConverter origin() { this.origin }

	@Override
	protected List<String> tagNames() { [ 'del', 'strike', 's' ] }

	@Override
	protected int combiningCode() { lowLineCode }

} // }}}
