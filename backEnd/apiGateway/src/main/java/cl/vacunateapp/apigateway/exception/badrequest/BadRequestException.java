package cl.vacunateapp.apigateway.exception.badrequest;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BadRequestException extends RuntimeException{

    public static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
