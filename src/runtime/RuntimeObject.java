package runtime;

import enums.DataType;
import enums.Lang;

public class RuntimeObject {
	protected DataType type;
	protected int md_type;
	protected String subtype;
	protected Object value;
	
	public boolean isEmpty() {
		if (type == null)
			return true;
		switch (type) {
			case Empty:				return true;
			case Numeric:			return ((Integer)value == 0) ? true : false;
			case String:			return (((String)value).trim().length() == 0) ? true : false;
			default:				return false;
		}
	}

	public int getTypeCode() {
		if (type == null)
			return 0;
		switch (type) {
			case Empty:				return 0;
			case Numeric:			return 1;
			case String:			return 2;
			case Date:				return 3;
			case Enum:				return 10;
			case Reference:			return 11;
			case Document:			return 12;
			case Calendar:			return 13;
			case CalculationKind:	return 14;
			case Account:			return 15;
			case SubContoKind:		return 16;
			case AccPlan:			return 17;
			default:				return 100;
		}
	}

	public String getTypeString() {
		if (Runtime.baseLanguage == Lang.Ru) {
			switch (type) {
				case Numeric:			return "�����";
				case String:			return "������";
				case Date:				return "����";
				case Enum:				return "������������";
				case Reference:			return "����������";
				case Document:			return "��������";
				case Register:			return "�������";
				case Calendar:			return "���������";
				case CalculationKind:	return "����������";
				case CalcJournal:		return "��������������";
				case AccPlan:			return "����������";
				case Account:			return "����";
				case Operation:			return "��������";
				case CorrectEntries:	return "������������������";
				case AccTotals:			return "������������������";
				case Table:				return "�������";
				case Text:				return "�����";
				case AccQuery:			return "������";
				case ValueList:			return "��������������";
				case ValueTable:		return "���������������";
				case Periodic:			return "�������������";
				case Picture:			return "��������";
				case Form:				return "�����������������";
				case OLE:				return "OLE";
				case Object:			return "�����������������";
				case SubContoKind:		return "������������";
				case Constant:			return "���������";
				case CalculationGroup:	return "��������������";
				default:				return subtype;
			}
		} else {
			switch (type) {
				case Numeric:			return "Number";
				case String:			return "String";
				case Date:				return "Date";
				case Enum:				return "Enum";
				case Reference:			return "Reference";
				case Document:			return "Document";
				case Register:			return "Register";
				case Calendar:			return "Calendar";
				case CalculationKind:	return "Calculation";
				case CalcJournal:		return "CalcJournal";
				case AccPlan:			return "ChartOfAccounts";
				case Account:			return "Account";
				case Operation:			return "Operation";
				case CorrectEntries:	return "CorrectEntries";
				case AccTotals:			return "BookkeepingTotals";
				case Table:				return "Table";
				case Text:				return "Text";
				case AccQuery:			return "Query";
				case ValueList:			return "ValueList";
				case ValueTable:		return "ValueTable";
				case Periodic:			return "Periodic";
				case Picture:			return "Picture";
				case Form:				return "GroupContext";
				case OLE:				return "OLE";
				case Object:			return "UnknownObject";
				case SubContoKind:		return "";
				case Constant:			return "Const";
				case CalculationGroup:	return "CalculationGroup";
				default:				return subtype;
			}
		}
	}
}
