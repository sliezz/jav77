package DbObject;

import runtime.Value;

public class DbField {
	public String db_name;
	public String name;
	protected Value value;
	
	public Value get() {
		return value;
	}
	public void set(Value val) {
		value = val;
	}
}
