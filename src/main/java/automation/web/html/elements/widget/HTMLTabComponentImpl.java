package automation.web.html.elements.widget;

import automation.web.html.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLTabComponentImpl extends HTMLElementImpl implements HTMLTabComponent {
        private List<String> tabs = new ArrayList<>() ;

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLTabComponentImpl(WebElement element) {
        super(element);
    }

    public HTMLTabComponent tab (String tab) {
        tabs.add(tab);
        return this ;
    }

    public void click() {
        try {
            expandTab();
        } finally {
            tabs.clear();
        }
    }

    private void expandTab (){
        boolean expand = dfs (tabs, 0 , this);
        if (!expand) {
            String error = tabs.stream().collect(Collectors.joining("->"));
            Helper.error(error + " does not exists");
        }
    }

    private boolean dfs (List<String> tab , int index, HTMLTabComponentImpl root){
        if (index >= tab.size()) return true ;
        String on = " div[class=tab-strip-container] td[class=TabOn]" ;
        String off = " div[class=tab-strip-container] td[class=TabOff]";
        List<WebElement> elements = root.findElements(By.cssSelector(on));
        List<WebElement> off_element = root.findElements(By.cssSelector(off));
        elements.addAll(off_element) ;
        String cur = tab.get(index) ;
        for (WebElement ele : elements) {
            String  tabText = ele.findElement(By.cssSelector("div")).getText().trim() ;
            if (tabText.equals(cur)) {
                if (!isOn(ele)) ele.click();
                if (dfs(tab, index + 1, root.findElement(By.cssSelector("div[class=TabPanel]"), HTMLTabComponentImpl.class,true))) return true ;
                break ;
            }
        }
        if (!clickMore(root, tab.get(index))) return false ;
        if (dfs(tab, index + 1, root.findElement(By.cssSelector(" div[class=TabPanel]"), HTMLTabComponentImpl.class,true))) return  true ;
        return false ;
    }

    private boolean isOn (WebElement web){
        return "TabOn".equals(web.getAttribute("class")) ;
    }

    private boolean clickMore (WebElement root, String tab) {
        String css = " td.TabMenuArrows" ;
        List<WebElement> mores = root.findElements(By.cssSelector(css)) ;
        for (WebElement m : mores) {
            if (!m.isDisplayed()) continue;
            List<WebElement> tds = m.findElements(By.cssSelector("table[class=TabMenu] td")) ;
            for (WebElement td : tds) {
                int index = Integer.parseInt(td.getAttribute("keytipnumber")) ;
                if (td.getAttribute("alt") != null && tab.equals(td.getAttribute("alt").trim())) {
                    //click on more button to show more items
                    m.click();
                    WebElement a = this.findElement(By.cssSelector("td[keytipnumber=" + "\'" + String.valueOf(index)  + "\'" + "] a" )) ;
                    a.click();
                    return true ;
                }
            }
        }
        return false ;
    }

    @Override
    public HTMLTabComponent refreshElement() {
        return (HTMLTabComponent) super.refreshElement();
    }
}
