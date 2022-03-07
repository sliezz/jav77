package Exec.Root.Modules;

import java.util.HashMap;

public interface RootCode_Accounting {
	/*
	 * Get method's name by its alias
	 * Returns null if method doesn't exists or access denied
	 */
	public default void Init_Accounting(HashMap<String,String> map) {
		map.put("�������������������", "Accounting_GetSelected");
		map.put("������������������", "Accounting_GetDefault");
		map.put("����������", "Accounting_GetByCode");	// (��� �����, [���� ������])
		map.put("���������������", "Accounting_GetDate1");
		map.put("��������������", "Accounting_GetDate2");
		map.put("���������������������������", "Accounting_GetLastDate");
		map.put("������������������������������", "Accounting_GeSubcontoCountMax");
		map.put("���������������������������", "Accounting_GetLastDate");	// (��� ��������, ����, [����� ��������])
	}
}
