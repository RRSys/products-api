package com.rrsys.productsapi.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

//ResponseEntityExceptionHandler captura exceções de resposta de entidades
@ControllerAdvice
public class    ProductsExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("invalid.Message", null, LocaleContextHolder.getLocale());
        String errorMessage = ex.getCause().toString();

        return handleExceptionInternal(ex, new ErrorMessage(userMessage, errorMessage), headers, HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        String userMessage = ex.getMessage();
        String errorMessage = ex.toString();
        ErrorMessage error = new ErrorMessage(userMessage, errorMessage);

        return ResponseEntity.badRequest().body(error);
    }

    //Retorna uma lista de objetos do tipo ErrorMessage
        private List<ErrorMessage> ListOfErrors(BindingResult result){

            //Lista Vazia do tipo ErrorMessage
            List<ErrorMessage> errors = new ArrayList<>();


            for(FieldError fieldError : result.getFieldErrors()){
                //MessageSource é uma classe que contem as mensagens de erro
                String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                String errorMessage = fieldError.toString();
                // é criado um novo objeto ErrorMessage com as variáveis userMessage e errorMessage e adicionado à lista de erros.
                errors.add(new ErrorMessage(userMessage,errorMessage));
            }
            return errors;
        }
    }
         class ErrorMessage{

            private String userMessage;
            private String errorMessage;

            public ErrorMessage(String userMessage, String errorMessage) {
                super();
                this.userMessage = userMessage;
                this.errorMessage = errorMessage;
            }

             public String getUserMessage() {
                 return userMessage;
             }

             public void setUserMessage(String userMessage) {
                 this.userMessage = userMessage;
             }

             public String getErrorMessage() {
                 return errorMessage;
             }

             public void setErrorMessage(String errorMessage) {
                 this.errorMessage = errorMessage;
             }
         }



    

