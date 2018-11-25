package automation.web.base.elements.widget;

import automation.web.base.elements.base.Element;
import automation.web.base.elements.base.ImplementedBy;

/**
 * Text field functionality.
 */
@ImplementedBy(TextInputImpl.class)
public interface TextInput extends Element {
    /**
     * @param text The text to type into the field.
     */
    void set(String text);
}
