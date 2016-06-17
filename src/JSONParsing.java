import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParsing {

	  private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }

	  public static void main(String[] args) throws IOException, JSONException {
	    JSONObject json = readJsonFromUrl("http://jsoneditoronline.org/test/example.json");
	    System.out.println(json.get("name")+","+json.get("age")+","+json.get("employed")+","+json.getJSONObject("address").get("street")+","+json.getJSONObject("address").get("country")+","+json.getJSONObject("address").get("city"));
	    for(int i=0;i<json.getJSONArray("children").length();i++)
	    {
	    System.out.println(json.getJSONArray("children").getJSONObject(i).get("age")+","+json.getJSONArray("children").getJSONObject(i).get("name"));
	  }}
	}