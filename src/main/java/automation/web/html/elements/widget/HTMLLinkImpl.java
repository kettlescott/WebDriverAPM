package automation.web.html.elements.widget;

import automation.web.base.elements.widget.LinkImpl;
import org.openqa.selenium.WebElement;

public class HTMLLinkImpl extends LinkImpl implements HTMLLink {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLLinkImpl(WebElement element) {
        super(element);
    }

    @Override
    public HTMLLink refreshElement() {
        return (HTMLLink) super.refreshElement();
    }
}
