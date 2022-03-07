package Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import SQL.DB;
import SQL.SQL;
import enums.DataType;
import enums.RuntimeExceptionKinds;
import runtime.ExecException;
import runtime.IndexedStack;
import runtime.Runtime;
import runtime.Value;

public class Table implements AutoCloseable {
	static protected long table_index = 0;
	protected DB mem_db;
	protected IndexedStack objects = new IndexedStack();
	protected String table_name;
	protected long column_id_last = 0;
	protected int column_count = 0;
	protected long deleted_column_count = 0;
	protected long row_id_last = 0;
	protected int row_count = 0;
	protected int cursor_column = 0;
	protected int cursor_row = 0;

	/**
	 * Create empty Table
	 * @throws ExecException 
	 */
	public Table() throws ExecException {
		String sql;
		mem_db = runtime.Runtime.mem_db; 

		// generate internal name
		table_name = String.format("table_%d", table_index);
		table_index++;

		// create table on in-memory database
		try {
			mem_db.begintran();
			sql="CREATE TABLE ["+table_name+"] ([row_id] int NOT NULL PRIMARY KEY, [row_no] int NOT NULL)";
			mem_db.exec(sql);
			sql="CREATE TABLE ["+table_name+"_schema] "+
					"([column_id] int PRIMARY KEY"+
					",[column_no] int"+
					",[name] text NOT NULL"+
					",[title] text NOT NULL DEFAULT ''"+
					",[datatype] text NOT NULL DEFAULT ''"+
					",[size] int NOT NULL DEFAULT 0"+
					",[precision] int NOT NULL DEFAULT 0"+
					",[width] int NOT NULL DEFAULT 0"+
					",[format] text NOT NULL DEFAULT ''"+
					",[align] int NOT NULL DEFAULT 0"+
				")";
			mem_db.exec(sql);
			mem_db.commit();
		}
		catch (SQLException e) {
			try { mem_db.rollback(); } catch (SQLException err) { }
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}
	}

	/**
	 * Release resources
	 * @throws ExecException 
	 */
	public void close() throws ExecException
	{
		try {
			mem_db.begintran();
			mem_db.exec("DROP TABLE ["+table_name+"]");
			mem_db.exec("DROP TABLE ["+table_name+"_schema]");
			mem_db.commit();
		}
		catch (SQLException e) {
			try { mem_db.rollback(); } catch (SQLException err) { }
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}
	}

	/**
	 * Adds new column
	 * @throws ExecException 
	 */
	public Integer addColumn(String name, String title, String datatype, int size, int precision, int width, String format, int align) throws ExecException
	{
		return insertColumn(name, column_count+1, title, datatype, size, precision, width, format, align);
	}

	/**
	 * Inserts new column into the specified position
	 * @throws ExecException 
	 */
	public Integer insertColumn(String name, int column_no, String title, String datatype, int size, int precision, int width, String format, int align) throws ExecException
	{
		SQL sql = new SQL();

		column_id_last++;
		column_count++;
		try {
			sql.setSQL("ALTER TABLE ["+table_name+"] ADD COLUMN [c"+column_id_last+"] text NULL");
			mem_db.exec(sql);

			if (column_no < column_count) {
				sql.setSQL("UPDATE ["+table_name+"_schema] SET [column_no]=[columnn_no]+1 WHERE [column_no]>=:column_no");
				sql.set("column_no", column_no);
				mem_db.exec(sql);
			}

			sql.setSQL("INSERT INTO "+table_name+"_schema "+
					"([column_id],[column_no],[name],[title],[datatype],[size],[precision],[width],[format],[align])"+
					"VALUES(:column_id,:column_no,:name,:title,:datatype,:size,:precision,:width,:format,:align)"
				);
			sql.set("column_id", column_id_last);
			sql.set("column_no", column_no);
			sql.set("name", name);
			sql.set("title", title);
			sql.set("datatype", datatype);
			sql.set("size", size);
			sql.set("precision", precision);
			sql.set("width", width);
			sql.set("format", format);
			sql.set("align", align);
			mem_db.exec(sql);
		}
		catch (SQLException e) {
			column_count--;
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}
		return column_no;
	}

	/**
	 * Gets column info by {@code column_no}
	 * @throws ExecException 
	 */
	public String setColumnInfo(int column_no, String datatype, int size, int precision, String title, int width, String format, int align) throws ExecException
	{
		SQL sql = new SQL("SELECT * FROM ["+table_name+"_schema] WHERE [column_no]=:column_no");
		sql.set("column_no", column_no);
		try
		{
			ResultSet rs = mem_db.query(sql);
			if (rs.next()) {
				title = rs.getString("title");
				datatype = rs.getString("datatype");
				size = rs.getInt("size");
				precision = rs.getInt("precision");
				width = rs.getInt("width");
				format = rs.getString("format");
				align = rs.getInt("align");
				return rs.getString("name");
			}
		}
		catch (SQLException e) {
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}
		return null;
	}

	/**
	 * Gets column info by {@code name}
	 * @throws ExecException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public Integer setColumnInfo(String name, String datatype, int size, int precision, String title, int width, String format, int align) throws ExecException
	{
		SQL sql = new SQL("SELECT * FROM ["+table_name+"_schema] WHERE [name]=:name");
		sql.set("name", name);
		try
		{
			ResultSet rs = mem_db.query(sql);
			if (rs.next()) {
				title = rs.getString("title");
				datatype = rs.getString("datatype");
				size = rs.getInt("size");
				precision = rs.getInt("precision");
				width = rs.getInt("width");
				format = rs.getString("format");
				align = rs.getInt("align");
				return rs.getInt("column_no");
			}
		}
		catch (SQLException e) {
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}
		return null;
	}

	/**
	 * Gets column info by {@code column_no}
	 * @throws ExecException 
	 */
	public String getColumnInfo(int column_no, String title, String datatype, int size, int precision, int width, String format, int align) throws ExecException
	{
		SQL sql = new SQL("SELECT * FROM ["+table_name+"_schema] WHERE [column_no]=:column_no");
		sql.set("column_id", column_no);
		try
		{
			ResultSet rs = mem_db.query(sql);
			if (rs.next()) {
				title = rs.getString("title");
				datatype = rs.getString("datatype");
				size = rs.getInt("size");
				precision = rs.getInt("precision");
				width = rs.getInt("width");
				format = rs.getString("format");
				align = rs.getInt("align");
				return rs.getString("name");
			}
			
			// not found
			throw new ExecException(RuntimeExceptionKinds.WrongIndex);
		}
		catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Gets column info by {@code name}
	 * @throws ExecException 
	 */
	public Integer getColumnInfo(String name, String title, String datatype, int size, int precision, int width, String format, int align) throws ExecException
	{
		SQL sql = new SQL("SELECT * FROM ["+table_name+"_schema] WHERE [name]=:name ORDER BY column_no LIMIT 1");
		sql.set("name", name);
		try
		{
			ResultSet rs = mem_db.query(sql);
			if (rs.next()) {
				title = rs.getString("title");
				datatype = rs.getString("datatype");
				size = rs.getInt("size");
				precision = rs.getInt("precision");
				width = rs.getInt("width");
				format = rs.getString("format");
				align = rs.getInt("align");
				return rs.getInt("column_no");
			}
			
			// not found
			throw new ExecException(RuntimeExceptionKinds.WrongName);
		}
		catch (SQLException e) {
			return 0;
		}
	}

	/**
	 * Checks if possible to flush deleted columns from table and does it
	 * @throws ExecException
	 */
	protected void flushDeletedColumns() throws ExecException {
		// it'll be too long to perform
		if (deleted_column_count == 0 || row_count > 1000)
			return;
		
		try
		{
			String sql_create="CREATE TABLE ["+table_name+"_new] ([row_id] int PRIMARY KEY";
			String sql_fields="[row_id]";

			ResultSet rs = mem_db.query("SELECT * FROM ["+table_name+"_schema] ORDER BY column_no");
			while (rs.next()) {
				int column_id = rs.getInt("column_id");

				sql_create += ", [c"+column_id+"] text NULL";
				sql_fields += ", [c"+column_id+"]";
			}

			sql_create += ")";
			mem_db.exec(sql_create);

			mem_db.exec("INSERT INTO ["+table_name+"_new] ("+sql_fields+") SELECT "+sql_fields+" FROM ["+table_name+"]");
			
			mem_db.exec("DROP TABLE ["+table_name+"]");
			
			mem_db.exec("ALTER TABLE ["+table_name+"_new] RENAME TO ["+table_name+"]");
			
			mem_db.commit();

			// another life?
			deleted_column_count = 0;
			if (column_count == 0) {
				column_id_last = 0;
			}
		}
		catch (SQLException e) {
			try { mem_db.rollback(); } catch (SQLException err) { }
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}
	}
	
	/**
	 * Deletes column by {@code column_no}
	 * @throws ExecException 
	 */
	public Integer deleteColumn(int column_no) throws ExecException {
		SQL sql = new SQL();

		// not found?
		if (column_no < 1 || column_no>column_count) {
			throw new ExecException(RuntimeExceptionKinds.WrongIndex);
		}
		
		deleted_column_count++;
		try {
			mem_db.begintran();

			sql.setSQL("SELECT [column_id] FROM ["+table_name+"_schema] WHERE [column_no]=:column_no");
			sql.set("column_no", column_no);
			int column_id = Integer.parseInt(mem_db.scalar(sql.toString()));
			
			sql.setSQL("DELETE FROM ["+table_name+"_schema] WHERE [column_id]=:column_id");
			sql.set("column_id", column_id);
			mem_db.exec(sql);
		
			sql.setSQL("UPDATE ["+table_name+"_schema] SET [column_no]=[column_no]-1 WHERE [column_no]>:column_no");
			sql.set("column_no", column_no);
			mem_db.exec(sql);

			mem_db.exec("ALTER TABLE ["+table_name+"] RENAME COLUMN [c"+column_id+"] TO [d"+deleted_column_count+"]");
			
			mem_db.commit();
		}
		catch (SQLException e) {
			try { mem_db.rollback(); } catch (SQLException err) { }
			deleted_column_count--;
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}

		flushDeletedColumns();
		
		column_count--;
		return column_count;
	}

	/**
	 * @return the cursor_column
	 */
	public Integer getCursorColumn() {
		return cursor_column;
	}

	/**
	 * @param cursor_column the cursor_column to set
	 */
	public void setCursorColumn(int cursor_column) {
		this.cursor_column = cursor_column;
	}

	/**
	 * @return the cursor_row
	 */
	public Integer getCursorRow() {
		return cursor_row;
	}

	/**
	 * @param cursor_row the cursor_row to set
	 */
	public void setCursorRow(int cursor_row) {
		this.cursor_row = cursor_row;
	}

	/**
	 * @return the column_count
	 */
	public Integer getColumnCount() {
		return column_count;
	}

	/**
	 * @return the row_count
	 */
	public Integer getRowCount() {
		return row_count;
	}

	/**
	 * Adds new row
	 * @return the row_count
	 * @throws ExecException 
	 */
	public Integer addRow() throws ExecException {
		return insertRow(row_count+1);
	}

	/**
	 *  Inserts new row into the specified position
	 * @return the row_count
	 * @throws ExecException 
	 */
	public Integer insertRow(int row_no) throws ExecException {
		SQL sql = new SQL();

		if (row_no > row_count+1)
			row_no = row_count+1;
		
		row_id_last++;
		row_count++;
		try {
			mem_db.begintran();

			if (row_no < row_count) {
				sql.setSQL("UPDATE ["+table_name+"] SET [row_no]=[row_no]+1 WHERE [row_no]>=:row_no");
				sql.set("row_no", row_no);
				mem_db.exec(sql);
			}

			sql.setSQL("INSERT INTO ["+table_name+"]"+
					"([row_id],[row_no])"+
					"VALUES(:row_id,:row_no)"
				);
			sql.set("row_id", row_id_last);
			sql.set("row_no", row_no);
			mem_db.exec(sql);

			mem_db.commit();
}
		catch (SQLException e) {
			try { mem_db.rollback(); } catch (SQLException err) { }
			row_count--;
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}
		return row_count;
	}
	
	
	/**
	 * Deletes a row by {@code row_no}
	 * @throws ExecException 
	 */
	public Integer deleteRow(int row_no) throws ExecException {
		SQL sql = new SQL();

		// not found?
		if (row_no < 1 || row_no>row_count) {
			throw new ExecException(RuntimeExceptionKinds.WrongIndex);
		}
		
		row_count--;
		try {
			mem_db.begintran();

			sql.setSQL("DELETE FROM ["+table_name+"] WHERE [row_no]=:row_no");
			sql.set("row_no", row_no);
			mem_db.exec(sql);

			if (row_no > row_count) {
				sql.setSQL("UPDATE ["+table_name+"] SET [row_no]=[row_no]-1 WHERE [row_no]>:row_no");
				sql.set("row_no", row_no);
				mem_db.exec(sql);
			}

			mem_db.commit();
		}
		catch (SQLException e) {
			row_count++;
			try { mem_db.rollback(); } catch (SQLException err) { }
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}

		flushDeletedColumns();

		return row_count;
	}
	
	/**
	 * Deletes all rows
	 * @throws ExecException 
	 */
	public Boolean deleteRows() throws ExecException {
		// is empty?
		if (row_count==0) {
			return false;
		}
		
		SQL sql = new SQL();

		try {
			sql.setSQL("DELETE FROM ["+table_name+"]");
			mem_db.exec(sql);
		}
		catch (SQLException e) {
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}

		row_count=0;
		flushDeletedColumns();

		return true;
	}
	
	/**
	 * Resets a cursor position
	 * @throws ExecException 
	 */
	public Boolean cursorReset() throws ExecException {
		// is empty?
		if (row_count==0) {
			return false;
		}
		
		cursor_row=0;
		return true;
	}
	
	/**
	 * Moves a cursor to a next row
	 * @throws ExecException 
	 */
	public Boolean cursorFetch() throws ExecException {
		// is empty?
		if (cursor_row < 1 || cursor_row > row_count) {
			return false;
		}
		
		cursor_row++;
		return true;
	}
	
	/**
	 * Moves a cursor to a specified row
	 * @throws ExecException 
	 */
	public Boolean cursorSet(Integer new_pos) throws ExecException {
		// is missed?
		if (new_pos < 1 || new_pos > row_count) {
			return false;
		}
		
		cursor_row=new_pos;
		return true;
	}
	
	/**
	 * Opens a dialog to select a row
	 * @throws ExecException 
	 */
	public Integer browse() throws ExecException {
		// TODO
		return null;
	}
	
	/**
	 * Moves a specified row up or down by {@code delta} rows
	 * @throws ExecException 
	 */
	public Boolean moveRow(Integer delta, Integer row_no) throws ExecException {
		// need move?
		if (delta==0) {
			return false;
		}

		SQL sql = new SQL();

		try {
			sql.setSQL("UPDATE ["+table_name+"] SET [row_no]=-[row_no] WHERE [row_no]=:row_no");
			sql.set("row_no", row_no);
			mem_db.exec(sql);


			sql.setSQL("UPDATE ["+table_name+"] SET [row_no]=[row_no]+:delta WHERE [row_no]>=:row_no1 AND [row_no]<=:row_no2");
			if (delta > 0) {
				sql.set("row_no1", row_no);
				sql.set("row_no2", row_no+delta);
			} else {
				sql.set("row_no1", row_no+delta);
				sql.set("row_no2", row_no);
			}
			sql.set("delta", delta);
			mem_db.exec(sql);
		}
		catch (SQLException e) {
			throw new ExecException(RuntimeExceptionKinds.OutOfMemory, e.getMessage());
		}

		return true;
	}
	
	/**
	 * Moves the row under the cursor up or down by {@code delta} rows
	 * @throws ExecException 
	 */
	public Boolean moveRow(Integer delta) throws ExecException {
		return moveRow(delta, cursor_row);
	}
	
}
