package edu.nju.flag.base.utils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.List;

/**
 * OvalValidatorUtils
 *
 * @author xuan
 * @date 2018/12/10
 */
public class OvalValidatorUtils {


    private static final Validator VALIDATOR = new Validator();

    public static List<ConstraintViolation> validate(Object form){
        return VALIDATOR.validate(form);

    }


}
