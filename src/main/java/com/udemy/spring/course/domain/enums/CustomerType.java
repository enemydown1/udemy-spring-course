package com.udemy.spring.course.domain.enums;

public enum CustomerType {
    PHYSICAL_PERSON(1, "Physical Person"),
    LEGAL_PERSON(2, "Legal person");

    private int code;
    private String description;

    private CustomerType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return code;
    }

    public String getDescription(){
        return description;
    }

    public static CustomerType toEnum(Integer code){
        if (code == null) {
            return null;
        }
        for (CustomerType x: CustomerType.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid code: "+ code);
    }
}
