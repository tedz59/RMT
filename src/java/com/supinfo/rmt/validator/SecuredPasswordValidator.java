/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.supinfo.rmt.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 *
 * @author Binarymachine
 */
public class SecuredPasswordValidator implements ConstraintValidator<SecuredPassword, String> {

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*)";

    private SecuredPassword securedPassword;
    private Pattern pattern;

    @Override
    public void initialize(SecuredPassword securedPassword) {
        this.securedPassword = securedPassword;
        pattern = pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        return pattern.matcher(password).matches();
    }
}
