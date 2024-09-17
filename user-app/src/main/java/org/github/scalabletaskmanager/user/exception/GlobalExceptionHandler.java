package org.github.scalabletaskmanager.user.exception;

import org.github.scalabletaskmanager.common.exception.GlobalException;
import org.github.scalabletaskmanager.common.exception.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDTO> generateRuntimeException(GlobalException e) {
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setDescription(e.getMessage());
        errorDTO.setErrorCode(e.getHttpStatus().value());

        return new ResponseEntity<>(errorDTO, e.getHttpStatus());
    }
}
