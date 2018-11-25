package automation.web.html.elements.widget;

import automation.web.base.elements.widget.RadioButtonImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class HTMLRadioButtonImpl extends RadioButtonImpl implements HTMLRadioButton {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLRadioButtonImpl(WebElement element) {
        super(element);
    }

    @Override
    public List<HTMLRadioButton> getAllRadioButtons() {
        String name = getAttribute("name");
        String tag = name == null ? "self::* | following::input[@type = 'radio'] | preceding::input[@type = 'radio']" :
                String.format(
                        "self::* | following::input[@type = 'radio' and @name = '%s'] | " +
                                "preceding::input[@type = 'radio' and @name = '%s']",
                        name, name);
        return Collections.singletonList((HTMLRadioButtonImpl) findElements(By.xpath(tag)));
    }

    @Override
    public void selectByText(String text) {

    }

    @Override
    public boolean isSelected(String text) {
        return false;
    }

    @Override
    public HTMLRadioButton getRadioButtonByText(String text) {
        HTMLRadioButton matchingButton = getAllRadioButtons().stream()
                .filter(b -> text.equals(getAttribute("value")))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Cannot locate radio button with value: %s", text)));
        return matchingButton;
    }

    @Override
    public HTMLRadioButton refreshElement() {
        return (HTMLRadioButton) super.refreshElement();
    }
}
