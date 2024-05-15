package Framework;

import java.util.HashMap;

public class WrapperResponseGetterSetter {
    private static HashMap<String, Object> sTestData = new HashMap<>();
    public static void setsTestData(String key, Object object){
        sTestData.put(key,object);
    }

    public static Object getsTestData(String key){
        return sTestData.get(key);
    }

}
