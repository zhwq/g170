package homework.utils;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONHelper {
  private static final String TAG = "JSONHelper";
  private static final GsonBuilder gsonb;

  static {
    gsonb = new GsonBuilder();
    gsonb.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

      @Override
      public Date deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context)
        throws JsonParseException {
        String date = json.getAsJsonPrimitive().getAsString();
        String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\+.*)?\\))\\/";
        Pattern pattern = Pattern.compile(JSONDateToMilliseconds);
        Matcher matcher = pattern.matcher(date);
        String result = matcher.replaceAll("$2");
        try {
          return new Date(new Long(result));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

  public static <T> ArrayList<T> getList(String JSONString, Class<T> classOfT) {
    final ArrayList<T> data = new ArrayList<T>();

    JSONArray array = null;
    try {
      array = new JSONArray(JSONString);
    } catch (final JSONException e) {
      e.printStackTrace();
    }

    if (array != null) {
      final Gson gson = gsonb.create();

      for (int i = 0; i < array.size(); i++) {
        try {
          final JSONObject object = array.getJSONObject(i);
          final T entity = gson.fromJson(object.toString(), classOfT);
          data.add(entity);
        } catch (final JSONException e) {
          e.printStackTrace();
        }
      }
    }

    return data;
  }

  public static <T> T getObject(String JSONString, Class<T> classOfT) {
    try {
      Gson gson = gsonb.create();
      return gson.fromJson(JSONString, classOfT);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String getStringFromObject(Object src) {
    final Gson gson = gsonb.create();
    return gson.toJson(src);
  }






  /**
   * 将json对象转换为map集合，通过此方法获取存放map集合键的list集合
   * @return
   */
  public static List<Object> mapKeys(Map<?,?> map){
    List<Object> keysList = new ArrayList<Object>();
    String columnStr="column";
    for(int i=0;i<map.keySet().size();i++){
      keysList.add(columnStr+(i+1));
    }
    System.out.println(keysList.size());
    return keysList;
  }


//	/**
//	 * 将传入的json字符串转换为元素为map集合的List集合
//	 * @param jsonArrStr
//	 * @return
//	 */
//	public static List<Map<String, Object>> jsonObjList(String jsonArrStr) {
//		List<Map<String, Object>> jsonObjList = new ArrayList<Map<String, Object>>();
//		List<?> jsonList = Test.jsonToList(jsonArrStr);
//		Gson gson = new Gson();
//		for (Object object : jsonList) {
//			String jsonStr = gson.toJson(object);
//			Map<?, ?> json = Test.jsonToMap(jsonStr);
//			jsonObjList.add((Map<String, Object>) json);
//		}
//		return jsonObjList;
//	}


  /**
   * 将传入的json字符串解析为List集合
   * @param jsonStr
   * @return
   */
  public static List<?> jsonToList(String jsonStr) {
    List<?> ObjectList = null;
    Gson gson = new Gson();
    Type type = new com.google.gson.reflect.TypeToken<List<?>>() {}.getType();
    ObjectList = gson.fromJson(jsonStr, type);
    return ObjectList;
  }

  /**
   * 将传入的json字符串解析为Map集合
   * @param jsonStr
   * @return
   */
  public static Map<?, ?> jsonToMap(String jsonStr) {
    Map<?, ?> ObjectMap = null;
    Gson gson = new Gson();
    Type type = new com.google.gson.reflect.TypeToken<Map<?,?>>() {}.getType();
    ObjectMap = gson.fromJson(jsonStr, type);
    return ObjectMap;
  }
}
