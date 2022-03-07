package Exec.Root;

import Exec.Base.Module;

public class Root extends Module {
	@Override
	public void Init() {
		vars = new RootVars();
		vars.Init();
		code = new RootCode();
		code.Init();
		code.Attach(this);
	}
}
