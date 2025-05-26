package com.br.CEMP.model.enums;

public enum EnumCode {
    USR000("User find by userName not found"),
    USR001("User find by email not found"),
    USR003("User find by id not found");

    private String message;

    EnumCode(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
