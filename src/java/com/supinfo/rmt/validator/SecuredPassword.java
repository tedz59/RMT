/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.supinfo.rmt.validator;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Binarymachine
 */
@NotEmpty
@Size(min = 6, max = 20)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE } )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { SecuredPasswordValidator.class })
@Documented
public @interface SecuredPassword {

    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
