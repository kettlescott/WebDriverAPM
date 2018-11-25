package automation.web.html.elements.widget;



import java.util.List;


public interface HTMLErrorMessage extends HTMLElement {
    List<String> getErrorMessages();
    void close();
    boolean exists();
}
