package ru.javawebinar.graduation.util.exception;

import org.springframework.http.HttpStatus;

public class VoteTimesUpException extends ApplicationException {
    public static final String VOTE_TIMES_UP_EXCEPTION = "Time's up";

    public VoteTimesUpException(String arg) {
        super(ErrorType.VOTE_TIMES_UP, VOTE_TIMES_UP_EXCEPTION, HttpStatus.CONFLICT);
    }
}
