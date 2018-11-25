package automation.web.base.elements.widget;

import automation.web.base.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;

/**
 * Wrapper class like Select that wraps basic checkbox functionality.
 */
public class CheckBoxImpl extends ElementImpl implements CheckBox {

    /**
     * Wraps a WebElement with checkbox functionality.
     *
     * @param element to wrap up
     */
    public CheckBoxImpl(WebElement element) {
        super(element);
    }

    public void toggle() {
        getWrappedElement().click();
    }

    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    public void select() {
        check();
    }

    public void deSelect() {
        uncheck();
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }

    @Override
    public CheckBox refreshElement() {
        return (CheckBox) super.refreshElement();
    }
}
