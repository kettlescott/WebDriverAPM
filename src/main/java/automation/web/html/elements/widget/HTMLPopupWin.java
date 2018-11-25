package automation.web.html.elements.widget;

import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLPopupWinImpl.class)
public interface HTMLPopupWin extends HTMLElement {
    void clickButtonByText(String text);
}
