package TestRunner;

import java.io.FileInputStream;
import java.util.Properties;

public class MainClass {

	public Properties props = new Properties();

	public static void main(String[] args) {

		System.out.println("Test");

	}

	public void getData() throws Throwable {
		System.out.println("welcome to restassured");

		FileInputStream fis = new FileInputStream(
				"\\src\\test\\java\\Files\\environment.properties");
		props.load(fis);

	}

}
