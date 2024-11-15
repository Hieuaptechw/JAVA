//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Entity;

import java.time.LocalDate;

public class StatisticsView {
    private int productId;
    private int view;
    private int numberOfAddToCard;
    private int numberOfCheckout;
    private LocalDate date;

    public StatisticsView() {
    }

    public StatisticsView(int productId, int view, int numberOfAddToCard, int numberOfCheckout, LocalDate date) {
        this.productId = productId;
        this.view = view;
        this.numberOfAddToCard = numberOfAddToCard;
        this.numberOfCheckout = numberOfCheckout;
        this.date = date;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getView() {
        return this.view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getNumberOfAddToCard() {
        return this.numberOfAddToCard;
    }

    public void setNumberOfAddToCard(int numberOfAddToCard) {
        this.numberOfAddToCard = numberOfAddToCard;
    }

    public int getNumberOfCheckout() {
        return this.numberOfCheckout;
    }

    public void setNumberOfCheckout(int numberOfCheckout) {
        this.numberOfCheckout = numberOfCheckout;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMonthOfDate() {
        return this.date.getMonthValue();
    }

    public int getYearOfDate() {
        return this.date.getYear();
    }

    public String toString() {
        int var10000 = this.productId;
        return "StatisticsView{productId=" + var10000 + ", view=" + this.view + ", numberOfAddToCard=" + this.numberOfAddToCard + ", numberOfCheckout=" + this.numberOfCheckout + ", date=" + String.valueOf(this.date) + "}";
    }

    public String toString(String separator) {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.getProductId()).append(separator).append(this.getView()).append(separator).append(this.getNumberOfAddToCard()).append(separator).append(this.getNumberOfCheckout()).append(separator).append(this.getDate()).toString();
    }
}
