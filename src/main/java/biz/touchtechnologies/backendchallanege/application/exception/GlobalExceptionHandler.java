package biz.touchtechnologies.backendchallanege.application.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import biz.touchtechnologies.backendchallanege.application.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

	private final Environment env;


	public GlobalExceptionHandler(Environment env) {
		this.env = env;
	}

	/**
	 * Metodo para manejar todas las exceptions basicas de spring y java No recibe
	 * operationCode ni submessages, para ellos utilizar el metodo homonimo
	 * sobrecargado
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		return this.handleExceptionInternal(ex, null, headers, statusCode, request);
	}

	private ResponseEntity<Object> handleExceptionInternal(Exception ex, String customMessage, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		log.error("Error: [type: " + ex.getClass().getCanonicalName() + ", message: " + ex.getLocalizedMessage() + ", "
				+ customMessage + "]");

		log.info("Tipo de error: {}", ex.getClass().getCanonicalName());
		ex.printStackTrace();

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

		String finalMessage = ex.getMessage();
		if (customMessage != null && !customMessage.trim().equals("")) {
			finalMessage = customMessage;
		}

		ResponseDto dto = new ResponseDto();
		dto.setDate(LocalDateTime.now());
		dto.setMessage(finalMessage);
		dto.setHttpCode(status.value());
		dto.setResponse(null);

		return new ResponseEntity<>(dto, headers, status);
	}

	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<Object> nullPointerExceptionHandler(NullPointerException ex, WebRequest request)
			throws Exception {
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, null, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(ExternalApiException.class)
	public ResponseEntity<Object> notFoundException(ExternalApiException ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Object> notFoundException(ObjectNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> badRequestException(BadRequestException ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		BindingResult result = ex.getBindingResult();

		List<String> messages = result.getFieldErrors().stream().map(error -> {
			return error.getField() + " " + error.getDefaultMessage();
		}).collect(Collectors.toList());

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String propiedadCausante = ex.getPropertyName();
		String tipoRequerido = ex.getRequiredType().getSimpleName();
		Object valorEnviado = ex.getValue();

		String mensajeFinal = "Error de tipo de dato invalido: la propiedad %s requiere del tipo %s y se le envio el valor %s";
		mensajeFinal = String.format(mensajeFinal, propiedadCausante, tipoRequerido, valorEnviado);
		return handleExceptionInternal(ex, mensajeFinal, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		final String parameterName = ex.getParameterName();
		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		final String pathVariableName = ex.getVariableName();
		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> genericException(Exception ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, null, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
