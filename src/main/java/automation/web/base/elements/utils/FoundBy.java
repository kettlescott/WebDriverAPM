package automation.web.base.elements.utils;

public enum FoundBy {
    ID("id"),
    LINK_TEXT("link text"),
    PARTIAL_LINK_TEXT("partial link text"),
    TAG_NAME("tag name"),
    NAME("name"),
    CLASS_NAME("class name"),
    CSS_SELECTOR("css selector"),
    XPATH("xpath");

    private final String fbStrings;

    FoundBy(String foundBy) {
        this.fbStrings = foundBy;
    }

    public static FoundBy fromString(String foundBy) {
        for(FoundBy b:FoundBy.values()) {
            if(b.fbStrings.equalsIgnoreCase(foundBy)) {
                return b;
            }
        }
        return null;
    }
}
