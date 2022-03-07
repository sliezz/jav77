package SQL;

import java.util.HashMap;
import enums.DbEngines;
import runtime.Value;
import java.sql.SQLException;

/**
 * Build an SQL statement by replacing every ':ParamName' string with its values
 * @author sliezzz
 */
public class SQL {
	private DbEngines engine;
	private String sql;
	private HashMap<String, String> consts = new HashMap<String, String>();	// persistent parameters
	private HashMap<String, Value> params = new HashMap<String, Value>();	// one-time parameters
	private HashMap<String, String> params2 = new HashMap<String, String>();	// one-time parameters

	/**
	 *  Initialize with defaults
	 */
	public SQL() {
		engine = DbEngines.MSSQL;
		sql = "";
	}

	/**
	 *  Initialize with SQL statement
	 * @param sql
	 */
	public SQL(String sql) {
		setSQL(sql);
	}

	/**
	 *  Initialize with DbEngine without default SQL statement
	 * @param engine
	 */
	public SQL(DbEngines engine) {
		setEngine(engine);
	}

	/**
	 *  Initialize with DbEngine without default SQL statement
	 * @param engine
	 */
	public SQL(String sql, DbEngines engine) {
		setSQL(sql);
		setEngine(engine);
	}

	/**
	 * Setup engine
	 * @param engine
	 */
	private void setEngine(DbEngines engine) {
		consts.put("ПустойИД", "     0   ");
		consts.put("ПустойИД13", "   0     0   ");
		if (engine == DbEngines.MSSQL) {
			consts.put("ПустаяДата", "{d '1753-01-01'}");
		} else {
			consts.put("ПустаяДата", "'1753-01-01'");
		}
	}

	/**
	 *  Clear all stored parameters
	 */
	public void clear() {
		params.clear();
	}

	/**
	 *  Link the value to parameter's name
	 * @param name The name of the parameter
	 * @param value The value of the parameter
	 * @return status
	 */
	public boolean set(String name, Value value) {
		if (params.put(name, value) == null) {
			return false;
		}
		return true;
	}

	/**
	 *  Link the value to parameter's name (string)
	 * @param name The name of the parameter
	 * @param value The value of the parameter
	 * @return status
	 */
	public boolean set(String name, String value) {
		String _value = "'" + value.replace("'", "''") + "'";
		if (params2.put(name, _value) == null) {
			return false;
		}
		return true;
	}

	/**
	 *  Link the value to parameter's name (int)
	 * @param name The name of the parameter
	 * @param value The value of the parameter
	 * @return status
	 */
	public boolean set(String name, int value) {
		String _value = String.valueOf(value);
		if (params2.put(name, _value) == null) {
			return false;
		}
		return true;
	}

	/**
	 *  Link the value to parameter's name (long)
	 * @param name The name of the parameter
	 * @param value The value of the parameter
	 * @return status
	 */
	public boolean set(String name, long value) {
		String _value = String.valueOf(value);
		if (params2.put(name, _value) == null) {
			return false;
		}
		return true;
	}

	/**
	 *  Link the value to parameter's name (boolean)
	 * @param name The name of the parameter
	 * @param value The value of the parameter
	 * @return status
	 */
	public boolean set(String name, boolean value) {
		String _value = (value ? "1" : "0");
		if (params2.put(name, _value) == null) {
			return false;
		}
		return true;
	}

	/**
	 *  Get stored value of the parameter
	 * @param name The name of the parameter
	 * @return The value of the parameter
	 */
	public Value get(String name) {
		return params.get(name);
	}

	/**
	 *  Initialize with another SQL statement
	 * @param sql
	 * @return previous SQL statement
	 */
	public String setSQL(String new_sql) {
		String old_sql = sql; 
		sql = new_sql;
		return old_sql;
	}

	/**
	 *  Build SQL statement by replacing every ':ParamName' string with its values
	 * @return Resulted SQL statement
	 * @throws SQLException if one of the parameters are missed
	 */
	public String toString() {
		return toString(false);
	}

	/**
	 *  Build SQL statement by replacing every ':ParamName' string with its values
	 * @param log_queries Set to true if you want to dump resulted SQL statement
	 * @return Resulted SQL statement
	 * @throws SQLException if one of the parameters are missed
	 */
	public String toString(boolean log_queries) {
		String final_sql, tag;
		boolean is_comment = false;
		boolean is_string = false;
		int i, j;
		char ch, ch2;
		
		final_sql = "";
		for (i=0; i<sql.length(); i++) {
			ch = sql.charAt(i);
			if (is_comment) {
				if (sql.length()>i+1) {
					ch2 = sql.charAt(i+1);
				} else {
					ch2 = 0;
				}
				if (ch == '*' && ch2 == '/') {
					is_comment=false;
				}
				final_sql = final_sql + ch;
			}
			else if (!is_string && ch == ':') {
				tag="";
				for(j=i+1; j<sql.length(); j++) {
					ch = sql.charAt(j);
					if (ch=='_' || Character.isLetter(ch) || (tag.length()>0 && Character.isDigit(ch))) {
						tag += ch;
					} else {
						break;
					}
				}
				if (params.containsKey(tag)) {
					i+=tag.length();
					final_sql = final_sql + params.get(tag).getAsExprSQL(engine);
				} else if (params2.containsKey(tag)) {
					i+=tag.length();
					final_sql = final_sql + params2.get(tag);
				} else {
					throw new RuntimeException(runtime.Runtime.lang.MissedParameter(tag));
				}
			}
			else {
				if (sql.length()>i+1) {
					ch2 = sql.charAt(i+1);
				} else {
					ch2 = 0;
				}
				if (ch == '/' && ch2 == '*') {
					is_comment = true;
				}
				else if (is_string && ch == '\'' && ch2 == '\'') {
					i++;
				}
				else if (ch == '\'') {
					is_string=!is_string;
				}
				final_sql = final_sql + ch;
			}
		}
		
		if (log_queries) {
			System.out.println(final_sql);
		}
		return final_sql;
	}

}
/*
public class SQL {
	private DbEngines dbe = DbEngines.MSSQL;
	private String sql;
	private HashMap<String, String> consts = new HashMap<String, String>();	// persistent parameters
	private HashMap<String, Value> params = new HashMap<String, Value>();	// one-time parameters

	SQL(DbEngines engine) {
		setEngine(engine);
	}

	SQL(String sql, DbEngines engine) {
		setEngine(engine);
		setSQL(sql);
	}

	void setEngine(DbEngines engine) {
		consts.put("ПустойИД", "     0   ");
		consts.put("ПустойИД13", "   0     0   ");
		if (engine == DbEngines.MSSQL) {
			consts.put("ПустаяДата", "{d '1753-01-01'}");
		} else {
			consts.put("ПустаяДата", "'1753-01-01'");
		}
	}

	void setSQL(String new_sql) {
		sql = new_sql;
	}

	boolean setParam(String param, Value value) {
		if (params.put(param, value) == null) {
			return false;
		}
		return true;
	}

	Value getParam(String param) {
		return params.get(param);
	}

	void build(String new_sql) {
		setSQL(new_sql);
		build();
	}

	void build() {
		;
	}
}
*/