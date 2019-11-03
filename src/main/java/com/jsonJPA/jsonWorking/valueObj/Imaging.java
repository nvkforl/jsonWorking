package com.jsonJPA.jsonWorking.valueObj;

public class Imaging
{
    private Clm_flds[] clm_flds;

    private String policyNumber;

    private String name;

    private String location;

    private String time;

    public Clm_flds[] getClm_flds ()
    {
        return clm_flds;
    }

    public void setClm_flds (Clm_flds[] clm_flds)
    {
        this.clm_flds = clm_flds;
    }

    public String getPolicyNumber ()
    {
        return policyNumber;
    }

    public void setPolicyNumber (String policyNumber)
    {
        this.policyNumber = policyNumber;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [clm_flds = "+clm_flds+", policyNumber = "+policyNumber+", name = "+name+", location = "+location+", time = "+time+"]";
    }
}