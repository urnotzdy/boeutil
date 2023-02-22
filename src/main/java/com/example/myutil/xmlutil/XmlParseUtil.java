package com.example.myutil.xmlutil;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class XmlParseUtil {

    /**
     * 将xml字符串转换为json
     *
     * @param xmlContent xml字符串
     * @return json字符串
     */
    public static String parseXMlToString(String xmlContent) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        //将xml转为json（注：如果是元素的属性，会在json里的key前加一个@标识）
        String content = xmlSerializer.read(xmlContent).toString();
        return content;
    }

    /**
     * 根据json字符串获取body数组中的第一个对象
     *
     * @param content xml转换后的json对象
     * @return body数据的第一个对象，默认只有一个对象
     */
    public static String getBodyFirstContent(String content) {
        JSONObject bodyObj = JSON.parseObject(content, JSONObject.class);
        JSONArray bodyArray = bodyObj.getJSONArray("Body");
        if (null == bodyArray || bodyArray.isEmpty()) {
            return "";
        }
        String body = bodyArray.get(0).toString();
        return body;
    }

    /**
     * 根据json字符串获取header数据
     *
     * @param content xml转换后的json对象
     * @return header数据内容
     */
    public static String getHeader(String content) {
        JSONObject headerObj = JSON.parseObject(content, JSONObject.class);
        String header = headerObj.getString("Header");
        return header;
    }

    /**
     * 根据json字符串获取header数据和body数据
     *
     * @param content xml转换后的json对象
     * @return header和body（默认只有一个对象）数据内容
     */
    public static JSONObject getHeaderAndBody(String content) {
        JSONObject contentObj = JSON.parseObject(content, JSONObject.class);

        JSONObject headerObj = contentObj.getJSONObject("Header");
        JSONArray bodyArray = contentObj.getJSONArray("Body");
        if (null == bodyArray || bodyArray.isEmpty()) {
            return headerObj;
        }
        Object bodyObj = bodyArray.get(0);

        JSONObject finalObj = new JSONObject();
        finalObj.put("Header",headerObj);
        finalObj.put("Body",bodyObj);

        return finalObj;
    }
}
