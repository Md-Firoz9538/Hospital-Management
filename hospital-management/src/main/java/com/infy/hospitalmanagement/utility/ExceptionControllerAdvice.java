package com.infy.hospitalmanagement.utility;

import com.infy.hospitalmanagement.exception.PatientAdmissionException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;
    private static final Log LOGGER= LogFactory.getLog(ExceptionControllerAdvice.class);

    @ExceptionHandler(PatientAdmissionException.class)
        public ResponseEntity<ErrorInfo> patientAdmissionExceptionHandler(PatientAdmissionException exception){

        LOGGER.error(exception.getMessage(),exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
        return  new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
        LOGGER.error(exception.getMessage(),exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
        return  new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);



    }
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception){
        LOGGER.error(exception.getMessage(),exception);
        String errorMsg;
        if (exception instanceof MethodArgumentNotValidException manvException){
            errorMsg=manvException.getBindingResult().getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
        }else {
            ConstraintViolationException cvException=(ConstraintViolationException) exception;
            errorMsg=cvException.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        }
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorInfo.setErrorMessage(errorMsg);
        return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);


    }

}
