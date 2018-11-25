package automation.web.html.elements.widget;

import automation.web.base.elements.widget.RadioButton;
import automation.web.base.elements.base.ImplementedBy;

import java.util.List;

@ImplementedBy(HTMLRadioButtonImpl.class)
public interface HTMLRadioButton extends RadioButton {
    List<HTMLRadioButton> getAllRadioButtons();
    void selectByText(String text);
    boolean isSelected(String text);
    HTMLRadioButton getRadioButtonByText(String text);
}
