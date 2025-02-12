package com.example.lab.DTO;


public class TypeValue {
    private String type;
    private String value;

    public TypeValue()
    {

    }

    public TypeValue(String type, String value) {
        this.type = type;
        this.value = value;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return type;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getValue()
    {
        return value;
    }

    // overloading the default .equals() method
    public Boolean equals(TypeValue typeValue)
    {
        if (this.getType().equals(typeValue.getType()) && this.getValue().equals(typeValue.getValue()))
        {
            return true;
        }
        return false;
    }
}

