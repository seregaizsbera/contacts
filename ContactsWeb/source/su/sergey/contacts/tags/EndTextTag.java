package su.sergey.contacts.tags;

public class EndTextTag extends AbstractTextTag {

	/**
	 * @see AbstractTextTag#getTagName()
	 */
	protected String getTagName() {
		return "endText";
	}

	/**
	 * @see AbstractTextTag#setText(PageIteratorTag, String)
	 */
	protected void setText(PageIteratorTag pageIterator, String text) {
		pageIterator.setEndText(text);
	}
}
