package com.example.myutil.xmlutil;


import com.alibaba.fastjson.JSON;
import lombok.Data;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class JsonLibXml {

    public static void main(String[] args) {
        String xmlString = "<Request>\n" +
                "    <Header>\n" +
                "        <SourceSystem>02</SourceSystem>\n" +
                "        <MessageID>202979</MessageID>\n" +
                "    </Header>\n" +
                "    <Body>\n" +
                "        <PatientRegistryRt>\n" +
                "            <BusinessFieldCode>00001</BusinessFieldCode>\n" +
                "            <HospitalCode>HFJDFYY</HospitalCode>\n" +
                "            <PATPatientID>02000026</PATPatientID>\n" +
                "            <PATPatientName>李*</PATPatientName>\n" +
                "            <PATDob>2000-12-27</PATDob>\n" +
                "            <PATAge>20岁</PATAge>\n" +
                "            <PATSexCode>1</PATSexCode>\n" +
                "            <PATSexDesc>男</PATSexDesc>\n" +
                "            <PATMaritalStatusCode></PATMaritalStatusCode>\n" +
                "            <PATMaritalStatusDesc></PATMaritalStatusDesc>\n" +
                "            <PATDocumentNo></PATDocumentNo>\n" +
                "            <PATNationCode></PATNationCode>\n" +
                "            <PATNationDesc></PATNationDesc>\n" +
                "            <PATCountryCode>156</PATCountryCode>\n" +
                "            <PATCountryDesc>中国</PATCountryDesc>\n" +
                "            <PATDeceasedDate></PATDeceasedDate>\n" +
                "            <PATDeceasedTime></PATDeceasedTime>\n" +
                "            <PATHealthCardID></PATHealthCardID>\n" +
                "            <PATMotherID></PATMotherID>\n" +
                "            <PATOccupationCode></PATOccupationCode>\n" +
                "            <PATOccupationDesc></PATOccupationDesc>\n" +
                "            <PATWorkPlaceName></PATWorkPlaceName>\n" +
                "            <PATWorkPlaceTelNum></PATWorkPlaceTelNum>\n" +
                "            <PATAddressList>\n" +
                "                <PATAddress>\n" +
                "                    <PATAddressType>06</PATAddressType>\n" +
                "                    <PATAddressDesc></PATAddressDesc>\n" +
                "                    <PATHouseNum></PATHouseNum>\n" +
                "                    <PATVillage></PATVillage>\n" +
                "                    <PATCountryside></PATCountryside>\n" +
                "                    <PATCountyCode></PATCountyCode>\n" +
                "                    <PATCountyDesc></PATCountyDesc>\n" +
                "                    <PATCityCode></PATCityCode>\n" +
                "                    <PATCityDesc></PATCityDesc>\n" +
                "                    <PATProvinceCode>340000</PATProvinceCode>\n" +
                "                    <PATProvinceDesc>安徽省</PATProvinceDesc>\n" +
                "                    <PATPostalCode></PATPostalCode>\n" +
                "                </PATAddress>\n" +
                "            </PATAddressList>\n" +
                "            <PATIdentityList>\n" +
                "                <PATIdentity>\n" +
                "                    <PATIdentityNum>34250120001227****</PATIdentityNum>\n" +
                "                    <PATIdTypeCode>01</PATIdTypeCode>\n" +
                "                    <PATIdTypeDesc>居民身份证</PATIdTypeDesc>\n" +
                "                    <PAPERPhoto></PAPERPhoto>\n" +
                "                </PATIdentity>\n" +
                "            </PATIdentityList>\n" +
                "            <PATRelationList>\n" +
                "                <PATRelation>\n" +
                "                    <PATRelationCode></PATRelationCode>\n" +
                "                    <PATRelationDesc></PATRelationDesc>\n" +
                "                    <PATRelationName></PATRelationName>\n" +
                "                    <PATRelationPhone></PATRelationPhone>\n" +
                "                    <PATRelationAddressList>\n" +
                "                        <PATRelationAddress>\n" +
                "                            <PATRelationAddressDesc></PATRelationAddressDesc>\n" +
                "                            <PATRelationHouseNum></PATRelationHouseNum>\n" +
                "                            <PATRelationVillage></PATRelationVillage>\n" +
                "                            <PATRelationCountryside></PATRelationCountryside>\n" +
                "                            <PATRelationCountyCode></PATRelationCountyCode>\n" +
                "                            <PATRelationCountyDesc></PATRelationCountyDesc>\n" +
                "                            <PATRelationCityCode></PATRelationCityCode>\n" +
                "                            <PATRelationCityDesc></PATRelationCityDesc>\n" +
                "                            <PATRelationProvinceCode></PATRelationProvinceCode>\n" +
                "                            <PATRelationProvinceDesc></PATRelationProvinceDesc>\n" +
                "                            <PATRelationPostalCode></PATRelationPostalCode>\n" +
                "                        </PATRelationAddress>\n" +
                "                    </PATRelationAddressList>\n" +
                "                </PATRelation>\n" +
                "            </PATRelationList>\n" +
                "            <PATTelephone>1363721****</PATTelephone>\n" +
                "            <PATRemarks></PATRemarks>\n" +
                "            <UpdateUserCode>V0045</UpdateUserCode>\n" +
                "            <UpdateUserDesc>医院APP</UpdateUserDesc>\n" +
                "            <UpdateDate>2021-07-20</UpdateDate>\n" +
                "            <UpdateTime>23:13:54</UpdateTime>\n" +
                "            <PATDocumentNo></PATDocumentNo>\n" +
                "            <PATType>1</PATType>\n" +
                "        </PatientRegistryRt>\n" +
                "    </Body>\n" +
                "</Request>";
        /* 第二种方法，使用json-lib提供的方法 */
        //创建 XMLSerializer对象
        String result = XmlParseUtil.parseXMlToString(xmlString);
        JSONObject headerAndBody = XmlParseUtil.getHeaderAndBody(result);
        //输出json内容
        System.out.println(result);
    }

    @Data
    class Business{
        String BusinessFieldCode;
        String HospitalCode;
    }

}
