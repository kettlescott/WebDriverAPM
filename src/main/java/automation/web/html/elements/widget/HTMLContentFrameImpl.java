package automation.web.html.elements.widget;

import automation.web.base.page.PageSync;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class HTMLContentFrameImpl extends HTMLElementImpl implements HTMLContentFrame {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */

    public HTMLContentFrameImpl(WebElement element) {
        super(element);
    }



    @Override
    public void add() {
        this.findElement(By.cssSelector("input[id$=_cmdAdd]")).click();
    }

    @Override
    public void edit() {
        this.findElement(By.cssSelector("input[id$=_cmdEdit]")).click();
    }

    @Override
    public void view() {
        this.findElement(By.cssSelector("input[id$=_cmdView]")).click();
    }

    @Override
    public void remove() {
        this.findElement(By.cssSelector("input[id$=_cmdRemove]")).click();
    }

    @Override
    public HashMap<String,String> getLabelTextByContentIndex(int index) {
        HTMLElement root = getCurrentContent(index);
        List<WebElement> all = root.findElements(By.cssSelector("span.DataLabel"));
        Trie trie = new Trie() ;
        for (WebElement a : all) {
            if (!"".equals(a.getAttribute("id"))) {
                trie.add(a.getAttribute("id").toLowerCase(), a.getText());
            }
        }
        HashMap<String,String> result = new HashMap<>() ;
        all = root.findElements(By.cssSelector("label.TextLabel"));
        for (WebElement a : all) {
            if (!"".equals(a.getAttribute("id"))) {
              String key =   trie.search(a.getAttribute("id").toLowerCase()) ;
              if (!"".equals(key)) {
                  result.put(a.getText(),key) ;
              }
            }
        }
        return result ;
    }

    @Override
    public String getName (){
        return findElement(By.cssSelector("span.title-area span.header-title")).getText();
    }

    @Override
    public void deleteContent (){
        findElement(By.cssSelector("span[id$=deleteIcon]")).click();
    }

    @Override
    public void editContent (){
        findElement(By.cssSelector("span[id$=editIcon]")).click();
    }

    @Override
    public void expandContentFrame() {
        List<WebElement> all = this.refreshElement().findElements(By.cssSelector("span.expand-collapse"));
        if (!all.isEmpty() && isContentFrameExpandable(all.get(0))) all.get(0).click();
        PageSync.waitForJSandJQueryToLoad(getWrappedDriver());
    }

    @Override
    public void collapseContentFrame() {
        List<WebElement> all = this.refreshElement().findElements(By.cssSelector("span.expand-collapse"));
        if (!all.isEmpty() && !isContentFrameExpandable(all.get(0))) all.get(0).click();
        PageSync.waitForJSandJQueryToLoad(getWrappedDriver());
    }

    private boolean isContentFrameExpandable (WebElement element){
        WebElement ele = element.findElement(By.cssSelector("input"));
        return  "collapsed".equals(ele.getAttribute("value"));
    }

    @Override
    public void expandContentByIndex(int index) {
        HTMLElement currentContent = getCurrentContent(index) ;
        if (isExpandable(currentContent)) {
            currentContent.findElement(By.cssSelector("div.imageWrapper")).click();
        }
    }

    @Override
    public void collapseContentByIndex(int index) {
        HTMLElement currentContent = getCurrentContent(index) ;
        if (!isExpandable(currentContent)) {
            currentContent.findElement(By.cssSelector("div.imageWrapper")).click();
        }
    }

    private HTMLElement getCurrentContent (int index){
        List<WebElement> all = this.refreshElement().findElements(By.xpath("./div[contains(@class,'content')]"));
        if (index >= all.size()) {
            throw  new RuntimeException("index is out of boundary") ;
        }
        return  new HTMLElementImpl(all.get(index) );
    }

    private boolean isExpandable(HTMLElement currentContent){
         WebElement ele = currentContent.refreshElement().findElement(By.cssSelector("div.imageWrapper")) ;
         return "Expand this Section".equals(ele.getAttribute("title"));
    }

    class Trie {

       private  Node root ;

       public Trie (){
           root = new Node() ;
       }

       public void add (String id, String key){
           Node cur = root ;
           for (char c : id.toCharArray()) {
               if (cur.sub[c] == null) {
                   cur.sub[c] = new Node() ;
               }
               cur = cur.sub[c] ;
           }
           cur.key = key ;
           cur.match = true ;
       }

       public String search (String id){
           Node cur = root ;
           for (char c : id.toCharArray()) {
               if (cur.match) return cur.key ;
               if (cur.sub[c] == null) break;
               cur = cur.sub[c] ;
           }
           return "";
       }


    }

    class Node {
        Node [] sub = new Node [256] ;
        String key ;
        boolean match ;
    }


}
