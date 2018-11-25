package automation.web.html.elements.widget;

import automation.web.html.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HTMLPopupWinImpl extends HTMLElementImpl implements HTMLPopupWin {

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLPopupWinImpl(WebElement element) {
        super(element);
    }

    public void clickButtonByText (String  text){
        Helper.checkExists(exists(), "Popup window does not exists");
        findElements(By.cssSelector("div[class=PopupContainer] input")).stream().filter(e -> e.isDisplayed() && text.equals(e.getAttribute("value"))).findFirst().get().click();
    }

    @Override
    public HTMLPopupWin refreshElement() {
        return (HTMLPopupWin) super.refreshElement();
    }
}
