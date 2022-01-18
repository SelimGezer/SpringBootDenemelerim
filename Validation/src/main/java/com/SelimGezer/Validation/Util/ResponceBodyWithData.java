package com.SelimGezer.Validation.Util;

public class ResponceBodyWithData {

   public String message;
   public Object body;

    public ResponceBodyWithData(String message, Object body) {
        this.message = message;
        this.body = body;
    }

    public ResponceBodyWithData() {
    }
}
