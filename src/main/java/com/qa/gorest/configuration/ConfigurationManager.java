package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.exception.APIFrameworkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream ip;

	public Properties initProp() {


		FileInputStream fip = null;
		prop = new Properties();

		String envName = System.getProperty("envName");
		System.out.println("Running test cases on" + envName);
		try {
		if (envName == null) {
			System.out.println("No env is given hence running test on QA" + envName);
			fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
			else {
				
				switch (envName.toLowerCase().trim()) {
				case "qa":
				fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;
				case "dev":
				fip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
				case "stage":
				fip = new FileInputStream("./src/test/resources/config/config.stage.properties");
				break;
				case "uat":
				fip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				break;
				case "prod":
				fip = new FileInputStream("./src/test/resources/config/config.properties");
				break;

			default:
				System.out.println("plz pass the right env" + envName);
				throw new APIFrameworkException("Please pass right env");
				}
			}
		}
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

}
}