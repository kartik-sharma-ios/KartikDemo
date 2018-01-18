package com.dimts.eticketing.utils;


import com.dimts.eticketing.Constants.AppConstant;
import com.dimts.eticketing.model.http.Body;
import com.dimts.eticketing.model.http.HTTPData;

import java.net.HttpURLConnection;

public class WebServiceUtil 
{
	public static void initializeHeaderInRequest(HttpURLConnection httpURLConnection)
	{
		httpURLConnection.setRequestProperty(AppConstant.REQUEST_HEADER_CONTENT_TYPE_KEY, AppConstant.CONTENT_TYPE_JSON);
	}

	public static HTTPData initializeHttpData(String jsonRequest, int requestType, Boolean isMultipart)
	{
		//create body
		Body body = new Body();
		body.setJsonRequestString(jsonRequest);
		body.setMultipart(isMultipart);

		//create HttpData object
		HTTPData httpData = new HTTPData();
		httpData.setBody(body);
		httpData.setType(requestType);
		return httpData;
	}
}
