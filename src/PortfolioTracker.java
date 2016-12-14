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

    public PortfolioTracker() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) throws IOException {
        
        Database stockDB = new Database(StockDBContract.PATH);
        stockDB.createDatabase();
        stockDB.createTable(StockDBContract.HISTORICAL_TABLE_NAME, StockDBContract.HISTORICAL_COLUMN_LIST);
        
        Downloader DL1 = new Downloader(stockDB, "VAS.AX", "2016-12-05");
        DL1.download();
        
//        stockDB.insertIntoTable(StockDBContract.HISTORICAL_TABLE_NAME, 
//                                StockDBContract.HISTORICAL_COLUMNS, 
//                                "'VAP.AX', '2016-12-03', 73.21");

        historicalDBQueryResult result =  stockDB.selectFromTable("SELECT * FROM " + StockDBContract.HISTORICAL_TABLE_NAME);
        result.printResult();
        stockDB.dropTable(StockDBContract.HISTORICAL_TABLE_NAME);
        
    }

}
