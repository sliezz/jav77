package Exec.Base;

public class ExecError {
	private String ModuleName;
	private int LineNo;
	private String MethodName;
	private String ErrorMessage;
	
	public ExecError(String MethodName, String ErrorMessage) {
		this.ModuleName = "";
		this.LineNo = 0;
		this.MethodName = MethodName;
		this.ErrorMessage = ErrorMessage;
	}
	
	public ExecError(String ModuleName, int LineNo, String MethodName, String ErrorMessage) {
		this.ModuleName = ModuleName;
		this.LineNo = LineNo;
		this.MethodName = MethodName;
		this.ErrorMessage = ErrorMessage;
	}
	
	public String GetModuleName() {
		return ModuleName; 
	}
	
	public int GetLineNo() {
		return LineNo; 
	}
	
	public String GetMethodNameName() {
		return MethodName; 
	}
	
	public String GetErrorMessage() {
		return ErrorMessage; 
	}
	
	public String Get() {
		if (ModuleName.length()==0)
			return "::" + MethodName + "> " + ErrorMessage; 
		return ModuleName + "::" + MethodName + " @ " + String.valueOf(LineNo) + "> " + ErrorMessage; 
	}
}
