package lang;

import enums.RuntimeExceptionKinds;

public class Language {
	
	public String MissedParameter(String param) {
		return "�������� �������� \""+param+"\"";
	}
	
	public String GetRuntimeExceptionReason(RuntimeExceptionKinds kind) {
		String reason="����������� ������";
    	switch (kind) {
    	case WrongIndex:
    		reason="������ �� ��������� ��������� ��������";
    		break;

    	case WrongName:
    		reason="������ � ��������� ������ �� ������";
    		break;

    	case OutOfMemory:
    		reason="������������ ������";
    		break;

    	case OtherException:
			reason="������ ������";
			break;

		default:
			break;
     	}
    	return reason;
	}

	public String Ready() {
		return "������";
	}
}
