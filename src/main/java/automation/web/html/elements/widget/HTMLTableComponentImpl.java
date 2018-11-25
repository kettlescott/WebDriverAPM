package automation.web.html.elements.widget;


import automation.web.html.utils.SearchCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class HTMLTableComponentImpl extends HTMLElementImpl implements HTMLTableComponent {

    private static final String CHECKBOX_CSS = " div[class=ListView] input[type=checkbox][title=\"Select All\"] , div[class=ListView] input[type=checkbox]";
    private static final String HEADER_CSS = " div[class=headerTittle] table[class=ListTable] tr th";
    private static final String FOOTER_CSS = " div[class=ListNav] label[class=CurrentBlockNumber]";

    private static final String TEXT_FILTER_CSS = " div[id=PopupContainer] td[class=filterField]";
    private static final String ROW_CSS = " div[id=divListviewGrid] table[class=ListTable] tr";
    private static final String TEXT_FILTER_COMMAND_CSS = "div[id=PopupContainer] input[id$=cmdFilter]";


    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLTableComponentImpl(WebElement element) {
        super(element);
    }

    @Override
    public void filterBy (SearchCondition conditions){
        int cnt = 0 ;
        int size = conditions.getMap().size() ;
        while (cnt++ < size) {
            List<WebElement> headers = findElements(By.cssSelector(HEADER_CSS));
            for (WebElement header : headers) {
                String key = header.getText() ;
                if (conditions.getMap().containsKey(key)) {
                    header.findElements(By.tagName("a")).stream().filter( e-> e.isDisplayed()  && e.getAttribute("class").contains("fa-filter")).findFirst().get().click();
                    WebElement filterPanel = getWrappedDriver().findElements(By.cssSelector(TEXT_FILTER_CSS)).stream().filter(e -> e.isDisplayed()).findFirst().get() ;
                    setFilter(conditions.getMap().get(header.getText()), filterPanel);
                    conditions.getMap().remove(key);
                    break ;
                }
            }
        }
    }

    private void setFilter (String text, WebElement filterPanel){
        WebElement cur = filterPanel.findElements(By.xpath(".//*")).stream().filter(e -> e.isDisplayed()).findFirst().get() ;
        if (cur.getTagName().equals("input")) {
            cur.clear();
            cur.sendKeys(text);
        } else {
            Select sel = new Select(cur.findElement(By.tagName("select")));
            sel.selectByVisibleText(text);
        }
        getWrappedDriver().findElements(By.cssSelector(TEXT_FILTER_COMMAND_CSS)).stream().filter( e-> e.isDisplayed()).findFirst().get().click();
    }

    @Override
    public List<String> getHeaders (){
        return findElements(By.cssSelector(HEADER_CSS)).stream().filter(e -> e.getText().trim().length() != 0).map(e-> e.getText()).collect(Collectors.toList());
    }

    @Override
    public int getRowCount (){
        String text = findElement(By.cssSelector(FOOTER_CSS)).getText() ;
        return dfs(text, text.length() - 1) ;

    }

    private int dfs (String text, int i){
        if (i < 0) return 0 ;
        if (!Character.isDigit(text.charAt(i))) return 0 ;
        return 10 * dfs(text, i - 1) + (text.charAt(i) - '0');
    }

    @Override
    public String getSelectText(int x, int y){
        return  getCell(x, y).getText() ;
    }

    @Override
    public HTMLElement getCell (int x, int y){
        List<WebElement> rows = findElements(By.cssSelector(ROW_CSS)) ;
        if (rows.size() < x) throw new RuntimeException("row : " + x + "is out of boundary");
        List<WebElement> cols = rows.get(x).findElements(By.tagName("td")) ;
        if (cols.size() < y) throw new RuntimeException("col : " + y + "is out of boundary");
        return new HTMLElementImpl(cols.get(y));
    }

    @Override
    public int getHeaderIndex (String header){
        List<String> headers = getHeaders() ;
        for (int i = 0 ; i < headers.size(); ++i) {
            if (header.equals(headers.get(i))) return i ;
        }
        return -1 ;
    }

    @Override
    public void reset() {
        (new HTMLImageImpl(findElement(By.cssSelector("img[id$=Reset]")))).click();
    }

    @Override
    public void refresh(){
        (new HTMLImageImpl(findElement(By.cssSelector("img[id$=Refresh]")))).click();
    }

    @Override
    public HTMLLink exportListView(){
         return new HTMLLinkImpl(findElement(By.cssSelector("a[id$=ExportListview]")));
    }

    @Override
    public void select() {
        new HTMLCheckboxImpl(findElement(By.cssSelector(CHECKBOX_CSS))).select();
    }

    @Override
    public void deSelect() {
        new HTMLCheckboxImpl(findElement(By.cssSelector(CHECKBOX_CSS))).deSelect();
    }

    @Override
    public boolean isSelected() {
        return new HTMLCheckboxImpl(findElement(By.cssSelector(CHECKBOX_CSS))).isSelected();
    }

    @Override
    public HTMLTableComponent row(int index){
        List<WebElement> rows = findElements(By.cssSelector(ROW_CSS)) ;
        if (rows.size() < index) {
            throw new RuntimeException("row : " + index + "is out of boundary");
        } else {
            return new HTMLTableComponentImpl(rows.get(index));
        }
    }

    @Override
    public HTMLTabComponent refreshElement() {
        return (HTMLTabComponent) super.refreshElement();
    }
}
