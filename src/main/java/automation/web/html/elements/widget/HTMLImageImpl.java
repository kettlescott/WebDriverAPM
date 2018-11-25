package automation.web.html.elements.widget;

import automation.web.base.elements.widget.ImageImpl;
import org.openqa.selenium.WebElement;

public class HTMLImageImpl extends ImageImpl implements HTMLImage {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLImageImpl(WebElement element) {
        super(element);
    }

    @Override
    public HTMLImage refreshElement() {
        return (HTMLImage) super.refreshElement();
    }
}
