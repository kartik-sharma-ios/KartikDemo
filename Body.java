package com.dimts.eticketing.model.http;

import java.util.ArrayList;


public class Body
{
    private boolean isMultipart;
    private String jsonRequestString;
    private ArrayList<MultipartItem> multipartFileName;

    public String getJsonRequestString()
    {
        return jsonRequestString;
    }

    public void setJsonRequestString(String jsonRequestString)
    {
        this.jsonRequestString = jsonRequestString;
    }

    public ArrayList<MultipartItem> getMultipartFileName()
    {
        return multipartFileName;
    }

    public void setMultipartFileName(ArrayList<MultipartItem> multipartFileName)
    {
        this.multipartFileName = multipartFileName;
    }

    public boolean isMultipart() {
        return isMultipart;
    }

    public void setMultipart(boolean multipart) {
        isMultipart = multipart;
    }
}
