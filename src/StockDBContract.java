
public class StockDBContract {
    // Database path
    public static final String PATH = "Data/stockDB.db";
    
    // Historical stock data table contract
    // Table Name
    public static final String HISTORICAL_TABLE_NAME = "HistoricalStockData";

    // Table Columns
    public static final String HISTORICAL_DATE = "Date";
    public static final String HISTORICAL_PRICE = "Price_$";
    public static final String HISTORICAL_CODE = "Stock_Code";
    public static final String HISTORICAL_COLUMNS = HISTORICAL_CODE + ", " +
                                                    HISTORICAL_DATE + ", " +
                                                    HISTORICAL_PRICE;
//    HISTORICAL_COLUMNS = [HISTORICAL_CODE, HISTORICAL_DATE, HISTORICAL_PRICE]

    public static final String HISTORICAL_COLUMN_LIST = HISTORICAL_CODE + " TEXT, " +
                                                  HISTORICAL_DATE + " TEXT, " +
                                                  HISTORICAL_PRICE + " REAL";

    
    public StockDBContract() {
        // TODO Auto-generated constructor stub
    }

}
