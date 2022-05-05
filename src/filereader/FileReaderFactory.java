
package filereader;

import factory.StockData;
import factory.CardData;
import factory.UserOrderData;
import factory.FileReader;

/**
 * Factory Design Pattern Implementation
 */
public class FileReaderFactory {

    public FileReader getInstance(String fileType){
        if (fileType.equals("Stock")){
            return new StockData();
        }
        else if(fileType.equals("CARD")){
            return new CardData();
        }

        else if(fileType.equals("UserOrder")){
            return new UserOrderData();
        }
        else {
            throw new RuntimeException("Particular instance is not present"+ fileType);
        }
    }
}