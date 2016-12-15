/**
 * 
 * Stock class that holds all of the information about an individual stock.
 * 
 * @author DLMcAuslan
 * @created 09/12/2016
 *
 */
public class Stock {
    private final String stockCode;
    private final Database database;
    
    public Stock(String stockCode, Database database) {
        this.stockCode = stockCode;
        this.database = database;
        
        // Try update stock data, throws URL error or something
    }
    
    /**
     * Add a buy transaction to the Purchases database
     * @param numberBought - number of shares bought
     * @param price - purchase price
     * @param date - date of purchase
     */
    public void buy(int numberBought, double price, String date) {
        String purchaseString = "'" + stockCode + "', '" + date + "', " + Utilities.moneyDoubleToInt(price)
                                + ", " + numberBought + ", " + numberBought*Utilities.moneyDoubleToInt(price);
        database.insertIntoTable(StockContract.Purchases.TABLE_NAME, 
                                 StockContract.Purchases.COLUMNS, 
                                 purchaseString);
    }
    
    /**
     * Add a sell transaction to the Purchases database
     * @param numberSold - number of shares sold
     * @param price - sell price
     * @param date - date of sale
     */
    public void sell(int numberSold, double price, String date) {
        String purchaseString = "'" + stockCode + "', '" + date + "', " + Utilities.moneyDoubleToInt(price)
                                + ", " + -numberSold + ", " + -numberSold*Utilities.moneyDoubleToInt(price);
        database.insertIntoTable(StockContract.Purchases.TABLE_NAME, 
                                 StockContract.Purchases.COLUMNS, 
                                 purchaseString);
    }
    
    /**
    * Add a dividend payment to the Dividend database
    * @param payment - the dividend payment
    * @param date - date of payment
    */
   public void addDividend(double payment, String date) {
       String insertString = "'" + stockCode + "', '" + date + "', " + Utilities.moneyDoubleToInt(payment);
       database.insertIntoTable(StockContract.Dividend.TABLE_NAME, 
                                StockContract.Dividend.COLUMNS, 
                                insertString);
   }
}
