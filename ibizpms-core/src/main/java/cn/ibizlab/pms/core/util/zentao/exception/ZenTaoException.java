package cn.ibizlab.pms.core.util.zentao.exception;

public class ZenTaoException extends RuntimeException {
    static final long serialVersionUID = -1L;

    public ZenTaoException() {
        super();
    }

    public ZenTaoException(String message) {
        super(message);
    }

    public ZenTaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZenTaoException(Throwable cause) {
        super(cause);
    }

    protected ZenTaoException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
