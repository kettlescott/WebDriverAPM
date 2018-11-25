package automation.web.base.page;

import automation.web.base.elements.factory.api.ElementFactory;
import automation.web.html.pageobject.Page;
import automation.web.html.utils.Helper;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class PageVisitor {
    private HashMap<String, Page> cache ;

    private WebDriver driver;


    public PageVisitor(WebDriver driver) {
        cache = new HashMap<>() ;
        this.driver = driver ;
    }

    public  <T> T visit (Class<T> c, Class [] types, Object [] objects) {
        try {
            Page page = cache.getOrDefault(c.getSimpleName(), null) ;
            if (page == null) {
                Object obj = ElementFactory.initElements(driver,c, types, copyObjects(objects));
                if (!(obj instanceof Page)) {
                    Helper.error("Class : " + c.getSimpleName() + " is not type of Page");
                }
                page = (Page) obj ;
            }
            if (page.exists()) {
                cache.put(c.getSimpleName(), page);
                return c.cast(page) ;
            }
            Helper.error("Page " + c.getSimpleName() + " does not exist");
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
        throw  new RuntimeException("creating page object {" + c.getSimpleName() + "}" + " error") ;
    }

    public void clearCache (){
        cache.clear();
    }


    private Object [] copyObjects (Object [] objects){
        if (objects.length == 0) return new Object[] {driver} ;
        Object [] args = new Object[objects.length + 1] ;
        args[0] = driver ;
        System.arraycopy(objects,0, args, 1,objects.length);
        return  args ;
    }
}
