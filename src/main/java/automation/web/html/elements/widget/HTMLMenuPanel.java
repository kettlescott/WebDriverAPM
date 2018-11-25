package automation.web.html.elements.widget;

import automation.web.base.elements.base.ImplementedBy;

@ImplementedBy(HTMLMenuPanelImpl.class)
public interface HTMLMenuPanel extends HTMLElement {
    HTMLMenuPanel home();
    HTMLMenuPanel tasks();
    HTMLMenuPanel createNewClaim();
    HTMLMenuPanel searchParty();
    HTMLMenuPanel claims();
    HTMLMenuPanel viewClaims();
    HTMLMenuPanel viewClaimsPortfolio();
    HTMLMenuPanel archiveClaims();
    HTMLMenuPanel lookupEntitlementTypes();
    HTMLMenuPanel chips();
    HTMLMenuPanel help();
}
