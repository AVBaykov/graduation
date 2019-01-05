package ru.javawebinar.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.javawebinar.graduation.util.ValidationUtil;
import ru.javawebinar.graduation.util.exception.*;

import static ru.javawebinar.graduation.util.exception.ErrorType.*;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo handleError(WebRequest request, NotFoundException e) {
        return logAndGetErrorInfo(request, e, DATA_NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(WebRequest request, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(request, e, DATA_ERROR);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ErrorInfo illegalRequestDataError(WebRequest request, Exception e) {
        return logAndGetErrorInfo(request, e, VALIDATION_ERROR);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorInfo bindValidationError(WebRequest request, MethodArgumentNotValidException e) {
        return logAndGetErrorInfo(request, e, VALIDATION_ERROR, ValidationUtil.getBindErrorDetails(e.getBindingResult()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(WebRequest request, Exception e) {
        return logAndGetErrorInfo(request, e, APP_ERROR);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(VoteTimesUpException.class)
    public ErrorInfo voteTimesUp(WebRequest request, VoteTimesUpException e) {
        return logAndGetErrorInfo(request, e, VOTE_TIMES_UP);
    }

    private ErrorInfo logAndGetErrorInfo(WebRequest request, Exception e, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(log, request, e, errorType);
        return details.length != 0 ? new ErrorInfo(request.getDescription(false), errorType, details) :
                new ErrorInfo(request.getDescription(false), errorType, ValidationUtil.getMessage(rootCause));
    }
}
