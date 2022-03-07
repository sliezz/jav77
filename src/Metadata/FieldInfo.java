package Metadata;

import runtime.ValueInfo;

public class FieldInfo {
	int id;
	String db_name;
	String name;
	String synonym;
	String comment;
	String synonym2;
	String format_string;
	ValueInfo type;
	boolean deny_negative;
	boolean is_in_1sjourn;
	FieldAvailability availability;
	boolean is_indexed;
	boolean is_sortable;
	boolean is_periodic;
	boolean periodic_editable;
	boolean periodic_editable_doc;
	boolean has_totals;
	boolean is_nullable;
	boolean quick_select;
	int[] refers;

	public FieldInfo (
			int id,
			String name,
			String synonym,
			String comment,
			int[] refers
	) {
		this.id = id;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.refers = refers;
	}

	public FieldInfo (
			int id,
			String name,
			String synonym,
			String comment,
			String synonym2
	) {
		this.id = id;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.synonym2 = synonym2;
	}

	public FieldInfo (
			int id,
			String name,
			String synonym,
			String comment,
			ValueInfo type,
			String format_string,
			boolean deny_negative,
			boolean is_in_1sjourn
	) {
		this.id = id;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
		this.is_in_1sjourn = is_in_1sjourn;
	}

	public FieldInfo (
		int id,
		String db_name,
		String name,
		String synonym,
		String comment,
		String format_string,
		ValueInfo type,
		boolean deny_negative,
		FieldAvailability availability,
		boolean is_indexed,
		boolean is_sortable,
		boolean is_periodic,
		boolean periodic_editable,
		boolean periodic_editable_doc
	) {
		this.id = id;
		this.db_name = db_name;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
		this.availability = availability;
		this.is_indexed = is_indexed;
		this.is_sortable = is_sortable;
		this.is_periodic = is_periodic;
		this.periodic_editable = periodic_editable;
		this.periodic_editable_doc = periodic_editable_doc;
	}

	public FieldInfo (
			String db_name,
			String name,
			ValueInfo type
	) {
		this.db_name = db_name;
		this.name = name;
		this.type = type;
		this.is_indexed = true;
		this.is_sortable = true;
	}

	public FieldInfo (
			int id,
			String db_name,
			String name,
			String synonym,
			String comment,
			String format_string,
			ValueInfo type,
			boolean deny_negative
	) {
		this.id = id;
		this.db_name = db_name;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
	}

	public FieldInfo (
			int id,
			String db_name,
			String name,
			String synonym,
			String comment,
			String format_string,
			ValueInfo type,
			boolean deny_negative,
			boolean has_totals
	) {
		this.id = id;
		this.db_name = db_name;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
		this.has_totals = has_totals;
	}

	public FieldInfo (
			int id,
			String db_name,
			String name,
			String synonym,
			String comment,
			String format_string,
			ValueInfo type,
			boolean deny_negative,
			boolean is_indexed,
			boolean has_totals
	) {
		this.id = id;
		this.db_name = db_name;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
		this.is_indexed = is_indexed;
		this.has_totals = has_totals;
	}

	public FieldInfo (
			int id,
			String db_name,
			String name,
			String synonym,
			String comment,
			String format_string,
			ValueInfo type,
			boolean deny_negative,
			boolean is_indexed,
			boolean has_totals,
			boolean is_periodic
	) {
		this.id = id;
		this.db_name = db_name;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
		this.is_indexed = is_indexed;
		this.has_totals = has_totals;
		this.is_periodic = is_periodic;
	}

	public FieldInfo (
			int id,
			String db_name,
			String name,
			String synonym,
			String comment,
			String format_string,
			ValueInfo type,
			boolean deny_negative,
			boolean is_indexed,
			boolean has_totals,
			boolean is_nullable,
			boolean quick_select
	) {
		this.id = id;
		this.db_name = db_name;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
		this.is_indexed = is_indexed;
		this.has_totals = has_totals;
		this.is_nullable = is_nullable;
		this.quick_select = quick_select;
	}

	public FieldInfo (
			int id,
			String db_name,
			String name,
			String synonym,
			String comment,
			String format_string,
			ValueInfo type,
			boolean deny_negative,
			boolean is_indexed,
			boolean has_totals,
			boolean is_nullable,
			boolean quick_select,
			int[] refers
	) {
		this.id = id;
		this.db_name = db_name;
		this.name = name;
		this.synonym = synonym;
		this.comment = comment;
		this.format_string = format_string;
		this.type = type;
		this.deny_negative = deny_negative;
		this.is_indexed = is_indexed;
		this.has_totals = has_totals;
		this.is_nullable = is_nullable;
		this.quick_select = quick_select;
		this.refers = refers;
	}

}

