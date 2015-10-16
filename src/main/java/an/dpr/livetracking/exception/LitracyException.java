package an.dpr.livetracking.exception;

public class LitracyException extends Exception {
    
    private static final long serialVersionUID = 2679441615597498530L;

    public static final int CANT_DELETE_DEPENDENCIES = 0;
    
    private int code;
    
    public LitracyException(String msg) {
	super(msg);
    }
    
    public LitracyException(String msg, Throwable e) {
	super(msg, e);
    }

    public LitracyException(int code, String msg, Throwable e) {
	this(msg, e);
	this.code = code; 
    }

    public LitracyException(int code) {
	super();
	this.code = code; 
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
