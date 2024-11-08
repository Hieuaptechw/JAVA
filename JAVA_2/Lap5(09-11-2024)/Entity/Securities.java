package Entity;

public class Securities {
    private int id;
    private int tickerId;
    private SecuritiesType securityType;

    public Securities() {
    }

    public Securities(int id, int tickerId, SecuritiesType securityType) {
        this.id = id;
        this.tickerId = tickerId;
        this.securityType = securityType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTickerId() {
        return tickerId;
    }

    public void setTickerId(int tickerId) {
        this.tickerId = tickerId;
    }

    public SecuritiesType getSecurityType() {
        return securityType;
    }

    public void setSecurityType(SecuritiesType securityType) {
        this.securityType = securityType;
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", tickerId=" + tickerId +
                ", securityType=" + securityType +
                '}';
    }
}
