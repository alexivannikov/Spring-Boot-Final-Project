package root.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import root.model.dto.ErrorDto;
import root.model.exception.TransferException;

@RestControllerAdvice
public class TransferControllerException {
    @ExceptionHandler(TransferException.class)

    public ResponseEntity<ErrorDto> handleTransferException(TransferException transferException) {
        ErrorDto errorDto = new ErrorDto(transferException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}

