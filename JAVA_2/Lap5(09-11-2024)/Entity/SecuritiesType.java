package Entity;

public enum SecuritiesType {
    STOCK("Stock"),
    BOND("Bond"),
    COMMODITY("Commodity");

    private String label;
    SecuritiesType(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
