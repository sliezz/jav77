package Exec.Base;

public abstract class Vars {
	public void Init() {
	}

	public void Release() {
	}

	abstract public Object Get(String varName) throws Exception;
	abstract public void Set(String varName, Object newValue) throws Exception;
}
