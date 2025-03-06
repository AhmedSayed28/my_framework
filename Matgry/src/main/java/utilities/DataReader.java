package utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DataReader {
    private static final Map<String, JSONObject> jsonFiles = new HashMap<>();

    public static void loadFiles(String... filePaths) {
        JSONParser parser = new JSONParser();
        for (String path : filePaths) {
            try {
                Object obj = parser.parse(new FileReader(path));
                jsonFiles.put(path, (JSONObject) obj);
            } catch (Exception e) {
                e.printStackTrace();
                jsonFiles.put(path, new JSONObject()); // تجنب NullPointerException
            }
        }
    }

    // جلب قيمة معينة من أي ملف تم تحميله
    public static String getValue(String filePath, String keyPath) {
        JSONObject jsonObject = jsonFiles.getOrDefault(filePath, new JSONObject());
        String[] keys = keyPath.split("\\."); // Split nested keys

        Object value = jsonObject;
        for (String key : keys) {
            if (value instanceof JSONObject) {
                value = ((JSONObject) value).get(key);
            } else {
                return null; // Key not found
            }
        }
        return value != null ? value.toString() : null;
    }

}
