package automation.web.html.elements.widget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/*
Error message is a popup window, so there is no point initialize through annotation


 */
public class HTMLErrorMessageImpl extends HTMLElementImpl implements HTMLErrorMessage {

    private  String css = "table[id=val_message_table]" ;

    private List<WebElement> elementList ;

    private WebDriver driver ;

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLErrorMessageImpl(WebElement element) {
        super(element);
    }

    public boolean exists (){
        elementList =  driver.findElements(By.cssSelector(css)).stream().filter(e -> e.isDisplayed()).collect(Collectors.toList());
        return !elementList.isEmpty();
    }


    public List<String> getErrorMessages () {
        return  elementList.get(0).findElements(By.cssSelector("span[id^=PageMessage]")).
                stream().filter(e -> e.isDisplayed() && e.getText() != null && e.getText() .length() != 0).
                map(e -> e.getText()).collect(Collectors.toList());
    }


    public void close () {
        elementList.get(0).findElements(By.cssSelector("td[class=raiseMessage_closeMessage] a")).stream().
                filter(e -> e.isDisplayed()).findFirst().get().click();
    }

    @Override
    public HTMLErrorMessage refreshElement() {
        return (HTMLErrorMessage) super.refreshElement();
    }

}
