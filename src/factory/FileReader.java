package factory;

import model.Stock;
import java.util.ArrayList;

public interface FileReader<T> {

    public  ArrayList<T> DataFileRead(String filePath);
}