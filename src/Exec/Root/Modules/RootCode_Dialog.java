package Exec.Root.Modules;

import java.util.HashMap;

public interface RootCode_Dialog {
	/*
	 * Get method's name by its alias
	 * Returns null if method doesn't exists or access denied
	 */
	public default void Init_Dialog(HashMap<String,String> map) {
/*
// ?????
??????			(??????, ?????, [???????])
??????????????	(??????, [???????])

// ???????
??????????????	(????????, ?????????, ???, ?????, ????????)
???????????		(?????, ?????????, ?????, ????????, [???????])
????????????	(??????, ?????????, ?????, [?????????????=0], [???????])
??????????		(????, ?????????, [???????])
????????????	(????1, ????2, ?????????)
??????????????????	(????????, ?????????, [???????])

// ????????????? ????
????????????????	(???? ??????, ?????????, [???????=0]) = 1=??, 0=??????, -1=???????
?????????????????	(??? ????????, ?????????, [???????=0]) = 1=??, 0=??????, -1=???????

// ???????????
???????????? (????????, [????????=null], [????? ?????????=0]) ???  (??? ?????????, [????????=null], [???????? ?????????]) ???  (??? ???????????, [????????=null], [????????], [??? ??????], [????????]) ???  (??? ???????, [????????=null]) ???  ("?????", [????????=null], ??? ?????) ???  ("????????" ??? "??????????????.???" ??? "??????????????.???", [????????=null], ????????, [????? ????????], [????? ??????????????? ????????? ????????], [????? ?????????=0]) ???  ("????????", [????????=null], ??? ??????? ????????) ??? ("???????.?????????.???",,,????) ??? ("???????.??????????.???",,??????? ??????????? ??? ????,????) 
???????????????????? (??. ???????????? ????)
*/
	}
}
