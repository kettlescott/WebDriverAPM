package automation.web.html.elements.widget;

import automation.web.base.elements.widget.ButtonImpl;
import org.openqa.selenium.WebElement;

public class HTMLButtonImpl extends ButtonImpl implements HTMLButton {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLButtonImpl(WebElement element) {
        super(element);
    }

    @Override
    public HTMLButton refreshElement() {
        return (HTMLButton) super.refreshElement();
    }
}
