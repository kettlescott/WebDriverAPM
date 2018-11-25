package automation.web.html.elements.widget;

import automation.web.base.elements.widget.CheckBox;
import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLCheckboxImpl.class)
public interface HTMLCheckbox extends CheckBox {
    String getLabel();
}
