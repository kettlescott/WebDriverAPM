package automation.web.base.elements.widget;

import automation.web.base.elements.base.Element;
import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(ImageImpl.class)
public interface Image extends Element {
    String getSource();
    String getAltText();
}
