package automation.web.html.elements.widget;

import automation.web.base.elements.widget.TextInputImpl;
import org.openqa.selenium.WebElement;

public class HTMLTextInputImpl extends TextInputImpl implements HTMLTextInput {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLTextInputImpl(WebElement element) {
        super(element);
    }

    @Override
    public String getText() {
        if ("textarea".equals(getTagName())) {
            return getText();
        } else {
            String text = getAttribute("value");
            return text == null ? "" : text;
        }
    }

    @Override
    public void set(String text) {
        sendKeys(text);
    }

    @Override
    public HTMLTextInput refreshElement() {
        return (HTMLTextInput) super.refreshElement();
    }
}
