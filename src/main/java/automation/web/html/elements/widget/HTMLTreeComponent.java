package automation.web.html.elements.widget;

import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLTreeComponentImpl.class)
public interface HTMLTreeComponent extends HTMLElement {
    void expand ();

    HTMLTreeComponent treePath (String path);

    HTMLTreeComponent withBinarySearch ();
}
