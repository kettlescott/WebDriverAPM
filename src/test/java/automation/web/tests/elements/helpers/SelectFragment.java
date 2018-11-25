/**
 * 
 */
package automation.web.tests.elements.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import automation.web.base.elements.base.Element;
import automation.web.base.elements.base.ImplementedBy;
import automation.web.base.elements.widget.Select;

@ImplementedBy(SelectFragmentImpl.class)
public interface SelectFragment extends Element {
    
    final String ID_LOCATOR = "options";
    
    Select getOption1();
    
    WebElement getSubElement(By by);

}
