package automation.web.html.elements.widget;

import automation.web.base.elements.base.ImplementedBy;
import org.openqa.selenium.WebElement;



@ImplementedBy(HTMLContextMenuImpl.class)
public interface HTMLContextMenu extends HTMLElement {
    HTMLContextMenu menuItem(Object menuName);

    void click();

    WebElement getMenuElement (String  path);
}
