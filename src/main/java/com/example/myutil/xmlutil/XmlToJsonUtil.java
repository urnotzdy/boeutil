package com.example.myutil.xmlutil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;

import java.util.List;

@Deprecated
public class XmlToJsonUtil {

    public static void main(String[] args) {
        String xmlString = "<clinicalDocument>\n" +
                "    <documentHeader>\n" +
                "        <realmCode></realmCode>\n" +
                "        <typeId>REPORT</typeId>\n" +
                "        <template></template>\n" +
                "        <id>266793</id>\n" +
                "        <title>检查报告</title>\n" +
                "        <effectiveTime>2021-08-17 14:22:32</effectiveTime>\n" +
                "        <confidentiality code=\"级别代码\">级别名称</confidentiality>\n" +
                "        <versionNumber></versionNumber>\n" +
                "        <author id=\"医生编号\">yj01</author>\n" +
                "        <custodian>合肥京东方医院</custodian>\n" +
                "        <patient>\n" +
                "            <medicareNo></medicareNo>\n" +
                "            <admvisitNo>656395</admvisitNo>\n" +
                "            <medRecordNo></medRecordNo>\n" +
                "            <healthCardNo></healthCardNo>\n" +
                "            <certificate>\n" +
                "                <name code=\"证件类型代码\">身份证号</name>\n" +
                "                <value>340123197808150045</value>\n" +
                "            </certificate>\n" +
                "            <addr desc=\"现住址\">\n" +
                "                <text>完整地址</text>\n" +
                "                <houseNumber>xx号xx小区xx栋xx单元</houseNumber>\n" +
                "                <streetName>xx大道</streetName>\n" +
                "                <township>xx乡镇</township>\n" +
                "                <county>xx区</county>\n" +
                "                <city>xx市</city>\n" +
                "                <state>xx省</state>\n" +
                "                <postalCode>510000</postalCode>\n" +
                "            </addr>\n" +
                "            <name>胡传发</name>\n" +
                "            <telecom>020-87815102</telecom>\n" +
                "            <administrativeGender code=\"2\">女</administrativeGender>\n" +
                "            <maritalStatus code=\"20\">已婚</maritalStatus>\n" +
                "            <ethnicGroup code=\"01\">汉族</ethnicGroup>\n" +
                "            <age unit=\"岁\" value=\"60\" />\n" +
                "        </patient>\n" +
                "        <participant>\n" +
                "            <code code=\"1\">家庭关系描述</code>\n" +
                "            <addr desc=\"联系人地址\">\n" +
                "                <text>完整地址</text>\n" +
                "                <houseNumber>xx号</houseNumber>\n" +
                "                <streetName>xx大道</streetName>\n" +
                "                <township>xx乡镇</township>\n" +
                "                <county>xx区</county>\n" +
                "                <city>xx市</city>\n" +
                "                <state>xx省</state>\n" +
                "                <postalCode>02368</postalCode>\n" +
                "            </addr>\n" +
                "            <telecom>999-999-999999</telecom>\n" +
                "            <name>联系人姓名</name>\n" +
                "        </participant>\n" +
                "    </documentHeader>\n" +
                "    <structuredBody>\n" +
                "        <E0004 desc=\"姓名\">董雪梅</E0004>\n" +
                "        <E0005 desc=\"性别代码\">2</E0005>\n" +
                "        <E0006 desc=\"性别描述\">女</E0006>\n" +
                "        <E0008 desc=\"年龄\">43</E0008>\n" +
                "        <E0148 desc=\"病人类型代码\">H</E0148>\n" +
                "        <E0149 desc=\"病人类型名称\">体检</E0149>\n" +
                "        <E0156 desc=\"科室id\"></E0156>\n" +
                "        <E0077 desc=\"科室\">健康管理中心</E0077>\n" +
                "        <E0002 desc=\"住院号\"></E0002>\n" +
                "        <E0000 desc=\"门诊号\"></E0000>\n" +
                "        <E0154 desc=\"病床id\"></E0154>\n" +
                "        <E0075 desc=\"病床\"></E0075>\n" +
                "        <section code=\"S0048\" desc=\"诊断\">\n" +
                "            <text />\n" +
                "            <E07 desc=\"医生填写的诊断\"></E07>\n" +
                "            <E01 desc=\"诊断名称\"></E01>\n" +
                "            <E02 desc=\"诊断代码\"></E02>\n" +
                "        </section>\n" +
                "        <section code=\"S0076\" desc=\"检查记录\">\n" +
                "            <text />\n" +
                "            <E01 desc=\"检查号\">225415</E01>\n" +
                "            <E02 desc=\"检查设备编码\">1</E02>\n" +
                "            <E03 desc=\"检查设备名称\">CT</E03>\n" +
                "            <E04 desc=\"检查部位代码\">170400002</E04>\n" +
                "            <E05 desc=\"检查部位名称\">头颅CT（体检）</E05>\n" +
                "            <E06 desc=\"检查部位医生手写\"></E06>\n" +
                "            <E07 desc=\"检查部位\">头颅CT（体检）</E07>\n" +
                "            <E08 desc=\"检查所见\">双侧大脑半球对称，脑实质密度未见异常。脑室系统无扩大，各脑池、沟、裂无增宽、加深。脑中线结构居中。所见颅骨未见异常。 胸廓对称，两肺纹理清晰，肺内未见明显实质性病灶及其它明显异常密度。气管及主要支气管通畅。两侧肺门结构清晰，纵隔、肺门未见肿大淋巴结影。胸腔内未见积液征。</E08>\n" +
                "            <E09 desc=\"检查结果\">颅脑CT检查未见异常。 胸部CT检查未见明显异常。</E09>\n" +
                "            <E10 desc=\"检查医师代码\">10065204</E10>\n" +
                "            <E11 desc=\"检查医师姓名\">周歉歉</E11>\n" +
                "            <E12 desc=\"审核医师代码\">10175916</E12>\n" +
                "            <E13 desc=\"审核医师姓名\">赵金影</E13>\n" +
                "            <E14 desc=\"记录者代码\">10165662</E14>\n" +
                "            <E15 desc=\"记录者姓名\">王省伟</E15>\n" +
                "            <E16 desc=\"检查日期\">2021-08-17</E16>\n" +
                "            <E17 desc=\"检查时间\">08:33:04</E17>\n" +
                "            <E18 desc=\"报告号\">266793</E18>\n" +
                "            <E19 desc=\"审核日期\">2021-08-17</E19>\n" +
                "            <E20 desc=\"审核时间\">14:22:30</E20>\n" +
                "            <E21 desc=\"检查项目描述\"></E21>\n" +
                "            <E22 desc=\"检查方法描述\"></E22>\n" +
                "            <E23 desc=\"报告日期\">2021-08-17</E23>\n" +
                "            <E24 desc=\"报告时间\">08:51:00</E24>\n" +
                "            <E25 desc=\"医嘱号\">570975||24</E25>\n" +
                "            <E26 desc=\"图像号\">TI210817CT1037</E26>\n" +
                "        </section>\n" +
                "    </structuredBody>\n" +
                "</clinicalDocument>";
        System.out.println(xmlToJson(xmlString));
    }

    /*
     * xml转json
     */
    public static String xmlToJson(String xml) {
        try {
            // xmlStr ==> Document
            Document doc = DocumentHelper.parseText(xml);

            // Document ==> jsonStr
            Element rootEle = doc.getRootElement();
            JSONObject json = new JSONObject();
            dom4j2Json(rootEle, json);

            // 为了让JSON中包含根元素的key，才创建了resultJSON对象
            JSONObject resultJSON = new JSONObject();
            resultJSON.put(rootEle.getName(), json);

            return resultJSON.toJSONString();
        } catch (DocumentException e) {
            System.out.println("数据解析失败");   // 可改为打印日志
        }
        return null;
    }


    /**
     * 将element里面的内容存入参数json中
     *
     * @param element
     * @param json
     */
    private static void dom4j2Json(Element element, JSONObject json) {
        // Step 1: 如果有属性 形如：<XXX attr="xxx">张三</XXX>
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());        // key前加@存入参数json 形如：{"@attr":"value"}，可自定义存入格式
            }
        }

        // 获取element的子一级元素
        List<Element> childElements = element.elements();

        // Step 2: 形如：<name>张三</name>
        if (childElements.isEmpty() && !isEmpty(element.getText())) {   // 如果没有子元素，只有一个值
            json.put(element.getName(), element.getText());
        }

        // Step 3: 子元素 e是element的子一级元素
        for (Element e : childElements) {                               // element有子元素
            if (e.elements().isEmpty()) {                               // 子元素e没有子元素（子子元素）
                // attr
                for (Object o : e.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!isEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                // <name>张三</name>
                if (!isEmpty(e.getText())) {
                    json.put(e.getName(), e.getText());
                }

            } else {                                                // 子元素也有子元素
                JSONObject childJson = new JSONObject();
                dom4j2Json(e, childJson);                           // 递归，将e中的数据存入childJson

                Object o = json.get(e.getName());                   // 通过这个子元素e的key从参数json中获取内容

                if (o == null) {    // 说明json中没有存入过该类型的子元素，则当前的childJson作为第一个元素存入参数json
                    if (!childJson.isEmpty()) {
                        json.put(e.getName(), childJson);
                    }
                } else {            // json中已经存入过该类型的子元素，说明不止一个，需要转成数组形式存入参数json
                    JSONArray jsonArr = null;
                    if (o instanceof JSONObject) {                  // 当前o是之前存入的子元素，即"第一个子元素"
                        JSONObject jsonObj = (JSONObject) o;
                        json.remove(e.getName());                   // 删除之前的JSONObject（稍候以JSONArray形式存入）
                        jsonArr = new JSONArray();                  // 新建JSONArray
                        jsonArr.add(jsonObj);                       // 添加"第一个子元素"
                        jsonArr.add(childJson);                     // 添加当前子元素

                    } else if (o instanceof JSONArray) {            // 如果已经存在JSONArray，则直接在该JSONArray中添加
                        jsonArr = (JSONArray) o;
                        jsonArr.add(childJson);                     // 添加当前子元素
                    }

                    json.put(e.getName(), jsonArr);                 // {e:[{child1},{child2},{child3}]}
                }
            }
        }
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }

}