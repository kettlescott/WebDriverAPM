package automation.web.html.elements.widget;

import automation.web.base.elements.base.Element;
import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLElementImpl.class)
public interface HTMLElement extends Element {
    boolean isReady();
    boolean exists();
    HTMLElement refreshElement();
}
