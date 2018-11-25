package automation.web.html.elements.widget;


import automation.web.base.elements.widget.LabelImpl;
import org.openqa.selenium.*;

public class HTMLLabelImpl extends LabelImpl implements HTMLLabel {

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLLabelImpl(WebElement element) {
        super(element);
    }


    @Override
    public HTMLLabelImpl refreshElement() {
        return (HTMLLabelImpl) super.refreshElement();
    }

    @Override
    public String getLabelText() {
        return this.getText() ;
    }
}
