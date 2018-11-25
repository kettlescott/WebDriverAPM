package automation.web.html.elements.widget;

import automation.web.base.elements.widget.TextInput;
import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLTextInputImpl.class)
public interface HTMLTextInput extends TextInput {
    void set(String text);
}
