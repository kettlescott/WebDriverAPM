package automation.web.html.elements.widget;

import automation.web.html.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/*
This component will not support @findBy as it probably generate stale element exception exception after DOM renders
 */
public class HTMLContextMenuImpl extends HTMLElementImpl implements HTMLContextMenu {

    protected WebDriver driver ;

    private List<Object> items ;

    private HashMap<String,ElementEqual> conditions ;

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLContextMenuImpl(WebElement element) {
        super(element);
        items = new ArrayList<>() ;
        conditions = new HashMap<>() ;
        init ();
    }

    public HTMLContextMenu menuItem (Object text){
        items.add(text) ;
        return this ;
    }


    public void click (){
        try {
            boolean status = doSelect(items, 0 ,this) ;
            if (!status) {
                String error = items.stream().map(it -> it.toString()).collect(Collectors.joining("->"));
                Helper.error("menu items path " + error + " does not exists");
            }
        } finally {
            items.clear();
        }
    }


    private boolean doSelect (List<Object> items, int index, HTMLContextMenuImpl root){
        List<WebElement> menu = root.findElements(By.cssSelector("li span.menuItemContainer > a"));
        Object cur = items.get(index) ;
        int count = 0;
        for (int i = 0 ; i < menu.size() ; ++i) {
            if (!menu.get(i).isDisplayed()) continue;
            if (conditions.get("STRING").isEquals(menu,i, cur) ||
                    conditions.get("INTEGER").isEquals(menu,count + 1, cur)) {
                menu.get(i).click();
                if (index + 1 >= items.size()) return true ;
                return doSelect(items, index + 1, root.findElement(By.cssSelector("li > div.drop ul"), HTMLContextMenuImpl.class, true)) ;
            }
            count++;
        }
        return  false ;
    }


    public WebElement getMenuElement (String  path) {
        path += " span.LinkText" ;
        return driver.findElement(By.cssSelector(path)) ;
    }

    @Override
    public HTMLContextMenu refreshElement() {
        return (HTMLContextMenu) super.refreshElement();
    }

    interface ElementEqual {
        boolean isEquals (List<WebElement> elements, int index, Object expected) ;
    }

    private void init (){
        conditions.put("STRING", (List<WebElement> elements, int index, Object expected) ->
                ((expected instanceof String)) &&
                        elements.get(index).getText().equals(expected));

        conditions.put("INTEGER", (List<WebElement> element, int index, Object expected) ->
                ((expected instanceof Integer)) && index == (Integer)expected
        );
    }
}
