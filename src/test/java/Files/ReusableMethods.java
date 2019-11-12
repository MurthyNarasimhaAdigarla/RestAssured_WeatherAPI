package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static JsonPath rawToJson(Response res) {
		String responsestring = res.asString();

		JsonPath js = new JsonPath(responsestring);

		return js;
	}

	public static XmlPath RawToXml(Response r) {
		String responsestring = r.asString();

		XmlPath xml = new XmlPath(responsestring);

		return xml;
	}

}
