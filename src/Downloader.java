import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * 
 * Downloader class that downloads historical stock data from the web using Yahoo Query Language
 * 
 * @author DLMcAuslan
 * @created 09/12/2016
 * 
 */
public class Downloader {
    private final String stockCode;
    private final String startDate;
    private final String endDate;
    private final Database database;
    private static final String MIN_DATE = "1800-01-01";
    private static final String URL_BASE = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20";
    private static final String URL_END = "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    
    /**
     * Constructor for Downloader Class
     * @param stockCode - Code of the stock to download
     * @param startDate - string for the start date of range of dates to be downloaded in format yyyy-mm-dd.
     * @param endDate -  string for the end date of range of dates to be downloaded in format yyyy-mm-dd.
     */
    public Downloader(Database database, String stockCode, String startDate, String endDate) {
        this.database = database;
        this.stockCode = stockCode;
        this.startDate = startDate;
        this.endDate = endDate;  
    }
    
    /**
     * Constructor for Downloader Class which sets the end date as todays date.
     * @param stockCode - Code of the stock to download
     * @param startDate - string for the start date of range of dates to be downloaded in format yyyy-mm-dd.
     */
    public Downloader(Database database, String stockCode, String startDate) {
        this.database = database;
        this.stockCode = stockCode;
        this.startDate = startDate;
        LocalDate localDate = LocalDate.now();
        this.endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
    }
    
    /**
     * Constructor for Downloader Class which sets start date as MIN_DATE and the end date as todays date.
     * @param stockCode - Code of the stock to download
     */
    public Downloader(Database database, String stockCode) {
        this.database = database;
        this.stockCode = stockCode;
        this.startDate = MIN_DATE;
        LocalDate localDate = LocalDate.now();
        this.endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);   
    }
    
    /**
     * download method. Downloads data for the stock between start and end date. Because the yahoo query language
     * api has a maximum number of results per query, we download 1 year worth of data at a time in a loop.
     * @throws IOException 
     */
    public void download() throws IOException {
        // Get the range of years to loop over
        int minYear = Integer.parseInt(startDate.substring(0, 4));
        int maxYear = Integer.parseInt(endDate.substring(0, 4));
        String minMonthDay;
        String maxMonthDay;
        
        for (int y = minYear; y <= maxYear; y++) {
            // If its the first or last year, have a different start(end) date
            if (y == minYear) minMonthDay = startDate.substring(4);
            else minMonthDay = "-01-01";
            if (y == maxYear) maxMonthDay = endDate.substring(4);
            else maxMonthDay = "-12-31";
            // Create the minimum and maximum date string for the query
            String minDate = String.valueOf(y) + minMonthDay;
            String maxDate = String.valueOf(y) + maxMonthDay;
            // Create the query string and URL to download from
            String query = "where%20symbol%20%3D%20%22" + stockCode + "%22%20and%20startDate%20%3D%20%22" + minDate 
                    + "%22%20and%20endDate%20%3D%20%22" + maxDate + "%22";
            URL DL_URL = new URL(URL_BASE + query + URL_END);
            getDataFromURL(DL_URL);
//            System.out.println(minDate);
//            System.out.println(maxDate);
            System.out.println(DL_URL);
        }
    }
    
    /**
     * Gets stock data from the URL supplied. Downloads json file and parses it to get date
     * and stock close price data.
     * @param DL_URL - URL to download the stock data from
     * @throws IOException
     */
    private void getDataFromURL(URL DL_URL) throws IOException {
        try(InputStream is = DL_URL.openStream();
                JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray dataArray = obj.getJsonObject("query").getJsonObject("results").getJsonArray("quote");
            // Loop over Json Array getting date and closing price data
            for (int i = dataArray.size() - 1; i >= 0; i--) {
                JsonObject dataObj = dataArray.getJsonObject(i);
                String date = dataObj.getString("Date");
//                double close = Double.parseDouble(dataObj.getString("Close"));
                int close = Utilities.moneyStringToInt(dataObj.getString("Close"));
//                System.out.println(date + " - $" + close);
                // TODO: Save in database.
//                String values = "'VAS.AX', '2013-12-02', 70.21";
                String values = "'" + stockCode + "', '" + date + "', " + close;
                database.insertIntoTable(StockContract.Historical.TABLE_NAME, 
                                        StockContract.Historical.COLUMNS, values);
            }            
        }        
    }
    

    
    /**
     * Getter for stock code
     * @return string containing the stockCode
     */
    public String getStockCode() {
        return stockCode;
    }

}
