package automation.web.html.utils;

import java.util.HashMap;


public class SearchCondition {

    HashMap<String,String> map ;

    public SearchCondition(){
        map = new HashMap<>() ;
    }

    public SearchCondition addConditions (String title, String val){
        if (map.containsKey(title)) return  this;
        map.put(title,val) ;
        return this ;
    }

    public HashMap<String,String> getMap (){
        return map ;
    }
}
