package biz.touchtechnologies.backendchallanege.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	@JsonProperty(value = "http_code")
	private int httpCode;

	private Object response;

	private LocalDateTime date;

	public ResponseDto() {
		this.date = LocalDateTime.now();
	}

	public ResponseDto(Object response) {
		this.response = response;
		this.httpCode = HttpStatus.OK.value();
		this.date = LocalDateTime.now();
	}

	public ResponseDto(Object response, int httpCode) {
		this.response = response;
		this.httpCode = httpCode;
		this.date = LocalDateTime.now();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ResponseDto{" +
				"message='" + message + '\'' +
				", httpCode=" + httpCode +
				", response=" + response +
				", date=" + date +
				'}';
	}
}
