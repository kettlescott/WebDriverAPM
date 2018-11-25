package automation.web.base.elements.widget;

import automation.web.base.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;

public class ImageImpl extends ElementImpl implements Image {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public ImageImpl(WebElement element) {
        super(element);
    }

    public String getSource() {
        return getAttribute("src");
    }

    public String getAltText() {
        return getAttribute("alt");
    }

    @Override
    public Image refreshElement() {
        return (Image) super.refreshElement();
    }
}
