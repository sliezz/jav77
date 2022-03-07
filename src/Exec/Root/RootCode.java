package Exec.Root;

import Exec.Base.Code;
import Exec.Root.Modules.*;

public class RootCode extends Code implements RootCode_Accounting, RootCode_Base {

	@Override
	public void Init() {
		Init_Accounting(mapMethods);
		Init_Base(mapMethods);
	}
}
