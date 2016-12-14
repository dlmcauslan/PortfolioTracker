/**
 * Note all money amounts are saved as an INT representing number of cents.
 * @author DLMcAuslan
 *
 */
public class StockDBContract {
    // Database path
    public static final String PATH = "Data/stockDB.db";
    
    // Historical stock data table contract
    // Table Name
    public static final String HISTORICAL_TABLE_NAME = "HistoricalStockData";

    // Table Columns
    public static final String HISTORICAL_CODE = "Stock_Code";
    public static final String HISTORICAL_DATE = "Date";
    public static final String HISTORICAL_PRICE = "Price_$";
    public static final String HISTORICAL_PK = HISTORICAL_TABLE_NAME + "_pk";
    public static final String HISTORICAL_COLUMNS = HISTORICAL_CODE + ", " +
                                                    HISTORICAL_DATE + ", " +
                                                    HISTORICAL_PRICE;

    public static final String HISTORICAL_COLUMN_LIST = 
            HISTORICAL_CODE + " TEXT NOT NULL, " +
            HISTORICAL_DATE + " TEXT NOT NULL, " +
            HISTORICAL_PRICE + " INT NOT NULL, " +
            "CONSTRAINT " + HISTORICAL_PK + " PRIMARY KEY (" + HISTORICAL_CODE + ", " 
            + HISTORICAL_DATE + ", " + HISTORICAL_PRICE + ")";

    
    // Stock Purchases data table contract
    // Table Name
    public static final String PURCHASES_TABLE_NAME = "StockPurchases";
    
    // Table columns
    public static final String PURCHASE_ID = "_ID";
    public static final String PURCHASE_CODE = "Stock_Code";
    public static final String PURCHASE_DATE = "Date";
    public static final String PURCHASE_PRICE = "Price_$";
    public static final String PURCHASE_NUMBER_PURCHASED = "Number_Purchased";
    public static final String PURCHASE_COST = "Total_Cost_$";
    public static final String PURCHASE_COLUMNS = PURCHASE_ID + ", " +
                                                  PURCHASE_CODE + ", " +
                                                  PURCHASE_DATE + ", " +
                                                  PURCHASE_PRICE + ", " +
                                                  PURCHASE_NUMBER_PURCHASED + ", " +
                                                  PURCHASE_COST;
    
    public static final String PURCHASE_COLUMN_LIST = PURCHASE_ID + " INT PRIMARY KEY NOT NULL, " +
                                                      PURCHASE_CODE + " TEXT NOT NULL, " +
                                                      PURCHASE_DATE + " TEXT NOT NULL, " +
                                                      PURCHASE_PRICE + " INT NOT NULL, " +
                                                      PURCHASE_NUMBER_PURCHASED + " INT NOT NULL, " +
                                                      PURCHASE_COST + " INT NOT NULL";
    
    
    // Dividends data table contract
    // Table Name
    public static final String DIVIDEND_TABLE_NAME = "Dividends";
    
    // Table Columns
    public static final String DIVIDEND_ID = "_ID";
    public static final String DIVIDEND_CODE = "Stock_Code";
    public static final String DIVIDEND_DATE = "Date";
    public static final String DIVIDEND_AMOUNT = "AMOUNT_$";
    public static final String DIVIDEND_COLUMNS = DIVIDEND_ID + ", " +
                                                  DIVIDEND_CODE + ", " +
                                                  DIVIDEND_DATE + ", " +
                                                  DIVIDEND_AMOUNT + ", " +
                                                  PURCHASE_NUMBER_PURCHASED + ", " +
                                                  PURCHASE_COST;
    
    public static final String DIVIDEND_COLUMN_LIST = DIVIDEND_ID + " INT PRIMARY KEY NOT NULL, " +
                                                      DIVIDEND_CODE + " TEXT NOT NULL, " +
                                                      DIVIDEND_DATE + " TEXT NOT NULL, " +
                                                      DIVIDEND_AMOUNT + " INT NOT NULL";
    
    
    public StockDBContract() {
        // TODO Auto-generated constructor stub
    }

}
