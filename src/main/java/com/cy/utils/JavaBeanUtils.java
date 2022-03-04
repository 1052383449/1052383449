package com.cy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.TypeUtils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;
import java.text.ParseException;
import java.util.*;

import static com.alibaba.fastjson.util.TypeUtils.*;

/**
 * @author 吴鸿
 * @date 2019/9/20 14:12
 * @ClassName: JavaBeanUtil
 * @Description: 对javabean操作的工具
 */
public class JavaBeanUtils {
    //静态单例
    private static class SingletonHoler {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static JavaBeanUtils instance = new JavaBeanUtils();
    }

    private JavaBeanUtils() {
    }

    public static JavaBeanUtils getInstance() {
        return SingletonHoler.instance;
    }

    /**
     * 是否给属性设置默认值
     */
    private Boolean defaultValue;

    public Boolean getDefaultValue() {
        if (ObjectUtils.isEmpty(this.defaultValue)) {
            this.defaultValue = false;
        }
        return this.defaultValue;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 把dto转成map（深复制）
     *
     * @param javaObject
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object javaBeanToMap(Object javaObject) {
        SerializeConfig config = SerializeConfig.globalInstance;
        if (javaObject == null) {
            return null;
        }

        if (javaObject instanceof Map) {
            Map<Object, Object> map = (Map<Object, Object>) javaObject;

            JSONObject json = new JSONObject(map.size());

            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object key = entry.getKey();
                String jsonKey = TypeUtils.castToString(key);
                Object jsonValue = javaBeanToMap(entry.getValue());
                json.put(jsonKey, jsonValue);
            }

            return json;
        }

        if (javaObject instanceof Collection) {
            Collection<Object> collection = (Collection<Object>) javaObject;

            JSONArray array = new JSONArray(collection.size());

            for (Object item : collection) {
                Object jsonValue = javaBeanToMap(item);
                array.add(jsonValue);
            }

            return array;
        }

        Class<?> clazz = javaObject.getClass();

        if (clazz.isArray()) {
            int len = Array.getLength(javaObject);

            JSONArray array = new JSONArray(len);

            for (int i = 0; i < len; ++i) {
                Object item = Array.get(javaObject, i);
                Object jsonValue = javaBeanToMap(item);
                array.add(jsonValue);
            }

            return array;
        }

        ObjectSerializer serializer = config.getObjectWriter(clazz);
        if (serializer instanceof JavaBeanSerializer) {
            JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer) serializer;

            Map<String, Object> json = new HashMap<String, Object>();
            try {
                Map<String, Object> values = javaBeanSerializer.getFieldValuesMap(javaObject);
                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    json.put(entry.getKey(), javaBeanToMap(entry.getValue()));
                }
            } catch (Exception e) {
                throw new JSONException("toJSON error", e);
            }
            return json;
        }

        String text = JSON.toJSONString(javaObject);
        return JSON.parse(text);
    }

}
