package com.dimts.eticketing.Manager;

import com.dimts.eticketing.model.Request.MasterRequest;
import com.dimts.eticketing.model.Response.MasterResponse;

public class ParseManager
{
    //Prepare JSON request string
	public static String prepareJsonRequest(MasterRequest requestObject) throws Exception
	{
		//Create JSON String
		String jsonRequestString = JacksonHandler.createJson(requestObject);
		return jsonRequestString;
	}

	//Prepare Web Service Object Model
	public static MasterResponse prepareWebServiceResponseObject(Class<?> classInstance, String jsonResponseString) throws Exception
	{
		//Get Response Object
		MasterResponse masterResponse = JacksonHandler.createResponse(jsonResponseString, classInstance);

		return masterResponse;
	}
}