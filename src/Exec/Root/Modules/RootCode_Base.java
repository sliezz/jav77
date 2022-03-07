package Exec.Root.Modules;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.HashMap;

import Table.Table;
import enums.Lang;
import enums.RootHandlers;
import runtime.ObjectsList;
import runtime.Runtime;
import runtime.RuntimeObject;

public interface RootCode_Base {
	/*
	 * Get method's name by its alias
	 * Returns null if method doesn't exists or access denied
	 */
	public default void Init_Base(HashMap<String,String> map) {
		// (строка, [маркер])
		// Вывести строку в окно сообщений. Перед сообщениями можно отображать специальные пиктограммы, которыми можно помечать сообщения различной важности.
		map.put("Сообщить", "outputString");
		map.put("Message", "outputString");

		// ()
		// Очистить окно сообщений.
		map.put("ОчиститьОкноСообщений", "clearOutputWindow");
		map.put("ClearMessageWindow", "clearOutputWindow");

		// ([строка])
		// Вывести сообщение в строку состояния.
		map.put("Состояние", "setStatusText");		
		map.put("Status", "setStatusText");

		// ()
		// Вывести звуковой сигнал.
		map.put("Сигнал", "beep");
		map.put("Beep", "beep");

		// (массив)
		// Возвращает размерность массива.
		map.put("Разм", "getArrayLen");
		map.put("Dim", "getArrayLen");

		// ([заголовок])
		// Позволяет получить/установить заголовок окна программы. Возвращает строковое значение - заголовок окна программы.
		map.put("ЗаголовокСистемы", "setTitle");
		map.put("SystemCaption", "setTitle");

		// ()
		// Возвращает сетевое имя компьютера, работающего в данный момент с программой.
		map.put("ИмяКомпьютера", "getPCName");
		map.put("ComputerName", "getPCName");

		// ()
		// Возвращает имя пользователя
		map.put("ИмяПользователя", "getLogin");
		map.put("UserName", "getLogin");

		// ()
		// Возвращает полное имя пользователя
		map.put("ПолноеИмяПользователя", "getUserName");
		map.put("UserFullName", "getUserName");

		// ([Игнорировать отключение прав доступа=0])
		// Возвращает название набора прав пользователя.
		map.put("НазваниеНабораПрав", "getAccessGroup");
		map.put("RightName", "getAccessGroup");

		// (Название права, [Объект])
		// Проверяет для текущего пользователя наличие права доступа для заданного объекта. Возвращает: 1 - если право доступа есть, иначе 0.
		map.put("ПравоДоступа", "hasAccess");
		map.put("AccessRight", "hasAccess");

		// ()
		// Возвращает название интерфейса пользователя.
		map.put("НазваниеИнтерфейса", "getUIName");
		map.put("UserInterfaceName", "getUIName");

		// ()
		// Возвращает имя каталога пользователя.
		map.put("КаталогПользователя", "getHome");
		map.put("UserDir", "getHome");

		// ()
		// Возвращает имя каталога базы данных.
		map.put("КаталогИБ", "getBaseDir");
		map.put("IBDir", "getBaseDir");

		// ()
		// Возвращает имя каталога с исполняемыми файлами системы
		map.put("КаталогПрограммы", "getRootDir");
		map.put("BinDir", "getRootDir");

		// ()
		// Возвращает имя каталога временных файлов.
		map.put("КаталогВременныхФайлов", "getTempDir");
		map.put("TempFilesDir", "getTempDir");

		// ()
		// Возвращает значение режима работы программы: 1 - программа запущена в монопольном режиме; 0 - программа запущена в сетевом режиме.
		map.put("МонопольныйРежим", "amIAlone");
		map.put("ExclusiveMode", "amIAlone");

		// ()
		// Возвращает: 1, если основной язык конфигурации - русский; 0, если основной язык конфигурации - английский
		map.put("ОсновнойЯзык", "isInRussian");
		map.put("GeneralLanguage", "isInRussian");

		// (ЖР)
//TODO		map.put("ОсновнойЖурналРасчетов", "SetBrowserForCalculations");
//TODO		map.put("BasicCalcJournal", "SetBrowserForCalculations");

		// (имя типа)
		// Создает объект агрегатного типа данных.Возвращает: ссылку на созданный объект агрегатного типа данных.
		map.put("СоздатьОбъект", "createObject");
		map.put("CreateObject", "createObject");

		// СтатусВозврата	([новый статус])
		// Определение или установка статуса возврата предопределенной процедуры.
		map.put("СтатусВозврата", "setReturnCode");
		map.put("ReturnStatus", "setReturnCode");

		// (объект)
		// Возвращает: тип данных значения в виде числа.
		map.put("ТипЗначения", "GetTypeCode");
		map.put("ValueType", "GetTypeCode");

		// (объект)
		// Возвращает строковое обозначение типа данных.
		map.put("ТипЗначенияСтр", "getTypeString");
		map.put("ValueTypeStr", "getTypeString");

		// (объект)
		// Функция определяет, является ли пустым переданное в параметре значение
		map.put("ПустоеЗначение", "IsEmpty");
		map.put("EmptyValue", "IsEmpty");

		// (имя типа) - необязательный параметр: строка или вид субконто или объект метаданных, задающий тип данных.
		// Возвращает пустое значение заданного типа.
		map.put("ПолучитьПустоеЗначение", "GetEmpty");
		map.put("GetEmptyValue", "GetEmpty");

		// (Значение, [Вид])
		// Процедура НазначитьВид используется для установки значениям типа "Документ неопределенного вида", "Справочник неопределенного вида", "Счет неопределенного вида" конкретного вида.
//TODO		map.put("НазначитьВид", "AssignType");
//TODO		map.put("SetKind", "AssignType");

		// (ИмяВида, [Префикс])
		// Установить префикс для автоматического создания новых номеров.
//TODO		map.put("ПрефиксАвтоНумерации", "SetPrefix");
//TODO		map.put("AutoNumPrefix", "SetPrefix");

		// (Имя отбора, Список значений, [Дата начала], [Дата окончания]) = 1 или 0
		// 		ИмяОтбора => строковое выражение с полным названием общего реквизита документа или графы отбора из конфигурации
		// 		СписокЗначений => имя переменной, в которой возвращается объект типа "СписокЗначений"
		// 		ДатаНач => дата начала интервала, в котором проводится отбор (если не задан, то отбор производится по всем данным)
		// 		ДатаКон => дата конца интервала, в котором проводится отбор (если не задан или равен 0, то отбор производится до ТА)
		// Выбирать все существующие значения отбора.
//TOOD		map.put("ПолучитьЗначенияОтбора", "FindUsedValues");
//TOOD		map.put("GetSelectionValues", "FindUsedValues");

		// (Комментарий, [Тип события="Дополнительные события"], [Событие="Дополнительное событие"], [Объект], [Категория (1-Администрирование,2=Изменение данных,3-Информация,4-Предупреждение,5-Ошибка)=3])
		//		Коммент => Строковое выражение, комментарий к событию
		//		ТипСобытия => Строковое выражение - тип события
		//		Событие => Строковое выражение - событие
		//		Объект => Объект события, по умолчанию отсутствует
		//		Категория => Число - категория события
		// Выводит строку текста в системный журнал регистрации.
		map.put("ЗаписьЖурналаРегистрации", "logEvent");
		map.put("LogMessageWrite", "logEvent");

		// (строка команды)
		// Вызывает на исполнение команду DOS.
		map.put("КомандаСистемы", "shellExec");
		map.put("System", "shellExec");

		// (путь к исполняемому файлу или документу)
		// Выполняет запуск любого приложения или открытие файла зарегистрированным приложением
		map.put("ЗапуститьПриложение", "openURI");
		map.put("RunApp", "openURI");

		// ([Вопрос сохранения=1])
		// вызывает завершение рабоы системы.
		map.put("ЗавершитьРаботуСистемы", "Quit");
		map.put("ExitSystem", "Quit");

		// (Список значений)
		// Находит все помеченные на удаление объекты и помещает их в переданный список значений
		map.put("НайтиПомеченныеНаУдаление", "FindMarkedObjects");
		map.put("FindMarkedForDelete", "FindMarkedObjects");

		// (Объект или список значений, Таблица значений со ссылками на объекты (колонка1-объект на который ссылка, колонка2-объект, колонка3-комментарий к ссылке))
		//		Объекты => конкретное значение искомого объекта или значение типа "СписокЗначений", в котором данной процедуре передаются объекты, по которым надо найти ссылки.
		//		Ссылки => идентификатор объекта типа "ТаблицаЗначений", в который данная процедура помещает найденные ссылки на объекты.
		// Находит ссылки на объекты, переданные в списке значений.
		map.put("НайтиСсылки", "findReferences");
		map.put("FindReferences", "findReferences");

		// (Объект или список значений, [Проверять=0], [Таблица значений со ссылками на объекты (колонка1-объект на который ссылка, колонка2-объект, колонка3-комментарий к ссылке)])
		//		Объекты => конкретное значение объекта или значение типа ''СписокЗначений'', в котором данной процедуре передаются объекты, которые надо удалить.
		//		Проверять => необязательный параметр. Число: 1 - перед удалением проверяется, нет ли ссылок на удаляемый объект.
		//		Ссылки => Необязательный параметр. Идентификатор объекта типа ''ТаблицаЗначений'', в который данная процедура помещает найденные ссылки на объекты.
		// Удаляет объекты, переданные в списке значений.
		map.put("УдалитьОбъекты", "deleteObjects");
		map.put("DeleteObjects", "deleteObjects");

		// (Имя процедуры, [Интервал вызова в секундах или 0-остановить=0])
		// Инициирует периодический вызов процедуры глобального модуля с заданным интервалом времени.
		map.put("ОбработкаОжидания", "setTimer");
		map.put("IdleProcessing", "setTimer");

		// ()
		// Возвращает описание ошибки времени выполнения модуля, такое же, как то, которое выдается в окне сообщений.
		map.put("ОписаниеОшибки", "getLastError");
		map.put("GetErrorDescription", "getLastError");
	}

	/* 
	 * Outputs the string with default marker
	 * Origin: Сообщить(строка)
	 */
	public default void outputString(String text) {
		outputString(text, ".");
	}

	/* 
	 * Outputs the string with the specified marker
	 * Origin: Сообщить(строка, маркер)
	 */
	public default void outputString(String text, String marker) {
		Runtime.logView.getItems().add(text);		

		if (Runtime.splitPane.getDividerPositions()[0] >= 0.99f)
			Runtime.splitPane.setDividerPositions(0.85f, 0.15f);
	}
	
	/*
	 * Clears the output window
	 * Origin: ОчиститьОкноСообщений()
	 */
	public default void clearOutputWindow() {
		Runtime.logView.getItems().clear();
	}

	/*
	 * Gets status text
	 */
	public default String setStatusText() {
		return Runtime.status.getText();
	}

	/*
	 * Sets status text
	 * Origin: Состояние(строка)
	 */
	public default String setStatusText(String text) {
		String old_status = Runtime.status.getText();
		Runtime.status.setText(text);
		return old_status;
	}

	/*
	 * Beeps. Really useless to implement
	 * Origin: Сигнал()
	 */
	public default void Beep() {
	}

	/*
	 *  Gets array's length
	 *  Origin: Разм(массив)
	 */
	public default Integer getArrayLen(Object[] arr) {				
		return arr.length;
	}

	/*
	 * Gets title of the main window
	 * Origin: ЗаголовокСистемы()
	 */
	public default String setTitle() {
		return Runtime.primaryStage.getTitle();
	}

	/*
	 * Sets title of the main window
	 * Origin: ЗаголовокСистемы(заголовок)
	 */
	public default String setTitle(String text) { 
		String old_title = Runtime.primaryStage.getTitle();
		Runtime.primaryStage.setTitle(text);
		return old_title;
	}

	/*
	 * Gets this machine's network name
	 * Origin: ИмяКомпьютера()
	 */
	public default String getPCName() {
		try {
			return java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "";
		}
	}

	/*
	 * Gets current user's login
	 * Origin: ИмяПользователя()
	 */
	public default String getLogin() {
		return Runtime.session.login;
	}

	/*
	 * Gets current user's name
	 * Origin: ПолноеИмяПользователя()
	 */
	public default String getUserName() {
		return Runtime.session.user;
	}

	/*
	 * Gets current user's access group
	 * Origin: НазваниеНабораПрав()
	 */
	public default String getAccessGroup() {	// ([Игнорировать отключение прав доступа=0])
		return Runtime.session.accessGroup;
	}

	/*
	 * Gets current user's UI
	 * Origin: НазваниеИнтерфейса()
	 */
	public default String getUIName() {
		return Runtime.session.ui;
	}

	/*
	 * Gets current user's home folder
	 * Origin: КаталогПользователя()
	 */
	public default String getHome() {
		return Runtime.session.home;
	}

	/*
	 * Gets default temp file path
	 * Origin: КаталогВременныхФайлов()
	 */
	public default String getTempDir() {
		return Runtime.session.tempdir;
	}

	/*
	 * Gets root path
	 * Origin: КаталогПрограммы()
	 */
	public default String getRootDir() {
		return Runtime.session.rootdir;
	}

	/*
	 * Gets base path
	 * Origin: КаталогИБ()
	 */
	public default String getBaseDir() {
		return null;
	}

	/*
	 * Check permission's request to the object
	 * Origin: ПравоДоступа()
	 */
	public default Boolean hasAccess(String request, Object obj) {				// (Название права, Объект)
		return Runtime.permissions.check(request, obj);
	}

	/*
	 * Check if there is other sessions in this database
	 * Origin: МонопольныйРежим()
	 */
	public default Boolean amIAlone() {
		return (Runtime.session.sessionCount()>1) ? false : true;
	}

	/*
	 * Check if default language is Russian
	 * Origin: ОсновнойЯзык()
	 */
	public default Boolean isInRussian() {
		return (Runtime.baseLanguage == Lang.Ru) ? true : false;
	}

	/*
	 * Creates new object
	 * Origin: СоздатьОбъект()
	 */
	public default Object createObject(String name) {
		return Runtime.factory.createByName(name, true);
	}

	/*
	 * Creates an editable object
	 * Origin: СтатусВозврата()
	 */
	public default Integer setReturnCode(Integer newStatusCode) {
		int oldStatusCode = Runtime.StatusCode;
		Runtime.StatusCode = newStatusCode;
		return oldStatusCode;
	}
	public default Integer setReturnCode() {
		return Runtime.StatusCode;
	}

	/*
	 * Returns numeric code which represents object type 
	 * Origin: ТипЗначения()
	 */
	public default Integer getTypeCode(RuntimeObject obj) {
		return obj.getTypeCode();
	}

	/*
	 * Returns string which represents object type 
	 * Origin: ТипЗначенияСтр()
	 */
	public default String getTypeString(RuntimeObject obj) {
		return obj.getTypeString();
	}

	/*
	 * Checks if object is empty 
	 * Origin: ПустоеЗначение()
	 */
	public default Boolean isEmpty(RuntimeObject obj) {
		return obj.isEmpty();
	}

	/*
	 * Returns a non-editable object 
	 * Origin: ПустоеЗначение()
	 */
	public default Object getEmpty(String name) {
		return Runtime.factory.createByName(name, false);
	}

	/*
	 * Quits the application
	 * (without any question if ask=false) 
	 */
	public default void Quit() {
		Quit(true);
	}
	public default void Quit(Boolean ask) {
		if (Runtime.invokeHandler(RootHandlers.OnQuit) != 0) {
			Runtime.getRuntime().stop();
		}
	}

	/*
	 * Returns the last execution error
	 */
	public default String getLastError() {
		return Runtime.LastErrorMessage;
	}

	public default void SetTimer(String proc, Integer interval) {		// (Имя процедуры, [Интервал вызова в секундах или 0-остановить=0])
	}
	public default void SetTimer(String proc) {		// (Имя процедуры)
	}

	public default void logEvent() {		// (Комментарий, [Тип события="Дополнительные события"], [Событие="Дополнительное событие"], [Объект], [Категория (1-Администрирование,2=Изменение данных,3-Информация,4-Предупреждение,5-Ошибка)=3])
	}
	public default void shellExec() {		// (строка команды)
	}
	public default void openURI() {		// (путь к исполняемому файлу или документу)
	}
	public default void FindMarkedObjects(ObjectsList list) {		// (Список значений)
	}
	public default void FindReferences(Object obj, Table ref) {		// (Объект или список значений, Таблица значений со ссылками на объекты (колонка1-объект на который ссылка, колонка2-объект, колонка3-комментарий к ссылке))
	}
	public default void DeleteObjects(Object obj, Boolean check, Table ref) {		// (Объект или список значений, [Проверять=0], [Таблица значений со ссылками на объекты (колонка1-объект на который ссылка, колонка2-объект, колонка3-комментарий к ссылке)])
	}
}
