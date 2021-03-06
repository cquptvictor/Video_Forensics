package com.edu.victor.AOP;

import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.WrongArgumentsException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
public class ArgumentsAop{
    @Before("execution(* com.edu.victor..*(*,org.springframework.validation.BindingResult,..))")
    public void authArguments(JoinPoint joinPoint) throws WrongArgumentsException, NotAuthorizedException {
            Object[] args = joinPoint.getArgs();
            for(Object object : args){
                if(object instanceof BindingResult){
                    if(((BindingResult) object).hasErrors()){
                        BindingResult bindingResult = (BindingResult)object;
                        for(FieldError fieldError:bindingResult.getFieldErrors()){
                            System.out.println(fieldError.toString());
                        }
                        throw new WrongArgumentsException();
                    }
                }
            }
    }
}
