package automation.web.base.utils;

import automation.web.html.utils.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigManager {

    private static ConfigManager instance ;
    private static final Logger logger = LogManager.getLogger();
    private HashMap<String,String> configValues;

    private String errorMsg = "Duplicate Keys found in configuration:\n";
    public static ConfigManager instance (){
       if (instance == null) instance  = new ConfigManager() ;
       return instance ;
    }

    private ConfigManager () {
        configValues = new HashMap<>() ;
        init();
    }

    private void init (){
        SAXBuilder builder = new SAXBuilder();
        try {
            Document xmlFile = builder.build(new File("configuration.xml"));
            Element rootNode = xmlFile.getRootElement();
            List<Element> list = rootNode.getChildren("appSettings");
            if (list.size() == 0) return;
            List<String> keys = new ArrayList<>();
            for (Element e : list.get(0).getChildren("add")) {
                keys.add(e.getAttributeValue("key"));
                String key = e.getAttributeValue("key") ;
                String value = e.getAttributeValue("value") ;
                if (configValues.containsKey(key)) {
                    errorMsg += "{" + key + "}" ;
                    logger.error(errorMsg);
                    throw new RuntimeException(errorMsg);
                }
                configValues.put(key, value);
            }
        }
        catch (JDOMException e) {
            logger.error(Helper.convertStacktrace(e));
        } catch (IOException e) {
            logger.error(Helper.convertStacktrace(e));
        }
    }



    public String getByKey (String key){
        return configValues.get(key);
    }
}
