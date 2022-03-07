package Exec.Root.Modules;

import java.util.HashMap;

public interface RootCode_Text {
	/*
	 * Get method's name by its alias
	 * Returns null if method doesn't exists or access denied
	 */
	public default void Init_(HashMap<String,String> map) {
		map.put("СтрДлина", "strLen");
		map.put("Найти", "strPos");
		map.put("СокрЛ", "strTrimL");
		map.put("СокрП", "strTrimR");
		map.put("СокрЛП", "strTrim");
/*
ПустаяСтрока
Лев
Прав
Сред
СтрЗаменить (стр, что, на что)
СтрЧислоВхождений (стр, подстр)
СтрКоличествоСтрок
СтрПолучитьСтроку
Врег
Нрег
OemToAnsi
AnsiToOem
Симв
КодСимв
*/
	}

	/*
	 * Returns the index within the string of the first occurrence of the specified substring
	 * (0 if is not found)
	 */
	public default int strPos(String str, String substr) {
		return 1+str.indexOf(substr);
	}

	/*
	 * Return the length of the string
	 */
	public default int strLen(String str) {
		return str.length();
	}

	/*
	 * Returns a string whose value is this string, with all leading space removed
	 * (ascii=32 only)
	 */
	public default String strTrimL(String str) {
	    int i = 0;
	    while (i<str.length() && str.charAt(i)==' ') {
	        i++;
	    }
	    return str.substring(i);
	}

	/*
	 * Returns a string whose value is this string, with all trailing space removed
	 * (ascii=32 only)
	 */
	public default String strTrimR(String str) {
	    int j = str.length()-1;
	    while (j>=0 && str.charAt(j)==' ') {
	        j--;
	    }
	    return str.substring(0,j+1);
	}

	/*
	 * Returns a string whose value is this string, with all leading and trailing space removed
	 * (ascii=32 only)
	 */
	public default String strTrim(String str) {
	    int i = 0;
	    while (i<str.length() && str.charAt(i)==' ') {
	        i++;
	    }
	    int j = str.length()-1;
	    while (j>=0 && str.charAt(j)==' ') {
	        j--;
	    }
	    return str.substring(i,j+1-i);
	}
}
