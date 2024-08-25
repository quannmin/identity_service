package com.springBoot.identity_service.exception;
import com.springBoot.identity_service.dto.request.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException exception) {
        log.error("Exception: ", exception);ApiResponse apiResponse = new ApiResponse();

    apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
    apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> appExceptionHandler(AppException execption) {
        ErrorCode errorCode = execption.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> accessDeniedExceptionHandler(AccessDeniedException execption) {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED_EXCEPTION;
        return ResponseEntity.status(errorCode.getStatusCode()).body(
          ApiResponse.builder()
                  .code(errorCode.getCode())
                  .message(errorCode.getMessage())
                  .build()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<List<ApiResponse>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException execption) {

        //Danh sách chứa các thông báo lỗi
        List<ApiResponse> errors = new ArrayList<>();

        // Duyệt qua tất cả các lỗi
        for (FieldError fieldError : execption.getBindingResult().getFieldErrors()) {
            String enumKey = fieldError.getDefaultMessage();
            ErrorCode errorCode = ErrorCode.INVALID_MESSAGE;
            Map<String, Object> attributes = null;
            try {
                errorCode = ErrorCode.valueOf(enumKey);
                var constraintViolations = fieldError.unwrap(ConstraintViolation.class);
                attributes = constraintViolations.getConstraintDescriptor().getAttributes();
            } catch (Exception e) {
                // Xử lý nếu có lỗi không mong muốn
            }

            String errorMessage = Objects.nonNull(attributes) ?
                    mapAttribute(errorCode.getMessage(), attributes) :
                    errorCode.getMessage();

            ApiResponse apiResponse = ApiResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorMessage)
                    .build();
            errors.add(apiResponse);
        }
        return ResponseEntity.badRequest().body(errors);

//        String enumKey = execption.getFieldError().getDefaultMessage();
//        ErrorCode errorCode = ErrorCode.INVALID_MESSAGE;
//        Map<String, Object> attributes = null;
//        try {
//            errorCode = ErrorCode.valueOf(enumKey);
//
//                var constraintViolations = execption.getBindingResult().getAllErrors().getFirst()
//                        .unwrap(ConstraintViolation.class);
//
//                attributes = constraintViolations.getConstraintDescriptor().getAttributes();
//        } catch (Exception e) {
//
//        }

//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(Objects.nonNull(attributes) ?
//                mapAttribute(errorCode.getMessage(), attributes)
//                : errorCode.getMessage());

//        return ResponseEntity.badRequest().body(apiResponse);
    }

    private String mapAttribute(String message, Map<String, Object> attribute) {
        String minValue = attribute.get(MIN_ATTRIBUTE).toString();

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
