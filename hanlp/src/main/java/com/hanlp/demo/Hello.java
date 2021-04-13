package com.hanlp.demo;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.DynamicCustomDictionary;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Hello {
    public static void main(String[] args) throws IOException {
        // 动态增加
        CustomDictionary.add("攻 城狮", "x11");
        // 强行插入
        CustomDictionary.insert("白富美", "x21");
        // 删除词语（注释掉试试）
//        CustomDictionary.remove("攻城狮");
        System.out.println(CustomDictionary.add("单身狗", "x31"));
        System.out.println("contains " + CustomDictionary.get("丫巴儿"));

        String text = "攻 城狮逆袭单身狗，迎娶白富美，走上人生巅峰,攻城狮";  // 怎么可能噗哈哈！
        // DoubleArrayTrie分词
        final char[] charArray = text.toCharArray();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value) {
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < value.nature.length; ++i)
                {
                    sb.append(value.nature[i]);
                }
                System.out.printf("[%d:%d]=str=%s value=%s\n", begin, end, new String(charArray, begin, end - begin), value);
            }
        });
        // 首字哈希之后二分的trie树分词
        BaseSearcher searcher = CustomDictionary.getSearcher(text);
        Map.Entry entry;
        while ((entry = searcher.next()) != null)
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // 标准分词
//        System.out.println(HanLP.segment(text));

        // Note:动态增删不会影响词典文件
        // 目前CustomDictionary使用DAT储存词典文件中的词语，用BinTrie储存动态加入的词语，前者性能高，后者性能低
        // 之所以保留动态增删功能，一方面是历史遗留特性，另一方面是调试用；未来可能会去掉动态增删特性。

        // 系统默认的词典
        DynamicCustomDictionary dictionary = CustomDictionary.DEFAULT;
        // 每个分词器都有一份词典，默认公用 CustomDictionary.DEFAULT，你可以为任何分词器指定一份不同的词典
        DynamicCustomDictionary myDictionary = new DynamicCustomDictionary("data/dictionary/custom/CustomDictionary.txt", "data/dictionary/custom/机构名词典.txt");
        StandardTokenizer.SEGMENT.enableCustomDictionary(myDictionary);
        StandardTokenizer.SEGMENT.customDictionary.insert("插入到该分词器专用的词典中");
    }
}
