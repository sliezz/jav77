package Metadata;

import enums.DataType;
import runtime.ValueInfo;

public abstract class Metadata {
	public int next_id;
	public String flags;
	public int file_ver;
	
	public int TaskItem_id;
	public String name;
	public String synonym;
	public String comment;
	public String password_hash;
	public String language;
	public boolean allow_direct_deletion;
	public boolean format_round_half_to_zero;
	public int unknown1;
	public int unknown2;

	public FieldInfo[] CommonFields;

	public DocumentFilter[] DocumentFilters;
	public class DocumentFilter {
		int id;
		String name;
		String synonym;
		String comment;
		ValueInfo type;
		boolean use_for_empty_values;
		int[] field_ids;

		public DocumentFilter(int id, String name, String synonym, String comment, ValueInfo type, boolean use_for_empty_values, int[] field_ids) {
			this.id = id;
			this.name = name;
			this.synonym = synonym;
			this.comment = comment;
			this.type = type;
			this.use_for_empty_values = use_for_empty_values;
			this.field_ids = field_ids;
		}
	};

	public Numerator[] Numerators;
	public class Numerator {
		int id;
		String name;
		String synonym;
		String comment;
		Period period;
		DataType type;
		int length;
		boolean is_unique;

		public Numerator(int id, String name, String synonym, String comment, Period period, DataType type, int length, boolean is_unique) {
			this.id = id;
			this.name = name;
			this.synonym = synonym;
			this.comment = comment;
			this.period = period;
			this.type = type;
			this.length = length;
			this.is_unique = is_unique;
		}
	}

	public Constant[] Constants;
	public class Constant {
		int id;
		String name;
		String synonym;
		String comment;
		ValueInfo type;
		String format_string;
		boolean deny_negative;
		boolean is_periodic;
		int DistributionRuler;

		public Constant(int id, String name, String synonym, String comment, ValueInfo type, String format_string, boolean deny_negative, boolean is_periodic, int DistributionRuler) {
			this.id = id;
			this.name = name;
			this.synonym = synonym;
			this.comment = comment;
			this.type = type;
			this.format_string = format_string;
			this.deny_negative = deny_negative;
			this.is_periodic = is_periodic;
			this.DistributionRuler = DistributionRuler;
		}
	};

    /**
     * Initialize the object.
     * @return true if operation succeeded
     */
    abstract public boolean init();
}
