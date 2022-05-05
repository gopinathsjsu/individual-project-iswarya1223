
package model;

public class Stock {
    private String itemCategory;
    private String itemName;
    private int itemQuantity;
    private double itemPrice;

    public Stock(String itemCategory, String itemName, int itemQuantity, double itemPrice) {
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return "Stock{" + "itemCategory=" + itemCategory + ", item=" + itemName + ", itemQuantity=" + itemQuantity + ", itemPrice=" + itemPrice + '}';
    }
}
