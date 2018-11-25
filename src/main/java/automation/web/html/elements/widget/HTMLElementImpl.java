package automation.web.html.elements.widget;

import automation.web.base.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;

public class HTMLElementImpl extends ElementImpl implements HTMLElement {


    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLElementImpl(WebElement element) {
        super(element);
    }

    @Override
    public boolean isReady() {
        return elementWired();
    }

    @Override
    public boolean exists() {
        return elementWired();
    }

    @Override
    public HTMLElement refreshElement() {
        return (HTMLElement) super.refreshElement();
    }
}
