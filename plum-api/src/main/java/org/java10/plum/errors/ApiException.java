package org.java10.plum.errors;

import java.io.Serializable;

/**
 * Created by Tate on 2017/2/27.
 */
public class ApiException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8107893704146600982L;
    private int errCode;

    public ApiException(int errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
    }

    public ApiException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public ApiException(int errCode) {
        super("");
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
