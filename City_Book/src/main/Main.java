import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.apache.commons.lang.builder.CompareToBuilder;


import java.io.FileReader;
import java.util.*;

public class Main {
    private static List<City> cityList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        CsvToBean csv = new CsvToBean();
        String csvFilename = "C:\\GitHub\\Sber_IT\\City_Book\\src\\resources\\Задача ВС Java Сбер.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename), ';');
        cityList = csv.parse(setColumMapping(), csvReader);
        // System.out.println(cityList);


        Collections.sort(cityList, new Comparator<City>() {

            @Override
            public int compare(City o1, City o2) {


                return new CompareToBuilder()
                        .append(o1.getDistrict(), o2.getDistrict())
                        .append(o1.getName(), o2.getName())
                        .toComparison();
            }

        });


        for (City city : cityList) {

            System.out.println(city);
        }
    }


    private static ColumnPositionMappingStrategy setColumMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(City.class);
        String[] columns = new String[]{"id", "name", "region", "district", "population", "foundation"};
        ;
        strategy.setColumnMapping(columns);
        ;


        return strategy;
    }
}
