package kr.gracelove.petcare.controller;

import kr.gracelove.petcare.service.DuplicateLoginId;
import kr.gracelove.petcare.service.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberErrorsAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateLoginId.class)
    public ResponseEntity<?> duplicateLoginId() {
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> memberNotFound() {
        return ResponseEntity.notFound().build();
    }
}
