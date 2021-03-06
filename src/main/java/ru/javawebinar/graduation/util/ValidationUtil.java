package ru.javawebinar.graduation.util;

import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;
import ru.javawebinar.graduation.HasId;
import ru.javawebinar.graduation.util.exception.ErrorType;
import ru.javawebinar.graduation.util.exception.IllegalRequestDataException;
import ru.javawebinar.graduation.util.exception.NotFoundException;

public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static String[] getBindErrorDetails(BindingResult result) {
        return result.getFieldErrors().stream().map(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (msg != null) {
                        if (!msg.startsWith(fe.getField())) {
                            msg = fe.getField() + ' ' + msg;
                        }
                    }
                    return msg;
                }).toArray(String[]::new);
    }

    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable logAndGetRootCause(Logger log, WebRequest request, Exception e, ErrorType errorType) {
        Throwable rootCause = getRootCause(e);
        log.warn("{} at request  {}: {}", errorType, request.getDescription(false), rootCause.toString());
        return rootCause;
    }
}