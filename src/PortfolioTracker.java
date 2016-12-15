import java.io.IOException;

/**
 * 
 * Primary class for Portfolio Tracker app. The main methods are all called and run from here.
 * 
 * @author DLMcAuslan
 * @created 09/12/2016
 *
 */
public class PortfolioTracker {
    private static Database stockDB;
    
    public PortfolioTracker() {
        // Create database
        stockDB = new Database(StockContract.PATH);
        stockDB.createDatabase();
        // Create tables
        stockDB.createTable(StockContract.Historical.TABLE_NAME, StockContract.Historical.TABLE_CREATE);
        stockDB.createTable(StockContract.Purchases.TABLE_NAME, StockContract.Purchases.TABLE_CREATE);
        stockDB.createTable(StockContract.Dividend.TABLE_NAME, StockContract.Dividend.TABLE_CREATE);
    }

    public static void main(String[] args) throws IOException {
        PortfolioTracker portfolio = new PortfolioTracker();
//      
        // Download functions can be added to stock class
        Downloader DL1 = new Downloader(stockDB, "VAS.AX", "2016-12-05");
        DL1.download();
        
        stockDB.selectFromTable(StockContract.Historical.TABLE_NAME, StockContract.Historical.COLUMNS, 
                                "SELECT * FROM " + StockContract.Historical.TABLE_NAME);

//        historicalDBQueryResult result =  stockDB.selectFromTable("SELECT * FROM " + StockContract.Historical.TABLE_NAME);
//        result.printResult();
        
        // Create stock class and buy some.
        Stock vas = new Stock("VAS.AX", stockDB);
        vas.buy(44, 70.87, "2014-04-29");
        vas.sell(44, 70.87, "2014-04-29");
        
        stockDB.selectFromTable(StockContract.Purchases.TABLE_NAME, StockContract.Purchases.COLUMNS, 
                "SELECT * FROM " + StockContract.Purchases.TABLE_NAME);
        
        // Add dividends
        vas.addDividend(56.65, "2014-10-17");
        vas.addDividend(54.51, "2015-01-19");
        stockDB.selectFromTable(StockContract.Dividend.TABLE_NAME, StockContract.Dividend.COLUMNS, 
                "SELECT * FROM " + StockContract.Dividend.TABLE_NAME);
        
        
        // Drop tables
        stockDB.dropTable(StockContract.Historical.TABLE_NAME);
        stockDB.dropTable(StockContract.Purchases.TABLE_NAME);
        stockDB.dropTable(StockContract.Dividend.TABLE_NAME);
        
    }

}
