package automation.web.html.elements.widget;



import automation.web.base.elements.base.ImplementedBy;

import java.util.HashMap;

@ImplementedBy(HTMLContentFrameImpl.class)
public interface HTMLContentFrame extends HTMLElement {



    void add();

    void edit();

    void view();

    void remove();

    HashMap<String,String> getLabelTextByContentIndex(int index);

    String getName();

    void deleteContent();

    void editContent();

    void expandContentFrame();

    void collapseContentFrame();

    void expandContentByIndex(int index);

    void collapseContentByIndex(int index);



}
