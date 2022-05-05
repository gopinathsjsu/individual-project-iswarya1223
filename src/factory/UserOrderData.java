package factory;

import model.UserOrder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;

public class UserOrderData implements FileReader<UserOrder> {

    public ArrayList<UserOrder> DataFileRead(String filePath) {

        ArrayList<UserOrder> orderData = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            int count=0;
            String cardnumber ="iswarya";
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                if (count<=0) {
                    UserOrder userorder = new UserOrder(lineData[0], Integer.parseInt(lineData[1]), lineData[2]);
                    count=count+1;
                    cardnumber = lineData[2];

                    orderData.add(userorder);
                }
                else{
                    UserOrder userorder = new UserOrder(lineData[0], Integer.parseInt(lineData[1]), cardnumber);
                    orderData.add(userorder);
                }

            }
        } catch (FileNotFoundException ex) {
            System.err.println("order Error: " + ex.getMessage());
        }

        return orderData;
    }

}