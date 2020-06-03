package Algorithm.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {
    public static void main(String[] args) {
        //创建广播电台
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();
        //创建每个电台的范围
        HashSet<String> cityList1 = new HashSet<>();
        cityList1.add("北京");
        cityList1.add("上海");
        cityList1.add("天津");
        HashSet<String> cityList2 = new HashSet<>();
        cityList2.add("广州");
        cityList2.add("北京");
        cityList2.add("深圳");
        HashSet<String> cityList3 = new HashSet<>();
        cityList3.add("成都");
        cityList3.add("上海");
        cityList3.add("杭州");
        HashSet<String> cityList4 = new HashSet<>();
        cityList4.add("上海");
        cityList4.add("天津");
        HashSet<String> cityList5 = new HashSet<>();
        cityList5.add("杭州");
        cityList5.add("大连");

        //加入到map
        broadCasts.put("K1",cityList1);
        broadCasts.put("K2",cityList2);
        broadCasts.put("K3",cityList3);
        broadCasts.put("K4",cityList4);
        broadCasts.put("K5",cityList5);

        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList存放选择的电台
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey不为null , 则会加入到 selects
        String maxKey = null;
        while (allAreas.size() != 0){//如果allAreas不为 0, 则表示还没有覆盖到所有的地区
            //每进行一次 while,需要
            maxKey = null;
            //遍历 broadcasts, 取出对应 key
            for (String key : broadCasts.keySet()){
                //每进行一次for,置空交集
                tempSet.clear();
                //当前这个 key 能够覆盖的地区
                HashSet<String> areas = broadCasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas 集合的交集, 交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比 maxKey 指向的集合地区还多,就需要重置 maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadCasts.get(maxKey).size())){
                    maxKey = key;
                }
            }

            //如果maxKey != null, 就应该将 maxKey 加入 selects
            if (maxKey != null){
                selects.add(maxKey);
                //将 maxKey 指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
