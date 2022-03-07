package runtime;

import enums.RuntimeExceptionKinds;

/**
 * <P>An exception that provides information on a Runtime error.
 *
 * <P>Each <code>ExecException</code> provides several kinds of information:
 * <UL>
 *   <LI> a string describing the error.  This is used as the Java Exception
 *       message, available via the method <code>getMessage</code>.
 *   <LI> an Module name and a line number of the command in which the Exception is thrown.
 * </UL>
 */
public class ExecException extends java.lang.Exception {

    /**
     *  Constructs a <code>ExecException</code> object with a given
     * <code>reason</code>, <code>kind</code>, <code>module</code> and
     * <code>lineNo</code>.
     *
     * @param kind a <code>RuntimeExceptionKinds</code> of this exception
     * @param reason a description of the exception
     * @param module an module name identifying the exception
     * @param lineNo a line number of an module identifying the exception
     */
    public ExecException(RuntimeExceptionKinds kind, String reason, String module, int lineNo) {
        super(reason);
        this.kind = kind;
        this.module = module;
        this.lineNo = lineNo;
    }

    /**
     *  Constructs a <code>ExecException</code> object with a given
     * <code>reason</code> and <code>kind</code>.
     *
     * @param kind a <code>RuntimeExceptionKinds</code> of this exception
     * @param reason a description of the exception
     */
    public ExecException(RuntimeExceptionKinds kind, String reason) {
        super(reason);
        this.kind = kind;
        this.module = null;
        this.lineNo = 0;
   }

    /**
     *  Constructs a <code>ExecException</code> object with a given
     * <code>reason</code> of the <code>OtherException</code> kind.
     *
     * @param reason a description of the exception
     */
    public ExecException(String reason) {
        super(reason);
        this.kind = RuntimeExceptionKinds.OtherException;
        this.module = null;
        this.lineNo = 0;
    }

    /**
     *  Constructs a <code>ExecException</code> object with a given kind.
     *
     * @param kind a <code>RuntimeExceptionKinds</code> of this exception
     */
     public ExecException(RuntimeExceptionKinds kind) {
        super(Runtime.lang.GetRuntimeExceptionReason(kind));
        this.kind = kind;
        this.module = null;
        this.lineNo = 0;
	}

	/**
     * Retrieves Module name which invokes this <code>ExecException</code> object.
     *
     * @return module name
     */
    public String getModule() {
        return (module);
    }

    /**
     * Retrieves line number of <code>Module</code> which invokes this <code>ExecException</code>.
     *
     * @return line number
     */
    public int getLineNo() {
        return (lineNo);
    }


    /**
         * @serial
         */
    private RuntimeExceptionKinds kind;

    /**
         * @serial
         */
    private String module;

        /**
         * @serial
         */
    private int lineNo;
}
