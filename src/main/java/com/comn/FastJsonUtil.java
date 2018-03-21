package com.comn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;

/**
 * fastjson工具类，依赖于阿里的fastjson
 */
public class FastJsonUtil {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.PrettyFormat  //是否需要格式化输出Json数据
    };

    /**
     * 对象转json字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 对象转json字符串
     *
     * @param object
     * @return
     */
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * 将Json数据转换成JSONObject
     *
     * @param jsonStr
     * @return
     */
    public static JSONObject toJsonObj(String jsonStr) {
        return (JSONObject) JSON.parse(jsonStr);
    }

    /**
     * json字符串转化为map
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> stringToMap(String jsonStr) {
        Map<?, ?> map = JSONObject.parseObject(jsonStr);
        return map;
    }

    /**
     * 将map转化为string
     *
     * @param map
     * @return
     */
    public static String MapToString(Map<?, ?> map) {
        String jsonStr = JSONObject.toJSONString(map);
        return jsonStr;
    }

}
