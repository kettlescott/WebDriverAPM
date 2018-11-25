package automation.web.base.elements.utils;

import org.openqa.selenium.By;

public class Locator {
    By by;

    public Locator(FoundBy foundBy, String using) {
        this.by = setBy(foundBy, using);
    }

    private By setBy(FoundBy foundBy, String using) {
        switch (foundBy) {
            case ID:
                return By.id(using);
            case LINK_TEXT:
                return By.linkText(using);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(using);
            case TAG_NAME:
                return By.tagName(using);
            case NAME:
                return By.name(using);
            case CLASS_NAME:
                return By.className(using);
            case CSS_SELECTOR:
                return By.cssSelector(using);
            case XPATH:
                return By.xpath(using);
            default:
                return null;
        }
    }

    public By getBy() {
        return by;
    }
}
