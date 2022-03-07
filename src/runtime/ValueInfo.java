package runtime;

import enums.DataType;

public class ValueInfo {
	protected DataType type;
	protected int metadata_id;
	protected int precision;
	protected int scale;

	public ValueInfo(DataType type, int metadata_id, int precision, int scale) {
		setDataType(type, metadata_id, precision, scale);
	}
	public ValueInfo(DataType type, int precision, int scale) {
		setDataType(type, 0, precision, scale);
	}
	public ValueInfo(DataType type, int metadata_id) {
		setDataType(type, metadata_id, 0, 0);
	}
	public ValueInfo(DataType type) {
		setDataType(type, 0, 0, 0);
	}
	public ValueInfo() {
		setDataType(DataType.Empty, 0, 0, 0);
	}
	
	protected void setDataType(DataType new_type, int new_metadata_id, int new_precision, int new_scale) { 
		type = new_type;
		metadata_id = new_metadata_id;
		precision = new_precision;
		scale = new_scale;
	}
	public int getMetadataId() {
		return metadata_id;
	}
	public DataType getType() {
		return type;
	}
}
