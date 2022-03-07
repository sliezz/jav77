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
		// (������, [������])
		// ������� ������ � ���� ���������. ����� ����������� ����� ���������� ����������� �����������, �������� ����� �������� ��������� ��������� ��������.
		map.put("��������", "outputString");
		map.put("Message", "outputString");

		// ()
		// �������� ���� ���������.
		map.put("���������������������", "clearOutputWindow");
		map.put("ClearMessageWindow", "clearOutputWindow");

		// ([������])
		// ������� ��������� � ������ ���������.
		map.put("���������", "setStatusText");		
		map.put("Status", "setStatusText");

		// ()
		// ������� �������� ������.
		map.put("������", "beep");
		map.put("Beep", "beep");

		// (������)
		// ���������� ����������� �������.
		map.put("����", "getArrayLen");
		map.put("Dim", "getArrayLen");

		// ([���������])
		// ��������� ��������/���������� ��������� ���� ���������. ���������� ��������� �������� - ��������� ���� ���������.
		map.put("����������������", "setTitle");
		map.put("SystemCaption", "setTitle");

		// ()
		// ���������� ������� ��� ����������, ����������� � ������ ������ � ����������.
		map.put("�������������", "getPCName");
		map.put("ComputerName", "getPCName");

		// ()
		// ���������� ��� ������������
		map.put("���������������", "getLogin");
		map.put("UserName", "getLogin");

		// ()
		// ���������� ������ ��� ������������
		map.put("���������������������", "getUserName");
		map.put("UserFullName", "getUserName");

		// ([������������ ���������� ���� �������=0])
		// ���������� �������� ������ ���� ������������.
		map.put("������������������", "getAccessGroup");
		map.put("RightName", "getAccessGroup");

		// (�������� �����, [������])
		// ��������� ��� �������� ������������ ������� ����� ������� ��� ��������� �������. ����������: 1 - ���� ����� ������� ����, ����� 0.
		map.put("������������", "hasAccess");
		map.put("AccessRight", "hasAccess");

		// ()
		// ���������� �������� ���������� ������������.
		map.put("������������������", "getUIName");
		map.put("UserInterfaceName", "getUIName");

		// ()
		// ���������� ��� �������� ������������.
		map.put("�������������������", "getHome");
		map.put("UserDir", "getHome");

		// ()
		// ���������� ��� �������� ���� ������.
		map.put("���������", "getBaseDir");
		map.put("IBDir", "getBaseDir");

		// ()
		// ���������� ��� �������� � ������������ ������� �������
		map.put("����������������", "getRootDir");
		map.put("BinDir", "getRootDir");

		// ()
		// ���������� ��� �������� ��������� ������.
		map.put("����������������������", "getTempDir");
		map.put("TempFilesDir", "getTempDir");

		// ()
		// ���������� �������� ������ ������ ���������: 1 - ��������� �������� � ����������� ������; 0 - ��������� �������� � ������� ������.
		map.put("����������������", "amIAlone");
		map.put("ExclusiveMode", "amIAlone");

		// ()
		// ����������: 1, ���� �������� ���� ������������ - �������; 0, ���� �������� ���� ������������ - ����������
		map.put("������������", "isInRussian");
		map.put("GeneralLanguage", "isInRussian");

		// (��)
//TODO		map.put("����������������������", "SetBrowserForCalculations");
//TODO		map.put("BasicCalcJournal", "SetBrowserForCalculations");

		// (��� ����)
		// ������� ������ ����������� ���� ������.����������: ������ �� ��������� ������ ����������� ���� ������.
		map.put("�������������", "createObject");
		map.put("CreateObject", "createObject");

		// ��������������	([����� ������])
		// ����������� ��� ��������� ������� �������� ���������������� ���������.
		map.put("��������������", "setReturnCode");
		map.put("ReturnStatus", "setReturnCode");

		// (������)
		// ����������: ��� ������ �������� � ���� �����.
		map.put("�����������", "GetTypeCode");
		map.put("ValueType", "GetTypeCode");

		// (������)
		// ���������� ��������� ����������� ���� ������.
		map.put("��������������", "getTypeString");
		map.put("ValueTypeStr", "getTypeString");

		// (������)
		// ������� ����������, �������� �� ������ ���������� � ��������� ��������
		map.put("��������������", "IsEmpty");
		map.put("EmptyValue", "IsEmpty");

		// (��� ����) - �������������� ��������: ������ ��� ��� �������� ��� ������ ����������, �������� ��� ������.
		// ���������� ������ �������� ��������� ����.
		map.put("����������������������", "GetEmpty");
		map.put("GetEmptyValue", "GetEmpty");

		// (��������, [���])
		// ��������� ������������ ������������ ��� ��������� ��������� ���� "�������� ��������������� ����", "���������� ��������������� ����", "���� ��������������� ����" ����������� ����.
//TODO		map.put("������������", "AssignType");
//TODO		map.put("SetKind", "AssignType");

		// (�������, [�������])
		// ���������� ������� ��� ��������������� �������� ����� �������.
//TODO		map.put("��������������������", "SetPrefix");
//TODO		map.put("AutoNumPrefix", "SetPrefix");

		// (��� ������, ������ ��������, [���� ������], [���� ���������]) = 1 ��� 0
		// 		��������� => ��������� ��������� � ������ ��������� ������ ��������� ��������� ��� ����� ������ �� ������������
		// 		�������������� => ��� ����������, � ������� ������������ ������ ���� "��������������"
		// 		������� => ���� ������ ���������, � ������� ���������� ����� (���� �� �����, �� ����� ������������ �� ���� ������)
		// 		������� => ���� ����� ���������, � ������� ���������� ����� (���� �� ����� ��� ����� 0, �� ����� ������������ �� ��)
		// �������� ��� ������������ �������� ������.
//TOOD		map.put("����������������������", "FindUsedValues");
//TOOD		map.put("GetSelectionValues", "FindUsedValues");

		// (�����������, [��� �������="�������������� �������"], [�������="�������������� �������"], [������], [��������� (1-�����������������,2=��������� ������,3-����������,4-��������������,5-������)=3])
		//		������� => ��������� ���������, ����������� � �������
		//		���������� => ��������� ��������� - ��� �������
		//		������� => ��������� ��������� - �������
		//		������ => ������ �������, �� ��������� �����������
		//		��������� => ����� - ��������� �������
		// ������� ������ ������ � ��������� ������ �����������.
		map.put("������������������������", "logEvent");
		map.put("LogMessageWrite", "logEvent");

		// (������ �������)
		// �������� �� ���������� ������� DOS.
		map.put("��������������", "shellExec");
		map.put("System", "shellExec");

		// (���� � ������������ ����� ��� ���������)
		// ��������� ������ ������ ���������� ��� �������� ����� ������������������ �����������
		map.put("�������������������", "openURI");
		map.put("RunApp", "openURI");

		// ([������ ����������=1])
		// �������� ���������� ����� �������.
		map.put("����������������������", "Quit");
		map.put("ExitSystem", "Quit");

		// (������ ��������)
		// ������� ��� ���������� �� �������� ������� � �������� �� � ���������� ������ ��������
		map.put("�������������������������", "FindMarkedObjects");
		map.put("FindMarkedForDelete", "FindMarkedObjects");

		// (������ ��� ������ ��������, ������� �������� �� �������� �� ������� (�������1-������ �� ������� ������, �������2-������, �������3-����������� � ������))
		//		������� => ���������� �������� �������� ������� ��� �������� ���� "��������������", � ������� ������ ��������� ���������� �������, �� ������� ���� ����� ������.
		//		������ => ������������� ������� ���� "���������������", � ������� ������ ��������� �������� ��������� ������ �� �������.
		// ������� ������ �� �������, ���������� � ������ ��������.
		map.put("�����������", "findReferences");
		map.put("FindReferences", "findReferences");

		// (������ ��� ������ ��������, [���������=0], [������� �������� �� �������� �� ������� (�������1-������ �� ������� ������, �������2-������, �������3-����������� � ������)])
		//		������� => ���������� �������� ������� ��� �������� ���� ''��������������'', � ������� ������ ��������� ���������� �������, ������� ���� �������.
		//		��������� => �������������� ��������. �����: 1 - ����� ��������� �����������, ��� �� ������ �� ��������� ������.
		//		������ => �������������� ��������. ������������� ������� ���� ''���������������'', � ������� ������ ��������� �������� ��������� ������ �� �������.
		// ������� �������, ���������� � ������ ��������.
		map.put("��������������", "deleteObjects");
		map.put("DeleteObjects", "deleteObjects");

		// (��� ���������, [�������� ������ � �������� ��� 0-����������=0])
		// ���������� ������������� ����� ��������� ����������� ������ � �������� ���������� �������.
		map.put("�����������������", "setTimer");
		map.put("IdleProcessing", "setTimer");

		// ()
		// ���������� �������� ������ ������� ���������� ������, ����� ��, ��� ��, ������� �������� � ���� ���������.
		map.put("��������������", "getLastError");
		map.put("GetErrorDescription", "getLastError");
	}

	/* 
	 * Outputs the string with default marker
	 * Origin: ��������(������)
	 */
	public default void outputString(String text) {
		outputString(text, ".");
	}

	/* 
	 * Outputs the string with the specified marker
	 * Origin: ��������(������, ������)
	 */
	public default void outputString(String text, String marker) {
		Runtime.logView.getItems().add(text);		

		if (Runtime.splitPane.getDividerPositions()[0] >= 0.99f)
			Runtime.splitPane.setDividerPositions(0.85f, 0.15f);
	}
	
	/*
	 * Clears the output window
	 * Origin: ���������������������()
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
	 * Origin: ���������(������)
	 */
	public default String setStatusText(String text) {
		String old_status = Runtime.status.getText();
		Runtime.status.setText(text);
		return old_status;
	}

	/*
	 * Beeps. Really useless to implement
	 * Origin: ������()
	 */
	public default void Beep() {
	}

	/*
	 *  Gets array's length
	 *  Origin: ����(������)
	 */
	public default Integer getArrayLen(Object[] arr) {				
		return arr.length;
	}

	/*
	 * Gets title of the main window
	 * Origin: ����������������()
	 */
	public default String setTitle() {
		return Runtime.primaryStage.getTitle();
	}

	/*
	 * Sets title of the main window
	 * Origin: ����������������(���������)
	 */
	public default String setTitle(String text) { 
		String old_title = Runtime.primaryStage.getTitle();
		Runtime.primaryStage.setTitle(text);
		return old_title;
	}

	/*
	 * Gets this machine's network name
	 * Origin: �������������()
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
	 * Origin: ���������������()
	 */
	public default String getLogin() {
		return Runtime.session.login;
	}

	/*
	 * Gets current user's name
	 * Origin: ���������������������()
	 */
	public default String getUserName() {
		return Runtime.session.user;
	}

	/*
	 * Gets current user's access group
	 * Origin: ������������������()
	 */
	public default String getAccessGroup() {	// ([������������ ���������� ���� �������=0])
		return Runtime.session.accessGroup;
	}

	/*
	 * Gets current user's UI
	 * Origin: ������������������()
	 */
	public default String getUIName() {
		return Runtime.session.ui;
	}

	/*
	 * Gets current user's home folder
	 * Origin: �������������������()
	 */
	public default String getHome() {
		return Runtime.session.home;
	}

	/*
	 * Gets default temp file path
	 * Origin: ����������������������()
	 */
	public default String getTempDir() {
		return Runtime.session.tempdir;
	}

	/*
	 * Gets root path
	 * Origin: ����������������()
	 */
	public default String getRootDir() {
		return Runtime.session.rootdir;
	}

	/*
	 * Gets base path
	 * Origin: ���������()
	 */
	public default String getBaseDir() {
		return null;
	}

	/*
	 * Check permission's request to the object
	 * Origin: ������������()
	 */
	public default Boolean hasAccess(String request, Object obj) {				// (�������� �����, ������)
		return Runtime.permissions.check(request, obj);
	}

	/*
	 * Check if there is other sessions in this database
	 * Origin: ����������������()
	 */
	public default Boolean amIAlone() {
		return (Runtime.session.sessionCount()>1) ? false : true;
	}

	/*
	 * Check if default language is Russian
	 * Origin: ������������()
	 */
	public default Boolean isInRussian() {
		return (Runtime.baseLanguage == Lang.Ru) ? true : false;
	}

	/*
	 * Creates new object
	 * Origin: �������������()
	 */
	public default Object createObject(String name) {
		return Runtime.factory.createByName(name, true);
	}

	/*
	 * Creates an editable object
	 * Origin: ��������������()
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
	 * Origin: �����������()
	 */
	public default Integer getTypeCode(RuntimeObject obj) {
		return obj.getTypeCode();
	}

	/*
	 * Returns string which represents object type 
	 * Origin: ��������������()
	 */
	public default String getTypeString(RuntimeObject obj) {
		return obj.getTypeString();
	}

	/*
	 * Checks if object is empty 
	 * Origin: ��������������()
	 */
	public default Boolean isEmpty(RuntimeObject obj) {
		return obj.isEmpty();
	}

	/*
	 * Returns a non-editable object 
	 * Origin: ��������������()
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

	public default void SetTimer(String proc, Integer interval) {		// (��� ���������, [�������� ������ � �������� ��� 0-����������=0])
	}
	public default void SetTimer(String proc) {		// (��� ���������)
	}

	public default void logEvent() {		// (�����������, [��� �������="�������������� �������"], [�������="�������������� �������"], [������], [��������� (1-�����������������,2=��������� ������,3-����������,4-��������������,5-������)=3])
	}
	public default void shellExec() {		// (������ �������)
	}
	public default void openURI() {		// (���� � ������������ ����� ��� ���������)
	}
	public default void FindMarkedObjects(ObjectsList list) {		// (������ ��������)
	}
	public default void FindReferences(Object obj, Table ref) {		// (������ ��� ������ ��������, ������� �������� �� �������� �� ������� (�������1-������ �� ������� ������, �������2-������, �������3-����������� � ������))
	}
	public default void DeleteObjects(Object obj, Boolean check, Table ref) {		// (������ ��� ������ ��������, [���������=0], [������� �������� �� �������� �� ������� (�������1-������ �� ������� ������, �������2-������, �������3-����������� � ������)])
	}
}
