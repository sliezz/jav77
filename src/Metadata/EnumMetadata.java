package Metadata;

import java.util.HashMap;

public abstract class EnumMetadata {
	public int id;
	public String name;
	public String synonym;
	public String comment;
	public String synonym2;

	public HashMap<String, FieldInfo> values = new HashMap<String, FieldInfo>();

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
