package common;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LeitorJson {
  public Object getFromJson(String jsonFile, String JsonData) {
    JSONParser parser = new JSONParser();
    JSONObject jsonDataObj = null;
      try {
        Object jsonFileObj = parser.parse(new FileReader("Json\\" + jsonFile + ".json"));
        jsonDataObj = (JSONObject) jsonFileObj;
      }
      catch (IOException e) {
        e.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return (Object) jsonDataObj.get(JsonData);
  }
}
