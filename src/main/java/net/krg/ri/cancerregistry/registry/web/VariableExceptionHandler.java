package net.krg.ri.cancerregistry.registry.web;

import net.krg.ri.cancerregistry.registry.domain.VariableNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
class VariableExceptionHandler {

    @ExceptionHandler(VariableNotFoundException.class)
    public ProblemDetail handleVariableNotFoundException(VariableNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
