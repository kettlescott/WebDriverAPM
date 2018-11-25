package automation.web.html.elements.widget;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class HTMLMenuPanelImpl extends HTMLElementImpl implements HTMLMenuPanel {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    private static final Logger logger = LogManager.getLogger();

    public HTMLMenuPanelImpl(WebElement element) {
        super(element);
    }


    public HTMLMenuPanelImpl home() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=Homelink]")));
    }


    public HTMLMenuPanelImpl tasks() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=Taskslink]")));
    }


    public HTMLMenuPanelImpl createNewClaim() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=CreateClaimlink]")));
    }


    public HTMLMenuPanelImpl searchParty() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=SearchPartieslink]")));
    }


    public HTMLMenuPanelImpl claims() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=SearchCaseslink]")));
    }


    public HTMLMenuPanelImpl viewClaims() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=MyClaimslink]")));
    }


    public HTMLMenuPanelImpl viewClaimsPortfolio() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=CasePortfoliolink]")));
    }


    public HTMLMenuPanelImpl archiveClaims() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=ArchiveClaimlink]")));
    }


    public HTMLMenuPanelImpl lookupEntitlementTypes() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=EntitlementTypelink]")));
    }


    public HTMLMenuPanelImpl chips() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=Chipslink]")));
    }


    public HTMLMenuPanelImpl help() {
        return new HTMLMenuPanelImpl(findElement(By.cssSelector(" a[id$=Helplink]")));
    }


    @Override
    public void click (){
        String property = this.getAttribute("class").length() != 0 ?
                this.getAttribute("class") : this.getAttribute("id");
        super.click();
        logger.debug("[APM]{NODE:LINK_" + property + ", TIME :" + System.nanoTime() + "};");
    }

    @Override
    public HTMLMenuPanel refreshElement() {
        return (HTMLMenuPanel) super.refreshElement();
    }
}
