package DbObject;

import java.util.HashMap;
import runtime.Form;
import runtime.Value;
import Metadata.Compiled.DocumentMetadataCompiled;

public abstract class DbObject {
	protected String metadata_name;
	protected int metadata_id;
	protected DocumentMetadataCompiled Metadata;
	protected HashMap<String,Form> forms = new HashMap<String,Form>();
	protected HashMap<String,DbField> fields = new HashMap<String,DbField>();
	
	public int getMetadataId() {
		return metadata_id;
	}
	public String getMetadataName() {
		return metadata_name;
	}

	public Value get(String name) {
		DbField field = fields.get(name);
		return field.value;
	}
	public void set(String name, Value val) {
		DbField field = fields.get(name);
		field.set(val);
	}

	public Value openForm(String name, Value arg) {
		Form form = forms.get(name);
		return form.open(arg);
	}

	abstract public Value invoke(String method, Value[] args);
}
