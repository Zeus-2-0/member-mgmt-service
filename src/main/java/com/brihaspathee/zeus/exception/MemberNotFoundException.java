package com.brihaspathee.zeus.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 16, September 2022
 * Time: 7:45 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(String message){
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
