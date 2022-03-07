package Metadata;

import java.util.HashMap;

import enums.DataType;

public abstract class JournalMetadata {
	public int id;
	public String name;
	public String synonym;
	public String comment;
	public String unknown1;
	public int form_list;
	public int form_select;
	public boolean is_using_crdoc;
	public boolean is_for_any_doc;

	public HashMap<String, FieldInfo> fields = new HashMap<String, FieldInfo>();
	public HashMap<String, FormInfo> forms = new HashMap<String, FormInfo>();

    /**
     * Initialize the object by id of reference.
     * @return true if operation succeeded
     */
    abstract public boolean create(int metadata_id);

    /**
     * Get id of reference by its name.
     * @return true if operation succeeded
     */
    abstract public int get_id(String name);

    /**
     * Get name of reference by its id.
     * @return true if operation succeeded
     */
    abstract public String get_name(int metadata_id);
}
