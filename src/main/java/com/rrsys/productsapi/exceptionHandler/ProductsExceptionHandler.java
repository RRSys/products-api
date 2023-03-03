package com.rrsys.productsapi.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

//ResponseEntityExceptionHandler captura exceções de resposta de entidades
@ControllerAdvice
public class ProductsExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("invalid.Message", null, LocaleContextHolder.getLocale());
        String errorMessage = ex.getCause().toString();

        return handleExceptionInternal(ex, new ErrorMessage(userMessage, errorMessage), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("product Not found", null, LocaleContextHolder.getLocale());
        String devMessage = ex.toString();
        return handleExceptionInternal(ex, new ErrorMessage(userMessage,devMessage), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
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
            public String getMensagemUsuario() {
                return errorMessage;
            }
            public void setMensagemUsuario(String mensagemUsuario) {
                this.errorMessage = mensagemUsuario;
            }


        }

    

