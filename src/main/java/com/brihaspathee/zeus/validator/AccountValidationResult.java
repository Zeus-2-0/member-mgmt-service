package com.brihaspathee.zeus.validator;

import com.brihaspathee.zeus.exception.ValidationException;
import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, September 2022
 * Time: 3:51 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.validator
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountValidationResult {

    /**
     * Contains the list of validation exceptions
     */
    private List<ValidationException> validationExceptions;

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "AccountValidationResult{" +
                "validationExceptions=" + validationExceptions +
                '}';
    }
}
