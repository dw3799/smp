package com.alipapa.smp.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1123097649484696635L;
    public String errorCode;
    public int intErrorCode;
    public String errorMsg;
    public String extStatus;
    public Boolean isNeedRetry;
    public boolean isNeedToLogTrace;
    public boolean shouldHideErrorMessageFromUser;

    public ServiceException(String errorMsg) {
        this("1", errorMsg);
    }

    public ServiceException(Throwable cause) {
        this("1", cause.getMessage(), cause);
    }

    public ServiceException(String errorCode, String errorMsg) {
        this(errorCode, errorMsg, (Throwable) null);
    }

    public ServiceException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.isNeedToLogTrace = true;
        this.shouldHideErrorMessageFromUser = false;
        this.intErrorCode = Integer.parseInt(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
