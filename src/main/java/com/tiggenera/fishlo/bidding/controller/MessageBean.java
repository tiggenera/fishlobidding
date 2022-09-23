package com.tiggenera.fishlo.bidding.controller;


public class MessageBean {
    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "MessageBean [name=" + name + ", message=" + message + "]";
	}

}
