package Exec.Root.Modules;

import java.util.HashMap;

public interface RootCode_Registry {
	/*
	 * Get method's name by its alias
	 * Returns null if method doesn't exists or access denied
	 */
	public default void Init_Registry(HashMap<String,String> map) {
/*
// Оперативный учет
ПолучитьТА
ПолучитьДатуТА
ПолучитьВремяТА	([Часы],[Минуты],[Секунды]) = "ЧЧ:ММ:СС"
ПолучитьДокументТА
ПолучитьПозициюТА
УстановитьТАна	(дата/документ/позиция)
УстановитьТАпо	(дата/документ/позиция)
РассчитатьРегистрыНа	(дата/документ/позиция, [графа отбора/*=''])
РассчитатьРегистрыПо	(дата/документ/позиция, [графа отбора/*=''])
*/
	}
}
