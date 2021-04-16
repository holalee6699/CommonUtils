package com.github.holalee.common;


import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *         <!--   xml转Json     -->
 *         <dependency>
 *             <groupId>net.sf.json-lib</groupId>
 *             <artifactId>json-lib</artifactId>
 *             <classifier>jdk15</classifier>
 *             <version>2.4</version>
 *         </dependency>
 */
public class XmlJsonUtils {

    /**
     * JSON转xml
     * @param jsonString
     * @return
     */
    public static String json2xml(String jsonString){
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false);
        String xml = xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        xml = xml.replace("<o>", "").replace("</o>", "");
        xml = xml.replaceAll("\r\n", "").concat("\r\n");
        return xml;
    }

    /**
     * map转xml
     * @param params
     * @return
     */
    public static String toXml(Map<String, String> params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        buf.append("<xml>");
        for(String key : keys){
            buf.append("<").append(key).append(">");
            buf.append("<![CDATA[").append(params.get(key)).append("]]>");
            buf.append("</").append(key).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }

    /**
     * xml转json
     */

    public static String xml2json(String xmlString) {
        JSONObject jsonObject = XML.toJSONObject(xmlString);

        return jsonObject.toString(3);
    }
}
