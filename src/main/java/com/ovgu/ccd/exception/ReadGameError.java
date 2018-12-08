package com.ovgu.ccd.exception;

public class ReadGameError extends Exception {

    private final ErrorCode _errCode;

    public ReadGameError(ErrorCode code) {
        super();
        this._errCode = code;
    }

    public ReadGameError(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this._errCode = code;
    }

    public ReadGameError(String message, ErrorCode code) {
        super(message);
        this._errCode = code;
    }

    public ReadGameError(Throwable cause, ErrorCode code) {
        super(cause);
        this._errCode = code;
    }

    public ErrorCode getCode() {
        return this._errCode;
    }

}
