import java.sql.*;

public class Database {
    private final String databasePath;
    
    public Database(String databasePath) {
        this.databasePath = databasePath;
    }
    
    /**
     * Opens the database at the location given by databasePath. If the database
     * does not exist, it creates it.
     */
    public void createDatabase() {
      Connection c = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Opened database successfully");
    }
    
    /**
     * Creates a table with name tableName and columns given by columnList in the database.
     * @param tableName
     * @param columnList
     */
    public void createTable(String tableName,  String columnList) {
      Connection c = null;
      Statement stmt = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "CREATE TABLE " + tableName + "( " + columnList + ")"; 
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Table " + tableName + " created successfully");
    }
    
    /**
     * Drops the table tableName from the database.
     * @param tableName
     */
    public void dropTable(String tableName) {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "DROP TABLE " + tableName; 
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table " + tableName + " dropped successfully");
      }
    
    /**
     * Insert data into tableName, where columns is a string of the column names, and 
     * values is a string containing the data values to be added.
     * @param tableName
     * @param columns
     * @param values
     */
    public void insertIntoTable(String tableName, String columns, String values) {
      Connection c = null;
      Statement stmt = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO " + tableName + " (" + columns + ") " + "VALUES (" + values + ");"; 
        stmt.executeUpdate(sql);

        stmt.close();
        c.commit();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Records created successfully");
    }
    
    /**
     * Performs the select query given by query on the database.
     * @param query
     */
    public void selectFromTable(String query)
    {
      Connection c = null;
      Statement stmt = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while ( rs.next() ) {
           String code = rs.getString(StockDBContract.HISTORICAL_CODE);
           String date = rs.getString(StockDBContract.HISTORICAL_DATE);
           double price  = rs.getDouble(StockDBContract.HISTORICAL_PRICE);
           System.out.println( "Code = " + code );
           System.out.println( "Date = " + date );
           System.out.println( "Price = " + price );
           // TODO: save this information and return it. - Probably as 3 different arrayLists, saved in an object.
        }
        rs.close();
        stmt.close();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Operation done successfully");
    }
}
