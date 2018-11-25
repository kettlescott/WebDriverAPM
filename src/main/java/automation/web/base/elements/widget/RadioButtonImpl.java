package automation.web.base.elements.widget;

import automation.web.base.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;

public class RadioButtonImpl extends ElementImpl implements RadioButton {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public RadioButtonImpl(WebElement element) {
        super(element);
    }

    @Override
    public RadioButton refreshElement() {
        return (RadioButton) super.refreshElement();
    }
}
