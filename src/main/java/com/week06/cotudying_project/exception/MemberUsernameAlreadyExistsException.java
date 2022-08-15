package com.week06.cotudying_project.exception;

public class MemberUsernameAlreadyExistsException extends RuntimeException{

    public MemberUsernameAlreadyExistsException(String message) {
        super(message);
    }
}
