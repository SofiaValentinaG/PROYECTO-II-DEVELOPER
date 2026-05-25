package co.edu.uptc.developer.dto;

public class ResultDTO {
    private boolean isSuccessful;
    private String message;
    private Object data;

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