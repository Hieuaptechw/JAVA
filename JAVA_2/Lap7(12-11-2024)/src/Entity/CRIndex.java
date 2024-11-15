package Entity;

public class CRIndex {
    private int id;
    private int month;
    private int year;
    private double indexAddTo;
    private double indexCheckOut;
    public CRIndex() {
    }

    public CRIndex(int id, int month, int year, double indexAddTo, double indexCheckOut) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.indexAddTo = indexAddTo;
        this.indexCheckOut = indexCheckOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getIndexAddTo() {
        return indexAddTo;
    }

    public void setIndexAddTo(double indexAddTo) {
        this.indexAddTo = indexAddTo;
    }

    public double getIndexCheckOut() {
        return indexCheckOut;
    }

    public void setIndexCheckOut(double indexCheckOut) {
        this.indexCheckOut = indexCheckOut;
    }
    public String toString(String separator) {
        StringBuilder sb = new StringBuilder();
        return sb
                .append(this.getId())
                .append(separator)
                .append(this.getMonth())
                .append(separator)
                .append(this.getYear())
                .append(separator)
                .append(this.getIndexAddTo())
                .append(separator)
                .append(this.getIndexCheckOut()).toString();
    }
}
