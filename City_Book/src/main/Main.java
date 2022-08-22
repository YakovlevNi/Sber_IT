import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        CsvToBean csv = new CsvToBean();
        String csvFilename = "C:\\GitHub\\Sber_IT\\City_Book\\src\\resources\\Задача ВС Java Сбер.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename), ';');
        List list = csv.parse(setColumMapping(), csvReader);
        for (Object object : list) {
           City city = (City) object;
            System.out.println(city);
        }
        }

        private static ColumnPositionMappingStrategy setColumMapping()
        {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(City.class);
            String[] columns = new String[] {"id", "name", "region", "district", "population","foundation"};
            strategy.setColumnMapping(columns);

            return strategy;
        }
    }


