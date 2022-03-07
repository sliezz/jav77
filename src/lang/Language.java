package lang;

import enums.RuntimeExceptionKinds;

public class Language {
	
	public String MissedParameter(String param) {
		return "Пропущен параметр \""+param+"\"";
	}
	
	public String GetRuntimeExceptionReason(RuntimeExceptionKinds kind) {
		String reason="Неизвестная ошибка";
    	switch (kind) {
    	case WrongIndex:
    		reason="Индекс за пределами диапазона значений";
    		break;

    	case WrongName:
    		reason="Объект с указанным именем не найден";
    		break;

    	case OutOfMemory:
    		reason="Недостаточно памяти";
    		break;

    	case OtherException:
			reason="Прочая ошибка";
			break;

		default:
			break;
     	}
    	return reason;
	}

	public String Ready() {
		return "Готово";
	}
}
