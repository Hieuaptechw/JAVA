package Entity;

import java.time.LocalDate;

public class AnalysisProduct {
    private int id;
    private int productId;
    private int numberOfClick;
    private int numberOfAddToCard;
    private int numberOfCheckout;
    private LocalDate date;

    public AnalysisProduct() {
    }

    public AnalysisProduct(int id, int productId, int numberOfClick, int numberOfAddToCard, int numberOfCheckout, LocalDate date) {
        this.id = id;
        this.productId = productId;
        this.numberOfClick = numberOfClick;
        this.numberOfAddToCard = numberOfAddToCard;
        this.numberOfCheckout = numberOfCheckout;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfClick() {
        return numberOfClick;
    }

    public void setNumberOfClick(int numberOfClick) {
        this.numberOfClick = numberOfClick;
    }

    public int getNumberOfAddToCard() {
        return numberOfAddToCard;
    }

    public void setNumberOfAddToCard(int numberOfAddToCard) {
        this.numberOfAddToCard = numberOfAddToCard;
    }

    public int getNumberOfCheckout() {
        return numberOfCheckout;
    }

    public void setNumberOfCheckout(int numberOfCheckout) {
        this.numberOfCheckout = numberOfCheckout;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String toString(String separator) {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.getId())
                .append(separator)
                .append(this.getProductId())
                .append(separator)
                .append(this.getNumberOfClick())
                .append(separator)
                .append(this.getNumberOfAddToCard())
                .append(separator)
                .append(this.getNumberOfCheckout())
                .append(separator)
                .append(this.getDate())
                .toString();
    }
}