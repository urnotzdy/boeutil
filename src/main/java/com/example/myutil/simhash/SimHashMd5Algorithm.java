package com.example.myutil.simhash;

import com.example.myutil.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.util.List;

@Slf4j
public class SimHashMd5Algorithm {

    private static int hashbits = 64;

    public static String simHash(List<SimHashWord> list) {
        int[] v = new int[hashbits];

        for (SimHashWord word :list){
            String temp = word.getDimKey()+"_"+word.getText();
            //进行编码：简单的hash值（hash）
            BigInteger t = hash(temp);
            for (int i = 0; i < hashbits; i++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(i);
                //相对应位置如果hash值为1，则权重取正，hash值为0，则权重取负；
                //列向累加(加权合并)
                if (t.and(bitmask).signum() != 0) {
                    v[i] += word.getWeight();
                } else {
                    v[i] -= word.getWeight();
                }
            }
        }
        //对上述累加后的结果进行变换，对应位置为正数时取1，对应位置为负数时取0.（降维）
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < hashbits; i++) {
            if (v[i] > 0) {
                simHashBuffer.append("1");
            }else{
                simHashBuffer.append("0");
            }
        }
        //该文档的hash值
        return simHashBuffer.toString();
    }

    public static BigInteger hash(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return new BigInteger(1,md.digest(source.getBytes("UTF-8")));
        } catch (Exception e) {
            log.error("md5 hash error,source="+source,e);
        }
        return new BigInteger("0");
    }

    /**
     * 生成分片hash
     */
    public static SimHashSegment[] genSimHashSeg(String prefix, List<SimHashWord> dimensions, int segNumber) {

        String simHash = simHash(dimensions);
        SimHashSegment[] segments = new SimHashSegment[segNumber];
        if (segNumber!=4 && segNumber!=8){
            throw new InvalidParameterException("simhash segment must is 3 or 7");
        }
        int segLength = 64/segNumber;
        for (int i = 0; i < segNumber; i++) {
            String seg = simHash.substring(i * segLength, (i + 1) * segLength) + ":" + i;
            segments[i] = new SimHashSegment(seg, simHash, dimensions);
            String digest = CommonUtil.md5(prefix + "_" + seg);
            segments[i].setDigest(digest);
        }

        return segments;
    }

    /**
     * calculate Hamming Distance between two strings
     *  二进制怕有错，当成字符串，作一个，比较下结果
     * @author
     * @param str1 the 1st string
     * @param str2 the 2nd string
     * @return Hamming Distance between str1 and str2
     */
    public static int getDistance(String str1, String str2) {
        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }
}