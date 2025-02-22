package com.github.kaktushose.jda.commands.annotations.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a number whose value must be lower or equal to the specified maximum.
 *
 * @author Kaktushose
 * @version 2.0.0
 * @see Constraint
 * @since 2.0.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint({Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class})
public @interface Max {

    /**
     * Returns the value the element must be lower or equal to.
     *
     * @return Returns the value the element must be lower or equal to
     */
    long value();

    /**
     * Returns the error message that will be displayed if the constraint fails.
     *
     * @return the error message
     */
    String message() default "Parameter exceeds maximum value";
}
