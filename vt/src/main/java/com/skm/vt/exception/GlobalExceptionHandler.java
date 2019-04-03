package com.skm.vt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MultipartException.class)
	public RedirectView handleError(MultipartException e, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
		return new RedirectView("uploadError.html");
	}

	@ExceptionHandler(APIException.class)
	protected ResponseEntity<?> handleProductNotFoundException(final APIException e) {
		ErrorInfo response = new ErrorInfo.Builder()
				.setStatus(HttpStatus.BAD_REQUEST).setCode(100).setMessage("Error processing request.")
				.setDeveloperMessage(e.getLocalizedMessage()).setThrowable(e)
				.build();
		return new ResponseEntity<ErrorInfo>(response, HttpStatus.NOT_FOUND);
	}
}