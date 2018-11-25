package automation.web.html.elements.widget;

import automation.web.base.elements.widget.Label;
import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLLabel.class)
public interface HTMLLabel extends Label {

     String getLabelText();
}
