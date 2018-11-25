package automation.web.html.elements.widget;

import automation.web.html.utils.SearchCondition;
import automation.web.base.elements.base.ImplementedBy;

import java.util.List;

@ImplementedBy(HTMLTableComponentImpl.class)
public interface HTMLTableComponent extends HTMLElement {
    List<String> getHeaders();
    void filterBy(SearchCondition searchCondition);
    int getRowCount();
    HTMLTableComponent row(int row);
    HTMLElement getCell (int x, int y);
    String getSelectText(int x, int y);
    int getHeaderIndex(String header);
    void reset();
    void refresh();
    void select();
    void deSelect();
    boolean isSelected();
    HTMLLink exportListView();
}
