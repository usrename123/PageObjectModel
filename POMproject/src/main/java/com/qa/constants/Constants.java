package com.qa.constants;

public class Constants {
	  public final int WAIT_COMMON = 7;

	  public String getAppURL() throws Exception {
	    String environmentFromConfig = System.getProperty("Environment").toLowerCase().trim();
	    if (environmentFromConfig.startsWith("qa"))
	      return "https://rahulshettyacademy.com/seleniumPractise/#/";

	    else if (environmentFromConfig.startsWith("dev"))
	      return "https://dev-tekarch.firebaseapp.com/";

	    else if (environmentFromConfig.startsWith("prd"))
	      return "https://prd-tekarch.firebaseapp.com/";

	    else {
	      return "set environment correctly...";
	    }
	  }
	}
