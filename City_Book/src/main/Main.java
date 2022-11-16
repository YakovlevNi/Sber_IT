import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.apache.commons.lang.builder.CompareToBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Main {
    private static List<City> cityList = new ArrayList<>();
    private static HashMap<String, Integer> cityPeopleList = new HashMap<>();

    private static List<String> regionList = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException {
        cityParser();
        citySorter();
        getMaxPopulation();
        getCityCountPerRegion();

    }

    private static void cityParser() throws FileNotFoundException {
        CsvToBean csv = new CsvToBean();
        String csvFilename = "C:\\GitHub\\Sber_IT\\City_Book\\src\\resources\\Задача ВС Java Сбер.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename), ';');
        cityList = csv.parse(setColumMapping(), csvReader);
    }

    private static ColumnPositionMappingStrategy setColumMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(City.class);
        String[] columns = new String[]{"id", "name", "region", "district", "population", "foundation"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

    private static void citySorter() {
        Collections.sort(cityList, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return new CompareToBuilder().append(o1.getDistrict(), o2.getDistrict()).append(o1.getName(), o2.getName()).toComparison();
            }

        });
        for (City city : cityList) {
            System.out.println(city);
            cityPeopleList.put(city.getId(), city.getPopulation());
            regionList.add(city.getRegion());


        }

    }

    private static void getMaxPopulation() {
        Map.Entry<String, Integer> maxEntry = cityPeopleList.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);
        System.out.println("\n" + "Максимальное количество жителей" + "\n" + maxEntry);
    }

    private static void getCityCountPerRegion() {
        HashMap<String, Integer> regionCount = new HashMap<String, Integer>();
        Set<String> unique = new HashSet<String>(regionList);
        for (String key : unique) {
            regionCount.put(key, Collections.frequency(regionList, key));
        }
        System.out.println(regionCount);
    }


}
