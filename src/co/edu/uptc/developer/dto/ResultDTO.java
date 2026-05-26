package co.edu.uptc.developer.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDTO {

	private boolean isSuccessful;
	private String message;
	private Object object;
	private List<String> listMessageError;

	public ResultDTO() {
		this.listMessageError = new ArrayList<> ();
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<String> getListMessageError() {
		return listMessageError;
	}

	public void setListMessageError(List<String> listMessageError) {
		this.listMessageError = listMessageError;
	}

	@Override
	public String toString() {
		return "ResultDTO [isSuccessful=" + isSuccessful + ", message=" + message + ", object=" + object
				+ ", listMessageError=" + listMessageError + "]";
	}

}
