package com.dimts.eticketing.Manager;

import android.util.Log;

import com.dimts.eticketing.Constants.AppConstant;
import com.dimts.eticketing.model.Request.MasterRequest;
import com.dimts.eticketing.model.Response.MasterResponse;
import com.dimts.eticketing.model.http.Body;
import com.dimts.eticketing.model.http.HTTPData;
import com.dimts.eticketing.utils.WebServiceUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceManager
{
	//Get Request Object
	public static MasterRequest getRequestObject(MasterRequest requestObject)
	{
		//Place common parameters
		return requestObject;
	}

	//Hit Web Service
	public static Object callWebService(String baseUrl, String targetUrl, HTTPData httpData) throws Exception
	{
			Object response = requestServer(baseUrl, targetUrl, httpData);
		     return response;
	}

	//Check Response Status
	public static void processResponseStatus(MasterResponse masterResponse) throws Exception
	{

	}

	/**
	 * Common Function to request from server
	 */
	private static Object requestServer(String baseUrl, String targetUrl, HTTPData httpData) throws Exception
	{
		HttpURLConnection httpURLConnection = null;
		Object response = null;
		OutputStream outputStream;
		InputStream inputStream;
		int ch;

		try
		{
			System.setProperty("http.keepAlive", "false");
			URL url = new URL(baseUrl + targetUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();

			//Set Timeout for Connection and ReadData
			httpURLConnection.setConnectTimeout( AppConstant.TIME_CONNECTION_TIMEOUT); //Timeout until a connection is established
			httpURLConnection.setReadTimeout(AppConstant.TIME_CONNECTION_TIMEOUT); //Timeout for waiting for data to come

			WebServiceUtil.initializeHeaderInRequest(httpURLConnection);

			httpURLConnection.setDoInput(true);

			if(httpData.getType() == AppConstant.HTTP_METHOD_POST)
			{
				httpURLConnection.setDoOutput(true);
				final String boundary = "-------------" + System.currentTimeMillis();
				Body body = httpData.getBody();
				httpURLConnection.setRequestProperty(AppConstant.REQUEST_HEADER_CONTENT_TYPE_KEY, AppConstant.CONTENT_TYPE_JSON);

				//open
				httpURLConnection.connect();
				// prepare json object
				if(body != null) //safety check
				{
					//setup send
					outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());

					Log.i(AppConstant.LOG_TAG, "REQUEST URL : " + (baseUrl + targetUrl) + "\nJSON REQUEST : " + body.getJsonRequestString());
						outputStream.write(body.getJsonRequestString().getBytes());
						//clean up
						outputStream.flush();
				}
			}
			else
			{
				Log.i(AppConstant.LOG_TAG, "REQUEST URL : " + (baseUrl + targetUrl));

				httpURLConnection.setDoOutput(false);
			}

			//Get StatusCode
			int statusCode = httpURLConnection.getResponseCode();
				inputStream = new BufferedInputStream( (statusCode == HttpURLConnection.HTTP_OK) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream());

					StringBuffer stringBuffer = new StringBuffer();
					while ((ch = inputStream.read()) != -1)
					{
						stringBuffer.append((char) ch);
					}

					response = stringBuffer.toString();

					Log.i(AppConstant.LOG_TAG, "JSON RESPONSE: " + response);

		}

		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			httpURLConnection.disconnect();
		}

		return response;
	}



}
