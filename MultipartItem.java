package com.dimts.eticketing.model.http;

/**
 * Created by Ramit Yadav on 14-02-2017.
 */

public class MultipartItem
{
    private String fieldName;
    private String fileName;
    private String path;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
