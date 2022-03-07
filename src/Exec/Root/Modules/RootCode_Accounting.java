package Exec.Root.Modules;

import java.util.HashMap;

public interface RootCode_Accounting {
	/*
	 * Get method's name by its alias
	 * Returns null if method doesn't exists or access denied
	 */
	public default void Init_Accounting(HashMap<String,String> map) {
		map.put("¬ыбранныйѕлан—четов", "Accounting_GetSelected");
		map.put("ќсновнойѕлан—четов", "Accounting_GetDefault");
		map.put("—четѕо оду", "Accounting_GetByCode");	// ( од счета, [ѕлан счетов])
		map.put("ЌачалоѕериодаЅ»", "Accounting_GetDate1");
		map.put(" онецѕериодаЅ»", "Accounting_GetDate2");
		map.put(" онец–ассчитанногоѕериодаЅ»", "Accounting_GetLastDate");
		map.put("ћаксимальное оличество—убконто", "Accounting_GeSubcontoCountMax");
		map.put(" онец–ассчитанногоѕериодаЅ»", "Accounting_GetLastDate");	// (вид субконто, счет, [номер субконто])
	}
}
