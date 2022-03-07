package Exec.Base;

public class Module {
	public Code code;
	public Vars vars;
	
	public void Init() {
	}

	public void Release() {
		if (vars != null) {
			vars.Release();
			vars = null;
		}
		if (code != null) {
			code.Release();
			code = null;
		}
	}

	public Object Exec(String method, Object... args) throws Exception {
		if (code == null) throw new Exception("code is uninitialized");
		return code.Exec(method, args);
	}

	public Object Get(String var) throws Exception {
		if (vars == null) throw new Exception("vars is uninitialized");
		return vars.Get(var);
	}

	public void Set(String var, Object newValue) throws Exception {
		if (vars == null) throw new Exception("vars is uninitialized");
		vars.Set(var, newValue);
	}
}
