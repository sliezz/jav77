package runtime;

import Exec.Root.Root;
import Table.Table;

public class Tests {
	private static void ok(String str) {
		System.out.println(str + " - ok");
	}
	private static void failed(String str) {
		System.out.println(str + " - FAILED!");
	}
	private static void failed(Exception e, String str) {
		failed(str);
		e.printStackTrace();
	}

	private static void test_Table() {
		Table test=null;
		try
		{
			test = new Table();
			ok("new Table()");
		}
		catch (ExecException ex) {
			failed(ex,"new Table()");
		}
		try
		{
			test.addColumn("name 1", "title 1", "������", 10, 0, 15, "", 1);
			test.addColumn("name 2", "title 2", "�����", 15, 2, 8, "�15.1", 2);
			test.addColumn("name 3", "title 3", "����", 0, 0, 5, "���.��", 2);
			ok("Table.addColumn() x3");
		}
		catch (ExecException ex) {
			failed(ex,"Table.addColumn()");
		}
		try
		{
			test.deleteColumn(2);
			test.deleteColumn(2);
			ok("Table.deleteColumn(2) x2");
		}
		catch (ExecException ex) {
			failed(ex,"Table.deleteColumn()");
		}
		try
		{
			test.deleteColumn(2);
			failed(null,"Table.deleteColumn(2) didn't throw exception");
		}
		catch (ExecException ex) {
			ok("Table.deleteColumn(2) invoke exception <"+ex.getMessage()+">");
		}
		try
		{
			test.close();
			ok("Table.close()");
		}
		catch (ExecException ex) {
			failed(ex,"Table.close()");
		}
		test = null;
	}

	private static void test_ExecBase() {
		Root root = Runtime.root;
		try {
			root.Exec("��������", "��������(������)");
			ok("��������(������)");
		} catch (Exception e) {
			failed(e,"��������(������)");
		}

		try {
			root.Exec("��������", "��������(������,�����)","!");
			ok("��������(������,�����)");
		} catch (Exception e) {
			failed(e,"��������(������,�����)!");
		}

		try {
			root.Exec("���������", "���������(������)");
			ok("���������(������)");
		} catch (Exception e) {
			failed(e,"���������(������)!");
		}
	}
	
	public static void run() {
		//Runtime.mem_db.EnableLog();
		//Runtime.mem_db.DisableLog();

		test_Table();
		test_ExecBase();
	}
}
