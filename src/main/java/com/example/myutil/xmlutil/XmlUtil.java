package com.example.myutil.xmlutil;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@Deprecated
public class XmlUtil {

    public static void main(String[] args) throws DocumentException {
        // 使用map 来充当IOC容器
        Map<String, String> map = new HashMap<String, String>();
        String xmlString = "<Request>\n" +
                "    <Header>\n" +
                "        <MessageID>161439</MessageID>\n" +
                "        <SourceSystem>02</SourceSystem>\n" +
                "    </Header>\n" +
                "    <Body>\n" +
                "        <MedExamSummaryRt>\n" +
                "            <PATPatientID>101100000028</PATPatientID>\n" +
                "            <PAADMVisitNumber>64</PAADMVisitNumber>\n" +
                "<MedExamDate>2016-10-20</MedExamDate>\n" +
                "\n" +
                "            <ExaminationSummary>\n" +
                "                <MedExamSummary>1、[前列腺超声：前列腺钙化]前列腺钙化可能是前列腺炎症愈合留下的瘢痕，可能是前列腺结石的前兆。有尿频，尿急，排尿不畅等症状时，建议您去泌尿外科门诊复查或进一步检查。2、[Ⅱ度扁桃体肥大]如反复发炎或影响吞咽和呼吸(如打鼾)，建议耳鼻喉门诊进一步诊治。3、[鼻中隔稍右偏]可引起鼻腔功能障碍及鼻塞、鼻出血及头痛等。如有不适，建议耳鼻喉科进一步诊治。4、双侧下鼻甲稍大由于长期受炎症刺激引起鼻甲粘膜水肿，导致鼻腔阻塞，建议加强锻炼、预防感冒，如鼻塞症状明显耳鼻喉门诊进一步诊治。5、牙垢是由食物残渣、口腔粘膜脱落的细胞、唾液中的黏液及细菌等混合堆积而成，较易通过刷牙等方法清除，应掌握正确的刷牙方法，保持口腔卫生。6、[心电图：窦性心动过缓（53bpm）]可见于健康的年轻人、运动员、老年人以及某些疾病或服用某些药物的人。无症状者不需治疗，如有显著持久的窦性心动过缓伴胸闷憋气等症状，建议到心血管内科进一步诊治。7、腹部超声：左侧肾区未能探及肾脏回声(考虑左肾缺如)您既往体检查出有孤立肾，建议您结合既往情况，定期复查，泌尿外科随诊。</MedExamSummary>\n" +
                "                <MedExamSummaryDoctor>项**</MedExamSummaryDoctor>\n" +
                "                <MedExamSummaryDate>2020-01-07</MedExamSummaryDate>\n" +
                "                <MedExamAuditDoctor>贾玲</MedExamAuditDoctor>\n" +
                "                <MedExamAuditDate>2020-01-07</MedExamAuditDate>\n" +
                "            </ExaminationSummary>\n" +
                "        </MedExamSummaryRt>\n" +
                "    </Body>\n" +
                "</Request>";
        //将给定的String文本解析为XML文档并返回新创建的document
        Document document = DocumentHelper.parseText(xmlString);
        try {
            new XmlUtil().parseXml2Map(xmlString,map);
            System.out.println(JSON.toJSONString(map));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Map<String, String> parseXml2Map(String xml, Map<String, String> map) {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new StringReader(xml));
            Element root = doc.getRootElement();
            String path = "";
            if (map.containsKey(root.getName().trim())) {
                path = map.get(root.getName().trim());
                map.remove(root.getName().trim());
            }
            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Element element = (Element) i.next();
                if (element.isTextOnly()) {
                    if (path.length() > 0) {
                        map.put(path + element.getName().trim(), element.getTextTrim());
                    } else {
                        map.put(element.getName().trim(), element.getTextTrim());
                    }
                } else {
                    map.put(element.getName().trim(), path+ element.getName().trim() + ".");
                    parseXml2Map(element.asXML(), map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
