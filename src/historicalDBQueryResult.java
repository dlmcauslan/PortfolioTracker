import java.util.ArrayList;
import java.util.List;

public class historicalDBQueryResult {
    private List<String> dates;
    private List<String> codes;
    private List<Double> prices;
    
    
    public historicalDBQueryResult() {
        dates = new ArrayList<String>();
        codes = new ArrayList<String>();
        prices = new ArrayList<Double>();
    }
    
    public void printResult() {
        System.out.println("\n" + StockContract.Historical.COLUMNS);
        System.out.println("--------------------------");
        for (int i = 0; i < dates.size(); i++) {
            System.out.println(codes.get(i) + "  " + dates.get(i) + "  " + prices.get(i));
        }
        System.out.print("\n");
    }
        
    public void add(String code, String date, int price) {
       codes.add(code);
       dates.add(date);
       prices.add(Utilities.centsToDollars(price));
    }

}
