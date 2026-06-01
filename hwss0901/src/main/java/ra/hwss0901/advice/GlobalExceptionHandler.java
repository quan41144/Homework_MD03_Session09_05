package ra.hwss0901.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.hwss0901.exception.DuplicateResourceException;
import ra.hwss0901.exception.ResourceNotFoundException;
import ra.hwss0901.model.dto.response.ApiResponse;
import ra.hwss0901.model.entity.Employee;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ApiResponse<Map<String, String>> apiResponse = ApiResponse.<Map<String, String>>builder()
                .status("FAIL")
                .message("Dữ liệu không hợp lệ")
                .data(errors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Employee>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse<>(
                "FAIL",
                ex.getMessage(),
                null,
                HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Employee>> handleDuplicateResourceException(DuplicateResourceException ex) {
        return new ResponseEntity<>(new ApiResponse<>(
                "FAIL",
                ex.getMessage(),
                null,
                HttpStatus.CONFLICT
        ), HttpStatus.CONFLICT);
    }
}
