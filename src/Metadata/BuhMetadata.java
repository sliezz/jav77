package Metadata;

import java.util.HashMap;

import enums.DataType;

public abstract class BuhMetadata {
	public DataType type;
	public int id;
	public String name;
	public String synonym;
	public String comment;
	public String mask;
	public String mask2;
	public HashMap<String, FieldInfo> AccParams = new HashMap<String, FieldInfo>();
	public HashMap<String, FieldInfo> ProvParams = new HashMap<String, FieldInfo>();
	public HashMap<String, FieldInfo> OperParams = new HashMap<String, FieldInfo>();
	public HashMap<String, FieldInfo> Subconto = new HashMap<String, FieldInfo>();
	public HashMap<String, FormInfo> forms1 = new HashMap<String, FormInfo>();
	public HashMap<String, FormInfo> forms2 = new HashMap<String, FormInfo>();
	public HashMap<String, FormInfo> forms3 = new HashMap<String, FormInfo>();

	public DataType default_plan;
	public DataType currency;

	public int edit_rule, max_name_len, max_code_len, max_sbcnt_count, oper_descr_len, oper_sum_len, oper_sum_dec;
	public int prov_sum_len, prov_sum_dec, prov_cur_sum_len, prov_cur_sum_dec, prov_amount_len, prov_amount_dec;
	public int currency_id, rate_id, pr_sum_oper_select, pr_descr_oper_select, oper_sum_calc_method, pr_acc_select;
	public int max_level, pr_triad_sum, pr_triad_curr_sum, pr_triad_amount, pr_sum_prov_select, pr_curr_prov_select;
	public int pr_curr_sum_prov_select, pr_amount_prov_select, ratio_id, pr_compl_acc, pr_select_calc_method_by_user;
	public int pr_dbcr_select, pr_edit_oper_doc, pr_amount_by_analit, acc_separator_id, mainplain_id;

    /**
     * Initialize the object by its id.
     * @return true if operation succeeded
     */
    abstract public boolean create(int metadata_id);

    /**
     * Get id of object by its name.
     * @return true if operation succeeded
     */
    abstract public int get_id(String name);

    /**
     * Get name of object by its id.
     * @return true if operation succeeded
     */
    abstract public String get_name(int metadata_id);
}
