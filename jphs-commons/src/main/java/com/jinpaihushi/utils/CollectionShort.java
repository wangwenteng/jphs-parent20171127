package com.jinpaihushi.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/** 
* 对集合进行排序
* @ClassName: Collection 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王文腾
* @date 2016年10月19日 上午10:48:15 
*/
@SuppressWarnings("unused")
public class CollectionShort {
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public static List<Map<String, Object>> testMapOrder(List<Map<String, Object>> list, final String name) {

        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                //进行判断
                return -((String) o1.get(name)).compareTo((String) o2.get(name));
            }
        });
        return list;
    }

    public static List<Map<String, Object>> testMapOrderDouble(List<Map<String, Object>> list, final String name) {

        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                //进行判断
                return -((Double) o1.get(name)).compareTo((Double) o2.get(name));
            }
        });
        return list;
    }

    public static List<String> testListOrderDate(List<String> list) {

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //进行判断
                return (o1).compareTo(o2);
            }
        });
        return list;
    }
}
