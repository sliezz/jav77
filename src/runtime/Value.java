package runtime;

import java.sql.Date;
import java.util.HashMap;

import DbObject.DbObject;
import enums.DataType;
import enums.DbEngines;

public class Value extends ValueInfo {
	protected String str;
	protected Number num;
	protected Date date;
	protected DbObject object;

	/**
	 * Create new Value by the specified value
	 * @return new Value object
	 */
	static public Value create(String val) {
		Value v = new Value();
		v.set(val);
		return v;
	}

	/**
	 * Create new Value by the specified value
	 * @return new Value object
	 */
	static public Value create(Number val) {
		Value v = new Value();
		v.set(val);
		return v;
	}

	/**
	 * Create new Value by the specified value
	 * @return new Value object
	 */
	static public Value create(int val) {
		Value v = new Value();
		v.set(val);
		return v;
	}

	/**
	 * Create new Value by the specified value
	 * @return new Value object
	 */
	static public Value create(Date val) {
		Value v = new Value();
		v.set(val);
		return v;
	}

	/**
	 * Create new Value by the specified value
	 * @return new Value object
	 */
	static public Value create(DbObject val) {
		Value v = new Value();
		v.set(val);
		return v;
	}

	/**
	 * Create new Value by the specified value
	 * @return new Value object
	 */
	static public Value create(Object val) throws Exception {
		Value v = new Value();
		v.set(val);
		return v;
	}
	
	
	/**
	 * Clears the object 
	 */
	public void clear() {
		type = DataType.Empty;
		str=null;
		num=null;
		date=null;
		object=null;
	}
	
	/**
	 * Translates String into Value 
	 */
	public void set(String val) {
		clear();
		type = DataType.String;
		str = val;
	}

	/**
	 * Translates Number into Value 
	 */
	public void set(Number val) {
		clear();
		type = DataType.Numeric;
		num = val;
	}

	/**
	 * Translates int into Value
	 */
	public void set(int val) {
		clear();
		type = DataType.Numeric;
		num = new Number(val);
	}

	/**
	 * Translates double into Value
	 */
	public void set(Date val) {
		clear();
		type = DataType.Date;
		date = val;
	}

	/**
	 * Encapsulates DbObject into Value
	 */
	public void set(DbObject val) {
		clear();
		type = DataType.Date;
		object = val;
	}

	/**
	 * Encapsulates DbObject into Value
	 */
	public void set(Object obj) throws Exception {
		String className = obj.getClass().getName();
		if (className == "java.lang.String") {
			set((String)obj);
			return;
		}
		if (className == "java.lang.Number") {
			set((Number)obj);
			return;
		}
		if (className == "java.sql.Date") {
			set((Date)obj);
			return;
		}
		throw new Exception("Unsupported type "+className+" for Value::set()");
	}

	/**
	 * Returns string's representation of Value
	 */
	public String toString() {
		switch (getType()) {
		case String:
			return str;
		case Numeric:
			if (scale == 0) {
				return num.toString();
			}
			return num.toString(scale, precision);
		default:
			return object.toString();
		}
	}
	public Number getAsNumeric() {
		if (getType() != DataType.Numeric) return Number.ZERO;
		return num;
	}
	public Date getAsDate() {
		if (getType() != DataType.Date) return null;
		return date;
	}
	public DbObject getAsObject() {
		if (getType() != DataType.Object) return null;
		return object;
	}

	/**
	 * Returns string's representation of Value for Table
	 */
	public String toInternalString(IndexedStack objects) {
		switch (getType()) {
		case Empty:
			return "";
		case String:
			return str+":S";
		case Numeric:
			if (scale == 0) {
				return num.toString()+"::N";
			}
			return num.toString(scale, precision)+":"+String.valueOf(scale)+","+String.valueOf(precision)+":N";
		case Date:
			return date.toString() +":D";
		default:
			return object.toString()+":"+objects.put(this)+":O";
		}
	}

	/**
	 * Returns SQL's representation of Value
	 */
	public String getAsExprSQL(DbEngines engine) {
		switch (getType()) {
		case String:
			return "'"+str.replace("'","''")+"'";
		case Numeric:
			return num.toString(scale, precision);
		default:
			return null;
		}
	}
}
