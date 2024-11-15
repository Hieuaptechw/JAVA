//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Entity;

import java.util.Objects;

public class CRStatistics {
    private int id;
    private int month;
    private int year;
    private int totalView;
    private int totalAddTo;
    private int totalCheckOut;

    public CRStatistics() {
    }

    public CRStatistics(int id, int month, int year) {
        this.id = id;
        this.month = month;
        this.year = year;
    }

    public CRStatistics(int id, int month, int year, int totalView, int totalAddTo, int totalCheckOut) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.totalView = totalView;
        this.totalAddTo = totalAddTo;
        this.totalCheckOut = totalCheckOut;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalView() {
        return this.totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public int getTotalAddTo() {
        return this.totalAddTo;
    }

    public void setTotalAddTo(int totalAddTo) {
        this.totalAddTo = totalAddTo;
    }

    public int getTotalCheckOut() {
        return this.totalCheckOut;
    }

    public void setTotalCheckOut(int totalCheckOut) {
        this.totalCheckOut = totalCheckOut;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CRStatistics that = (CRStatistics)o;
            return this.id == that.id && this.month == that.month && this.year == that.year && this.totalView == that.totalView && this.totalAddTo == that.totalAddTo && this.totalCheckOut == that.totalCheckOut;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.month, this.year, this.totalView, this.totalAddTo, this.totalCheckOut});
    }

    public String toString() {
        return "CRStatistics{id=" + this.id + ", month=" + this.month + ", year=" + this.year + ", totalView=" + this.totalView + ", totalAddTo=" + this.totalAddTo + ", totalCheckOut=" + this.totalCheckOut + "}";
    }
}
