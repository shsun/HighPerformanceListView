package org.lee.android;

import java.util.ArrayList;
import java.util.List;

import cn.wangmeng.test.ImageAndText;
import cn.wangmeng.test.ImageAndTextListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListView extends Activity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = new ListView(this);
        //listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        
        List<ImageAndText> imageAndTexts = new ArrayList<ImageAndText>();
        
        imageAndTexts.add(new ImageAndText("http://img1.cache.netease.com/catchpic/1/1F/1FF0314B6437E201FE5FBBBD171D1742.jpg", "1"));
        imageAndTexts.add(new ImageAndText("http://www.people.com.cn/mediafile/pic/20150312/37/14824948350635245573.jpg", "2"));        
        imageAndTexts.add(new ImageAndText("http://news.a8-img.com/content/20060725115007735.jpg", "2"));
        imageAndTexts.add(new ImageAndText("http://img1.cache.netease.com/catchpic/8/82/82F6D639ABCCAC33C8CEA37CEDCECA5A.jpg", "4"));
        
        imageAndTexts.add(new ImageAndText("http://a2.att.hudong.com/53/73/300001062059132909736811971_950.jpg", "5"));
        
        imageAndTexts.add(new ImageAndText("http://desk.fd.zol-img.com.cn/t_s1920x1200c5/g5/M00/01/0E/ChMkJlbKwdmIVkqzAARBJ9yEZSEAALGewLJWf0ABEE_004.jpg", "60"));
        
        imageAndTexts.add(new ImageAndText("http://img.2258.com/d/file/yule/mingxing/bagua/2014-12-14/80682d925aed903c56aa7cce1c0588f3.jpeg", "6"));
        imageAndTexts.add(new ImageAndText("http://img.2258.com/d/file/yule/mingxing/bagua/2014-12-14/80682d925aed903c56aa7cce1c0588f3.jpeg", "7"));
        imageAndTexts.add(new ImageAndText("http://img6.cache.netease.com/photo/0003/2015-02-18/AIP1HVDO608E0003.jpg", "8"));
        imageAndTexts.add(new ImageAndText("http://img3.cache.netease.com/photo/0003/2015-02-18/900x600_AIP1I12G608E0003.jpg", "9"));
        imageAndTexts.add(new ImageAndText("http://img3.cache.netease.com/photo/0003/2015-02-18/900x600_AIP1HH7F608E0003.jpg", "10"));
        
        
        
        
        listView.setAdapter(new ImageAndTextListAdapter(this, imageAndTexts, listView));
        
        setContentView(listView);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        return data;
    }
}
