package Exec.Root;

import Exec.Base.Vars;
import Metadata.Metadata;
import Metadata.Compiled.MetadataCompiled;

public class RootVars extends Vars {
	public Metadata md = new MetadataCompiled();

	public int statusCode=0;
	
	@Override
	public Object Get(String varName) throws Exception {
		if (varName.compareToIgnoreCase("Метаданные")==0)
			return md;
		if (varName.compareToIgnoreCase("СимволТабуляции")==0)
			return "\t";
		if (varName.compareToIgnoreCase("РазделительСтрок")==0)
			return "\r\n";
		if (varName.compareToIgnoreCase("РазделительСтраниц")==0)
			return "\f";
		return null;
	}

	@Override
	public void Set(String varName, Object newValue) throws Exception {
		throw new Exception(varName+" is read only");
	}
	
}
