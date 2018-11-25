package automation.web.html.elements.widget;


import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLTabComponentImpl.class)
public interface HTMLTabComponent extends HTMLElement {
    HTMLTabComponent tab(String tab);
}
