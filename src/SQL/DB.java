package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import enums.DataType;

/**
 * Database connection object
 * @author sliezzz
 */
public class DB implements AutoCloseable {
	public Connection db;
	public Statement stmt;
	public ResultSet rs;
	
	protected boolean log_queries;

	/**
	 * Parse datatype enum into a sql (now sqlite only)
	 * @param datatype
	 * @param size
	 * @param precision
	 * @return SQL string
	 */
	public static String parseDataType(DataType datatype, int size, int precision) {
		if (datatype == DataType.Numeric && size > 0 && size <= 52)
			return "numeric NOT NULL DEFAULT 0";
		if (datatype == DataType.Numeric)
			return "text NOT NULL DEFAULT '0'";
		if (datatype == DataType.Date)
			return "date NOT NULL DEFAULT '1753-01-01'";
		if (datatype == DataType.String)
			return "text NOT NULL DEFAULT ''";
		if (datatype == DataType.Enum || datatype == DataType.Reference || datatype == DataType.Document)
			return "text NOT NULL DEFAULT '     0   '";
		return "numeric NULL";	// object
	}
	
	/**
	 * Initialization
	 * @throws ClassNotFoundException or SQLException if cannot connect
	 */
	public DB(String fileName) throws ClassNotFoundException, SQLException 
	{
		// variables
		db = null;
		rs=null;
		stmt=null;
		log_queries = false;

		// connect to Database
		Class.forName("org.sqlite.JDBC");
		db = DriverManager.getConnection("jdbc:sqlite:"+fileName);
	}

	/**
	 * Enable logging of executed queries
	 * @return the state of logging before executing this method
	 */
	public boolean EnableLog() {
		if (log_queries)
			return true;
		log_queries=true;
		return false;
	}

	/**
	 * Disable logging of executed queries
	 * @return the state of logging before executing this method
	 */
	public boolean DisableLog() {
		if (!log_queries)
			return false;
		log_queries=false;
		return true;
	}

	/**
	 * Release resources
	 */
	public void close()
	{
		// try to clear
		try {
			clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// disconnect
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Begins a transaction
	 * @throws SQLException if query can't executed
	 */
	public void begintran() throws SQLException
	{
//		db.setAutoCommit(false);
//		db.createStatement().execute("BEGIN TRANSACTION");
	}

	/**
	 * Commits a transaction
	 * @throws SQLException if query can't executed
	 */
	public void commit() throws SQLException
	{
//		db.commit();
//		db.setAutoCommit(true);
	}

	/**
	 * Rollbacks a transaction
	 * @throws SQLException if query can't executed
	 */
	public void rollback() throws SQLException
	{
//		db.rollback();
//		db.setAutoCommit(true);
	}


	/**
	 * Execute query
	 * @param sql SQL statement to execute
	 * @return status
	 * @throws SQLException if query can't executed
	 */
	public boolean exec(String sql) throws SQLException
	{
		if (log_queries) {
			System.out.println(sql);
		}
		Statement stmt = db.createStatement();
		boolean res = stmt.execute(sql);
		stmt.close();
		return res;
	}

	/**
	 * Execute query
	 * @param sql SQL statement to execute
	 * @return status
	 * @throws SQLException if query can't executed
	 */
	public boolean exec(SQL sql) throws SQLException {
		return exec(sql.toString());		
	}
	
	/**
	 * Run a scalar query
	 * @param sql SQL statement to execute
	 * @return value of the 1st column of the 1st row
	 * @throws SQLException if query can't executed
	 */
	public String scalar(String sql) throws SQLException
	{
		String val = null;

		if (log_queries) {
			System.out.println(sql);
		}

		Statement stmt = db.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			val = rs.getString(1);
		}
		rs.close();
		stmt.close();
		return val;
	}

	/**
	 * Run a query
	 * Resulted RecordSet & Statement are binded to this object
	 * @param sql SQL statement to execute
	 * @return ResultSet
	 * @throws SQLException if query can't executed
	 */
	public ResultSet query(String sql) throws SQLException
	{
		clear();
		if (log_queries) {
			System.out.println(sql);
		}

		stmt = db.createStatement();
		rs = stmt.executeQuery(sql);
		return rs;
	}

	/**
	 * Run a query
	 * Resulted RecordSet & Statement are binded to this object
	 * @param sql SQL statement to execute
	 * @return ResultSet
	 * @throws SQLException if query can't executed
	 */
	public ResultSet query(SQL sql) throws SQLException
	{
		return query(sql.toString());
	}

	/**
	 *  Clear RecordSet & Statement of the last query()
	 * @throws SQLException if something goes wrong
	 */
	public void clear() throws SQLException
	{
		if (rs!=null) {
			rs.close();
			rs=null;
		}
		if (stmt!=null) {
			stmt.close();
			stmt=null;
		}

	}

}
