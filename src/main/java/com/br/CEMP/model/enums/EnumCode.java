package com.br.CEMP.model.enums;

public enum EnumCode {
    USR000("Already registered this username"),
    USR001("Already registered this email"),
    USR003("User find by id not found"),
    EVT000("Already exists event with tittle"),
    EVT001("Event find by id not found"),
    EVT002("Event date invalid");

    private String message;

    EnumCode(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
