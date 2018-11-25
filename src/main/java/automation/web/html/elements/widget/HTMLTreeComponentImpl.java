package automation.web.html.elements.widget;


import automation.web.html.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HTMLTreeComponentImpl extends HTMLElementImpl implements HTMLTreeComponent {

    private List<String> treePath ;

    private boolean binarySearch ;

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public HTMLTreeComponentImpl(WebElement element) {
        super(element);
        treePath = new ArrayList<>() ;
    }




    public HTMLTreeComponentImpl treePath (String path){
        treePath.add(path) ;
        return this ;
    }

    //if the tree node is sorted ,then you can use binary search
    //
    public HTMLTreeComponentImpl withBinarySearch (){
        this.binarySearch = true ;
        return this ;
    }

    public void expand () {
        List<String> tracker = new ArrayList<>();
        expandTreeview (tracker);
        List<WebElement> elements = refreshElement().findElements(
                By.cssSelector("div[id=" + tracker.get(tracker.size() - 1) + "] span.TreeNodeElement"));
        if (elements.size() != 0) {
            elements.get(elements.size() - 1).click();
        }
    }




    private void expandTreeview (List<String> tracker){
        try {
            if (!expand(treePath, tracker)) {
                String error = treePath.stream().collect(Collectors.joining("->"));
                Helper.error(error + " does not exists");
            }
        } finally {
            treePath.clear();
        }
    }




    private boolean expand (List<String> treePath, List<String> tracker){
           Stack<String> stack = new Stack<>();
           int index = 1 ;
           String root = findRootID(treePath.get(0));
           if ("".equals(root)) return false ;
           expandNode("div[id=" + root + "]");
           stack.push(root);
           tracker.add(root) ;
           boolean found ;
           while (!stack.isEmpty() && index < treePath.size()){
               String cur = stack.pop() ;
               found = false ;
               String child_tag = cur + "_children" ;
               List<WebElement> children = refreshElement().findElements(By.cssSelector("span[id=" + child_tag
                + "] > div.TreeNodeContainer"));
               for (WebElement e : children) {
                   String id = e.getAttribute("id") ;
                   String text = getNodeText("div[id=" + id + "]" + " span.TreeNode > span.TreeNodeElement ") ;
                  if (treePath.get(index).equals(text)) {
                      expandNode("div[id=" + id + "]");
                      stack.push(id) ;
                      tracker.add(id);
                      found = true ;
                      break;
                  }
               }
               if (!found) return false ;
               index++;
           }
           return true ;
    }


    private String findRootID (String root){
        List<WebElement> elements = this.findElements(By.cssSelector("div[class=TreeRootContainer]"));
        if (elements.size() != 0) {
           String id = elements.get(0).getAttribute("id") ;
           return root.equals(getNodeText("div[id=" + id + "]" + " span").trim()) ? id : "" ;
        }
        elements = this.findElements(By.cssSelector("div[class=TreeNodeContainer]"));
        return binarySearch ? binarySearch(elements, root) : searchRoot(elements,root) ;
    }


    /*
    this is to handle if the tree has not root and by default the root text is sorted
    default java binary search does not work for this ,so  developed my own
     */
    private String binarySearch ( List<WebElement> elements ,String target){
       int lo = 0 , hi = elements.size() -1 ;
       while (lo + 1 < hi) {
           int mid = (hi + lo) >> 1 ;
           String id = elements.get(mid).getAttribute("id") ;
           String text = getNodeText("div[id=" + id + "]" + " span.TreeNode > span.TreeNodeElement ") ;
           int compare = target.compareTo(text);
           if (compare < 0) {
              hi = mid ;
           } else {
              lo = mid ;
           }
       }
       if (target.equals( getNodeText("div[id=" + elements.get(lo).getAttribute("id") + "]" + " span.TreeNode > span.TreeNodeElement "))) {
           return elements.get(lo).getAttribute("id") ;
       } else if (target.equals( getNodeText("div[id=" + elements.get(hi).getAttribute("id") + "]" + " span.TreeNode > span.TreeNodeElement "))) {
           return elements.get(hi).getAttribute("id") ;
       } else {
           return "" ;
       }
    }
    
    private String searchRoot (List<WebElement> elements ,String target){
        for (WebElement element : elements) {
            if (!element.isDisplayed()) continue;
            String id = element.getAttribute("id") ;
            String text =  getNodeText("div[id=" + id + "] span.TreeNode > span.TreeNodeElement ");
            if (target.equals(text)) return  id ;
        }
        return "";
    }

    private String getNodeText(String path){
        List<WebElement> elements = refreshElement().findElements(By.cssSelector(path)) ;
        if (elements.size() == 0) return  "" ;
        for (WebElement element : elements) {
            if (!"".equals(element.getText())) return  element.getText() ;
        }
        return  "" ;
    }

    private boolean isExpandable (String path){
        return "Expand".equals(refreshElement().findElement(By.cssSelector(path + " > span[id=nodeHandle] > img")).getAttribute("title"));
    }

    private void expandNode (String path) {
        if (isExpandable(path)) {
            refreshElement().findElement(By.cssSelector(path + " > span[id=nodeHandle] > img")).click();
            while (!(Boolean) ((JavascriptExecutor)(getWrappedDriver())).executeScript("return jQuery.active==0")) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (Exception e) {}
            }
        }
    }


    @Override
    public HTMLTreeComponentImpl refreshElement() {
        return (HTMLTreeComponentImpl) super.refreshElement();
    }
}
