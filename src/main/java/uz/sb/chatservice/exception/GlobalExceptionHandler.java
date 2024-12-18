package uz.sb.chatservice.exception;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import uz.sb.chatservice.domain.entity.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                ExceptionUtils.getStackTrace(e)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
//
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleInternalException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                e.getMessage(),
//                ExceptionUtils.getStackTrace(e)
//
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
}
