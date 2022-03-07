package DbObject;

import runtime.ValueInfo;

public abstract class Factory {
	public abstract DbObject create(ValueInfo vi);
	public DbObject createByName(String name, Boolean editable) {
		//TODO
		return null;
		
	}
}
