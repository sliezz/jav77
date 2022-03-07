package Exec.Base;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Code {
	protected Module module;
	protected HashMap<String,String> mapMethods = new HashMap<String,String>();

	public void Init() {
	}

	public void Release() {
		Detach();
	}

	public void Attach(Module module) {
		this.module = module;
	}

	public void Detach() {
		this.module = null;
	}

	/*
	 * Execute method by its name
	 * Returns true if method exists in this module
	 */
	public Object Exec(String methodName, Object... args) throws Exception {
		String methodNameReal = mapMethods.get(methodName);
		if (methodNameReal == null) {
			throw new Exception("Can't find a method for '"+methodName+"'");
		}

		// invoke
		Method[] methods = getClass().getMethods();
		for (int i=0; i<methods.length; i++) {
			if (methods[i].getName().compareToIgnoreCase(methodNameReal) == 0 && methods[i].getParameterCount() == args.length) {
				return methods[i].invoke(this, args);
			}
		}
		throw new Exception("Can't find a method for '"+methodName+"' with "+String.valueOf(args.length)+" arguments");
	}
}
