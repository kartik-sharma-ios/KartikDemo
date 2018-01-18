package com.dimts.eticketing.Manager;


import com.dimts.eticketing.model.Request.MasterRequest;
import com.dimts.eticketing.model.Response.MasterResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class JacksonHandler
{
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static String createJson(MasterRequest requestObject) throws Exception
	{
		String jsonString = "";

		//Set the Property Naming Strategy
		OBJECT_MAPPER.setPropertyNamingStrategy(new CustomizedPropertyNamingStrategy());
				
		try
		{
			jsonString = OBJECT_MAPPER.writeValueAsString(requestObject);
		}
		catch (Exception exception)
		{
		}

		return jsonString;
	}

	public static MasterResponse createResponse(String jsonResponseString, Class<? extends Object> classInstance) throws Exception
	{
		MasterResponse masterResponse = null;

		//Set the Property Naming Strategy
		OBJECT_MAPPER.setPropertyNamingStrategy(new CustomizedPropertyNamingStrategy());
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try
		{
			masterResponse = (MasterResponse)OBJECT_MAPPER.readValue(jsonResponseString, classInstance);
		}
		catch (Exception exception)
		{
		}

		return masterResponse;
	}
	
	private static class CustomizedPropertyNamingStrategy extends PropertyNamingStrategy
	{
		/**
		 * Auto Generated serialVersionUID
		 */
		private static final long serialVersionUID = 837479668977910450L;

		@Override
		public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
		{
			return convertName(defaultName);
		}
		
		@Override
		public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
		{
			return convertName(defaultName);
		}
		
		private String convertName(String defaultName)
		{
			return (Character.toUpperCase(defaultName.charAt(0)) + defaultName.substring(1));
		}
	}


}
