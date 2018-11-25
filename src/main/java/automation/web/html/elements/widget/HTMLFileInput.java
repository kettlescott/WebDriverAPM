package automation.web.html.elements.widget;

import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLFileInputImpl.class)
public interface HTMLFileInput extends HTMLElement {
    void setFile(String path);
}
