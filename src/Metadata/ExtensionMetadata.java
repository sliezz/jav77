package Metadata;

import enums.DataType;

public abstract class ExtensionMetadata {
	public DataType type;
	public int id;
	public String name;
	public String synonym;
	public String comment;

    /**
     * Initialize the object by id of extension.
     * @return true if operation succeeded
     */
    abstract public boolean create(int metadata_id);

    /**
     * Get id of extension by its name.
     * @return true if operation succeeded
     */
    abstract public int get_id(String full_name);

    /**
     * Get name of extension by its id.
     * @return true if operation succeeded
     */
    abstract public String get_name(int metadata_id);
}
