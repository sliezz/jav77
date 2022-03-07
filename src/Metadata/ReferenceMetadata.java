package Metadata;

import java.util.HashMap;

import enums.DataType;

public abstract class ReferenceMetadata {
	public int id;
	public String name;
	public String synonym;
	public String comment;
	public int owner_id;
	public int code_length;
	public RefCodeUniqueRule code_unique_rule;
	public boolean use_incremental_code;
	public DataType code_type;
	public RefDisplayRule display_rule;
	public int name_length;
	public EditRule edit_rule;
	public int max_recursion_level;
	public int form_item_id;
	public int form_group_id;
	public boolean form_edit_group_as_item;
	public boolean unknown_flag;

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
