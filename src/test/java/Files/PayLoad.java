package Files;

public class PayLoad {

	public static String getbody() {
		String postbody = "{\r\n" + "    \"userId\": \"21\",\r\n" + "    \"id\": \"18\",\r\n"
				+ "    \"title\": \"this is project\",\r\n" + "    \"body\": \"this is REST-Assured\"\r\n" + "   }";

		return postbody;

	}

	public static String getWeatherData(String externalidvalue, String namestring)

	{
		String weather = "{\r\n" + "   \"external_id\": \"" + externalidvalue + "\",\r\n" + "   \"name\": \""
				+ namestring + "\",\r\n" + "   \"latitude\": 12.34,\r\n" + "   \"longitude\": 56.78,\r\n"
				+ "   \"altitude\": 933\r\n" + "}";
		return weather;

	}


	public static String getWeather()

	{
		String weather = "{\n" +
				"    \"external_id\": \"dfhdjc\",\n" +
				"    \"name\": \"Tuni Test Station\",\n" +
				"    \"latitude\": 12.34,\n" +
				"    \"longitude\": 56.78,\n" +
				"    \"altitude\": 901,\n" +
				"    \"heat\" : 56\n" +
				"}";
		return weather;

	}

}
