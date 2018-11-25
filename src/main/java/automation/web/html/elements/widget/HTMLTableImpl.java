package automation.web.html.elements.widget;

import automation.web.base.elements.widget.TableImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HTMLTableImpl extends TableImpl implements HTMLTable {

    /**
     * Creates a Table for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLTableImpl(WebElement element) {
        super(element);
    }

    @Override
    public int getRowCount() {
        return getRows().size();
    }

    @Override
    public int getColumnCount() {

        return findElement(By.cssSelector("tr")).findElements(
                By.cssSelector("*")).size();
        // Would ideally do:
        // return findElements(By.cssSelector("tr:first-of-type > *"));
        // however HTMLUnitDriver does not support CSS3
    }

    @Override
    public WebElement getCellAtIndex(int rowIdx, int colIdx) {
        // Get the row atPage the specified index
        WebElement row = getRows().get(rowIdx);

        List<WebElement> cells;

        // Cells are most likely to be td tags
        cells = row.findElements(By.tagName("td"));
        if (!cells.isEmpty()) {
            return cells.get(colIdx);
        } else {
            // Failing that try th tags
            cells = row.findElements(By.tagName("th"));
            if (!cells.isEmpty()) {
                return cells.get(colIdx);
            } else {
                final String error = String
                        .format("Could not find cell atPage row: %s column: %s",
                                rowIdx, colIdx);
                throw new ElementNotVisibleException(error);
            }
        }
    }

    /**
     * Gets all rows in the table in order header > body > footer
     *
     * @return list of row WebElements
     */
    private List<WebElement> getRows() {
        List<WebElement> rows = new ArrayList<>();

        //Header rows
        rows.addAll(findElements(By.cssSelector("thead tr")));

        //Body rows
        rows.addAll(findElements(By.cssSelector("tbody tr")));

        //Footer rows
        rows.addAll(findElements(By.cssSelector("tfoot tr")));

        return rows;
    }

    @Override
    public HTMLTable refreshElement() {
        return (HTMLTable) super.refreshElement();
    }

}
