package automation.web.html.elements.widget;

import org.openqa.selenium.WebElement;

public class HTMLFileInputImpl extends HTMLElementImpl implements HTMLFileInput {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLFileInputImpl(WebElement element) {
        super(element);
    }

    @Override
    public void setFile(String path) {
        sendKeys(path);
    }

    @Override
    public HTMLFileInput refreshElement() {
        return (HTMLFileInput) super.refreshElement();
    }
}
