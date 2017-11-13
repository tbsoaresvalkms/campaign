package com.tbsoares.campaign.configurations;

import com.tbsoares.campaign.exceptions.*;
import com.tbsoares.campaign.models.ApiError;
import com.tbsoares.customer.exceptions.CampaignClientException;
import com.tbsoares.customer.exceptions.CustomerNotFoundException;
import com.tbsoares.customer.models.Customer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Invalid parameters";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<Object> handleCampaignNotFound(CampaignNotFoundException ex) {
        String error = "Campaign not found";
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
    }

    @ExceptionHandler(AssociateNotFoundException.class)
    public ResponseEntity<Object> handleAssociateNotFound(AssociateNotFoundException ex) {
        String error = "Associate not found";
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<Object> handleAlreadyRegistered(AlreadyRegisteredException ex) {
        String error = "Associate already registered in campaign";
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, error, ex));
    }

    @ExceptionHandler(StartDateAfterEndDateException.class)
    public ResponseEntity<Object> handleStartDateAfterEndDate(StartDateAfterEndDateException ex) {
        String error = "Start date after end date";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
        String error = "Customer not found";
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
    }

    @ExceptionHandler(CampaignClientException.class)
    public ResponseEntity<Object> handleCampaignClientException(CampaignClientException ex) {
        String error = "Internal error";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
