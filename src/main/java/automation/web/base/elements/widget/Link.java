package automation.web.base.elements.widget;

import automation.web.base.elements.base.Element;
import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(LinkImpl.class)
public interface Link extends Element {
    String getLinkReference();
}
