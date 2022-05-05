package factory;

import model.Stock;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;

public class StockData implements FileReader<Stock> {

    public ArrayList<Stock> DataFileRead(String filePath) {

        ArrayList<Stock> stockData = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                Stock stock = new Stock(lineData[1].trim(), lineData[0].trim(),
                        Integer.parseInt(lineData[2].trim()), Double.parseDouble(lineData[3].trim()));
                stockData.add(stock);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("stock Error: " + ex.getMessage());
        }
        return stockData;
    }
}