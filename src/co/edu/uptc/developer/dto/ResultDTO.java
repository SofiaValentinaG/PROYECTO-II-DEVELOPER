package co.edu.uptc.developer.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDTO {
<<<<<<< HEAD

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
=======
    private boolean isSuccessful;
    private String message;
    private Object data;
>>>>>>> c8e28efd0ea7b21e9ed5cd23432eec6d024c985e

    public ResultDTO(boolean isSuccessful, String message, Object data) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultDTO [isSuccessful=" + isSuccessful + ", message=" + message + ", data=" + data + "]";
	}

}