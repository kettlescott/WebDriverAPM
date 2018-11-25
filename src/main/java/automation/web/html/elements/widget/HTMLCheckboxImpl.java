package automation.web.html.elements.widget;

import automation.web.base.elements.widget.CheckBoxImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HTMLCheckboxImpl extends CheckBoxImpl implements HTMLCheckbox {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLCheckboxImpl(WebElement element) {
        super(element);
    }

    @Override
    public String getLabel() {
        String name = getText();
        String tag = name == null ? "self::* | following-sibling::label | preceding-sibling::label" :
                String.format("self::* | following-sibling::label | preceding-sibling::label", name, name);
        return findElement(By.xpath(tag)).getText();
    }

    @Override
    public HTMLCheckbox refreshElement() {
        return (HTMLCheckbox) super.refreshElement();
    }
}
