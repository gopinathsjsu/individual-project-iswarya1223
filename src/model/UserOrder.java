
package model;

public class UserOrder {
    // instance variables
    private String itemName;
    private int itemQuantity;
    private String cardNumber;

    public UserOrder(String itemName, int itemQuantity, String cardNumber) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.cardNumber = cardNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return  itemName + ", " + itemQuantity + ", " + cardNumber;
    }
       
}
