package com.example.myutil.service;

import com.example.myutil.simhash.SimHashSegment;
import com.example.myutil.simhash.SimHashWord;
import com.example.myutil.util.CommonUtil;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static com.example.myutil.simhash.SimHashMd5Algorithm.simHash;

//分词 或者 找到对应的字段
//去停用词后计算每个词的tf-idf权重
//对这些关键词进行编码，得出整体的hash值，因为是64位的hash值，故截取为4或者8段用于存储

//分段存储，根据subhash进行存储，对应一个list<item>，其中item的key为fullhash

//根据subhash进行分段查询
//1、如果subhash和fullhash能够精准匹配，则返回对应的信息
//2、如果不能精准匹配到
//2.1、根据subhash值进行分页批量扫描
//2.2、对获取到的list中的每个itemkey 与 当前数据的fullhash 计算相似度，算出海明距离
//2.3、将符合海明距离的数据拿到

//根据上述获取到的相似数据，通过LR逻辑回归模型，获取最相似的id

//参考链接：
//1、https://zhuanlan.zhihu.com/p/81026564
//2、https://blog.csdn.net/fkyyly/article/details/84503313
//3、https://blog.csdn.net/houxq123/article/details/79793184

//simhash如何处理短文本？换一种思路，simhash可以作为局部敏感哈希第一次计算缩小整个比较的范围，等到我们只有比较700多次比较时，
// 就算使用我们之前精准度高计算很慢的编辑距离也可以搞定。当然如果觉得慢了，也可以使用余弦夹角等效率稍微高点的相似度算法。

//余弦相似度算法适合于短文本，而SimHash算法适合于长文本，并且能应用于大数据环境中。
public class SimHashSegService {


    /**
     * 生成分片hash
     */
    public static SimHashSegment[] genSimHashSeg(String prefix, List<SimHashWord> dimensions, int segNumber) {

        String simHash = simHash(dimensions);
        SimHashSegment[] segments = new SimHashSegment[segNumber];
        if (segNumber!=4 && segNumber!=8){
            throw new InvalidParameterException("simhash segment must is 3 or 7");
        }
        //借助map的思想，分段存储，便于快速搜索
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
     * 获取海明距离（两个编码相对位置不相同的个数）
     */
    public static int getDistance(String simHash1, String simHash2) {

        int distance = 0;

        if (simHash1.length() != simHash2.length()) {
            distance = -1;
        } else {
            for (int i = 0; i < simHash1.length(); i++) {
                if (simHash1.charAt(i) != simHash2.charAt(i)) {
                    distance++;
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        List<SimHashWord> dimensions = new ArrayList<>();
//        SimHashWord simHashWord = new SimHashWord("address","在信息论中，两个字符串之间的汉明距离（英语：Hamming distance）是两个字符串对应位置的不同字符的个数。换句话说，它就是将一个字符串变换成另外一个字符串所需要替换的字符个数。汉明重量是字符串相对于同样长度的零字符串的汉明距离，也就是说，它是字符串中非零的元素个数：对于二进制字符串来说，就是1的个数，所以11101的汉明重量是4。例如：1011101与1001001之间的汉明距离是2",1);
        SimHashWord simHashWord=new SimHashWord("shi","北京市",1);
        SimHashWord simHashWord1=new SimHashWord("shi","大兴区",2);
        SimHashWord simHashWord2=new SimHashWord("shi","京东方路口北",3);
        SimHashWord simHashWord3=new SimHashWord("shi","301室",2);
        dimensions.add(simHashWord);
        dimensions.add(simHashWord1);
        dimensions.add(simHashWord2);
        dimensions.add(simHashWord3);
        String s = simHash(dimensions);
        System.out.println(s);

        List<SimHashWord> dimensions1 = new ArrayList<>();
//        SimHashWord simHashWord1 = new SimHashWord("address","在信息论中，两个等长字符串之间的汉明距离（英语：Hamming distance）是两个字符串对应位置的不同字符的个数。换句话说，它就是将一个字符串变换成另外一个字符串所需要替换的字符个数。汉明重量是字符串相对于同样长度的零字符串的汉明距离，也就是说，它是字符串中非零的元素个数：对于二进制字符串来说，就是1的个数，所以11101的汉明重量是4。例如：1011101与1001001之间的汉明距离是2",1);
        simHashWord=new SimHashWord("shi","北京市",1);
        simHashWord1=new SimHashWord("shi","大兴区",2);
        simHashWord2=new SimHashWord("shi","京东方路口北",3);
        simHashWord3=new SimHashWord("shi","301",2);
        dimensions1.add(simHashWord);
        dimensions1.add(simHashWord1);
        dimensions1.add(simHashWord2);
        dimensions1.add(simHashWord3);

        String s1 = simHash(dimensions1);
        System.out.println(s1);

        System.out.println(getDistance(s,s1));
    }



}
