package com.example.lab.DTO;


import java.sql.Date;

public class CustomerPoiDTO {
    private String type;
    private String value;
    private Date start;
    private Date end;

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

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getStart()
    {
        return start;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public Date getEnd()
    {
        return end;
    }
}

