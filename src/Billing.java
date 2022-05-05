
import model.Card;
import model.Stock;
import model.UserOrder;
import model.CapCategory;
import factory.FileReader;
import filereader.FileReaderFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;
import factory.StockData;
import factory.CardData;
public class Billing {

    public static void main(String[] args) throws IOException {
        double totalAmount = 0;
        int incorrectCount=0;
        int essentialCount=0;
        int luxuryCount=0;
        int miscCount=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Input File Path");
        String InputFilePath = sc.nextLine();
       FileReaderFactory readFactory = new FileReaderFactory();
        FileReader stockDataRead = readFactory.getInstance("Stock");
        FileReader cardDataRead = readFactory.getInstance("CARD");
        FileReader UserOrderDataRead = readFactory.getInstance("UserOrder");
        ArrayList<Stock> stock= stockDataRead.DataFileRead("Stock.csv");
        //CardData cd = new CardData();
        ArrayList<Card> cards = cardDataRead.DataFileRead("Cardnumbers.csv");
        ArrayList<UserOrder> userOrders = UserOrderDataRead.DataFileRead(InputFilePath);
        CapCategory cap = CapCategory.getInstance();
        HashMap<String, Integer> category = cap.capInfo;
        int essential = category.get("Essentials");

        int luxury = category.get("Luxury");
        int misc = category.get("Misc");
        ArrayList<List<String>> orderitems = new ArrayList<List<String>>();
        int count1 =0;
        for (UserOrder or : userOrders) {
            for (Stock st : stock) {
                if (or.getItemName().equalsIgnoreCase(st.getItemName())) {

                    if (st.getItemCategory().equalsIgnoreCase("Essentials") && st.getItemQuantity() >= or.getItemQuantity())
                    {
                        essentialCount =essentialCount+or.getItemQuantity();
                         if(essentialCount <= essential) {
                             double price = st.getItemPrice() * or.getItemQuantity();
                             if (count1 == 0) {
                                 if (!cardPresent(cards, or.getCardNumber())) {
                                     updateCardNumbers(or.getCardNumber());
                                     count1 = count1 + 1;
                                 }
                             }
                             totalAmount += price;
                             ArrayList<String> singleorder = new ArrayList<String>();
                             singleorder.add(or.getItemName());
                             singleorder.add(Integer.toString(or.getItemQuantity()));
                             singleorder.add(Double.toString(price));
                             orderitems.add(singleorder);

                             break;
                         }
                         else{
                             int stock_available=0;
                             int incCount= incorrectQuantity(stock_available,or.getItemName(),or.getItemQuantity());
                             incorrectCount = incorrectCount+incCount;
                         }
                    } else if (st.getItemCategory().equalsIgnoreCase("luxury") && st.getItemQuantity() >= or.getItemQuantity())
                    {
                        luxuryCount =luxuryCount+or.getItemQuantity();
                        if(luxuryCount <= luxury) {
                            double price = st.getItemPrice() * or.getItemQuantity();
                            totalAmount += price;
                            if (count1 == 0) {
                                if (!cardPresent(cards, or.getCardNumber())) {
                                    updateCardNumbers(or.getCardNumber());
                                    count1 = count1 + 1;
                                }
                            }
                            ArrayList<String> singleorder = new ArrayList<String>();
                            singleorder.add(or.getItemName());
                            singleorder.add(Integer.toString(or.getItemQuantity()));
                            singleorder.add(Double.toString(price));
                            orderitems.add(singleorder);

                            break;
                        }
                        else {
                            int stock_available=0;
                            int incCount= incorrectQuantity(stock_available,or.getItemName(),or.getItemQuantity());
                            incorrectCount = incorrectCount+incCount;
                        }
                    } else if (st.getItemCategory().equalsIgnoreCase("misc") && st.getItemQuantity() >= or.getItemQuantity())
                    {
                        miscCount =miscCount+or.getItemQuantity();
                        if(miscCount <= misc) {
                        double price = st.getItemPrice() * or.getItemQuantity();
                        totalAmount += price;

                        if (count1 == 0) {
                            if (!cardPresent(cards, or.getCardNumber())) {
                                updateCardNumbers(or.getCardNumber());
                                count1 = count1 + 1;
                            }
                        }
                        ArrayList<String> singleorder = new ArrayList<String>();
                        singleorder.add(or.getItemName());
                        singleorder.add(Integer.toString(or.getItemQuantity()));
                        singleorder.add(Double.toString(price));
                        orderitems.add(singleorder);

                        break;
                    }
                            else {
                            int stock_available=0;
                            int incCount= incorrectQuantity(stock_available,or.getItemName(),or.getItemQuantity());
                            incorrectCount = incorrectCount+incCount;
                            }

                    } else {
                        int stock_available=1;
                        int incCount= incorrectQuantity(stock_available,or.getItemName(),or.getItemQuantity());
                        incorrectCount = incorrectCount+incCount;
                    }
                }
            }

        }
        if (totalAmount > 0 && incorrectCount==0) {
            updateAmount(totalAmount,orderitems);
        }
    }
    public static int incorrectQuantity(int stock_available,String itemName,int itemQuantity) {
        int incCount=0;
        System.err.println("Incorrect quantities writing to error.txt");
        if (stock_available == 1)
        {
            String log = "Stock is not available for the below items\n";
            log += "Item: " + itemName + '(' + itemQuantity + ')';
            errorLog(log);
        }
        else{
            String log = "Cap limit exceeded on the below items\n";
            log += "Item: " + itemName + '(' + itemQuantity + ')';
            errorLog(log);
        }

        incCount = incCount+1;
        return incCount;
    }


    public static void updateCardNumbers(String cardNumber) throws IOException {
        FileWriter pw = new FileWriter("Cardnumbers.csv", true);
        pw.append(cardNumber + ",");
        pw.append("\n");
        pw.flush();
        pw.close();
    }
    public static void errorLog(String content) {
        FileWriter writer = null;
        try {
            File file = new File("error.txt");
            writer = new FileWriter(file, true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(content + "\n");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static boolean cardPresent(ArrayList<Card> cards, String cardNumber) {
        boolean status = false;
        for (Card c : cards) {
            if (c.getCardNumber().equals(cardNumber)) {
                status = true;
                break;
            }
        }

        return status;
    }

    public static void updateAmount(double amount,ArrayList<List<String>> orderitems) throws IOException {
        System.out.println("Total Amount Paid " + amount);
        FileWriter pw = new FileWriter("Output.csv");
        pw.append("Items");
        pw.append(",");
        pw.append("Quantity");
        pw.append(",");
        pw.append("Price");
        pw.append(",");
        pw.append("Amt Paid");
        pw.append("\n");
        for (int i =0 ; i< orderitems.size();i++) {
            for (int j = 0; j < orderitems.get(i).size(); j++) {
                pw.append(orderitems.get(i).get(j));
                pw.append(",");
            }
            if (i == 0) {
                pw.append(amount + "rs");
            }
            pw.append("\n");
        }
        pw.flush();
        pw.close();
    }


}
