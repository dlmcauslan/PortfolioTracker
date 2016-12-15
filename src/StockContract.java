/**
 * Note all money amounts are saved as an INT representing number of cents.
 * @author DLMcAuslan
 *
 */
public final class StockContract {
    // Database path
    public static final String PATH = "Data/stockDB.db";
    
    /**
     * Historical stock data table contract
     */
    public abstract class Historical {
        // Table Name
        public static final String TABLE_NAME = "HistoricalStockData";

        // Table Columns
        public static final String CODE = "Stock_Code";
        public static final String DATE = "Date";
        public static final String PRICE = "Price";
        public static final String PK = TABLE_NAME + "_pk";
        public static final String COLUMNS = CODE + ", " +
                                             DATE + ", " +
                                             PRICE;

        public static final String TABLE_CREATE = CODE + " TEXT NOT NULL, " +
                                                 DATE + " TEXT NOT NULL, " +
                                                 PRICE + " INTEGER NOT NULL, " +
                                                 "CONSTRAINT " + PK + " PRIMARY KEY (" + CODE + ", " 
                                                 + DATE + ", " + PRICE + ")";
    }
    
    /**
     * Stock Purchases data table contract
     */
    public abstract class Purchases {
        // Table Name
        public static final String TABLE_NAME = "StockPurchases";
        
        // Table columns
        public static final String ID = "_ID";
        public static final String CODE = "Stock_Code";
        public static final String DATE = "Date";
        public static final String PRICE = "Price";
        public static final String NUMBER_PURCHASED = "Number_Purchased";
        public static final String COST = "Total_Cost";
        public static final String COLUMNS = CODE + ", " +
                                             DATE + ", " +
                                             PRICE + ", " +
                                             NUMBER_PURCHASED + ", " +
                                             COST;
        
        public static final String TABLE_CREATE = ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                 CODE + " TEXT NOT NULL, " +
                                                 DATE + " TEXT NOT NULL, " +
                                                 PRICE + " INTEGER NOT NULL, " +
                                                 NUMBER_PURCHASED + " INTEGER NOT NULL, " +
                                                 COST + " INTEGER NOT NULL";
    }
    
    /**
     * Dividends data table contract
     */
    public abstract class Dividend {
        // Table Name
        public static final String TABLE_NAME = "Dividends";
        
        // Table Columns
        public static final String ID = "_ID";
        public static final String CODE = "Stock_Code";
        public static final String DATE = "Date";
        public static final String AMOUNT = "Amount";
        public static final String COLUMNS = CODE + ", " +
                                             DATE + ", " +
                                             AMOUNT;
        
        public static final String TABLE_CREATE = ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                 CODE + " TEXT NOT NULL, " +
                                                 DATE + " TEXT NOT NULL, " +
                                                 AMOUNT + " INTEGER NOT NULL";
    }
    

}
