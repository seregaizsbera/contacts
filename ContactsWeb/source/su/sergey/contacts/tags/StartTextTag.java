package su.sergey.contacts.tags;

public class StartTextTag extends AbstractTextTag {

	/**
	 * @see AbstractTextTag#getTagName()
	 */
	protected String getTagName() {
		return "startText";
	}

	/**
	 * @see AbstractTextTag#setText(PageIteratorTag, String)
	 */
	protected void setText(PageIteratorTag pageIterator, String text) {
		pageIterator.setStartText(text);
	}
}

