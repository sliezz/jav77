package Exec.Root.Modules;

import java.util.HashMap;

public interface RootCode_Documents {
	/*
	 * Get method's name by its alias
	 * Returns null if method doesn't exists or access denied
	 */
	public default void Init_Documents(HashMap<String,String> map) {
/*
// Позиция документа
СформироватьПозициюДокумента	(Докум, Флаг смещения (-1=предыдущая позиция, 0=текущая позиция, 1=следующая позиция))
СформироватьПозициюДокумента	(Дата,Часы,Минуты,Секунды,Флаг смещения (0=текущая секунда, 1=следующая секунда))
РазобратьПозициюДокумента		(Позиция, Дата, Часы, Минуты, Секунды, Документ)
*/
	}
}
