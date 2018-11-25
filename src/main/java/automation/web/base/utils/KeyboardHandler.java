package automation.web.base.utils;


import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;

/*
only use, in case the built in webdriver sendkeys does not work for you
 */
public class KeyboardHandler {


    private Robot robot ;

    public KeyboardHandler(){
        try {
            robot = new Robot() ;
        } catch (AWTException e) {}
    }



    public void sendKeys(String keys){
          copy(keys);
          paste();
    }


    public void sendKeyCombination (int [] keys) {
        for (int i = 0 ; i < keys.length ; ++i) robot.keyPress(keys[i]);
        for (int i = keys.length - 1 ; i >= 0 ; --i) robot.keyRelease(keys[i]);
    }


    public void copy (String text){
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new StringSelection(""), null);
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
    }

    public void paste (){
        sendKeyCombination(new int [] {KeyEvent.VK_CONTROL,KeyEvent.VK_V});
    }



}
