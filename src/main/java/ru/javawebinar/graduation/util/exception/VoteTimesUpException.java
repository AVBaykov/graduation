package ru.javawebinar.graduation.util.exception;

public class VoteTimesUpException extends RuntimeException {
    public VoteTimesUpException(String msg) {
        super(msg);
    }
}
