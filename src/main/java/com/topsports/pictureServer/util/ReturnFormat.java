package com.topsports.pictureServer.util;

/**
 * Created by huang.cj on 2017/8/30.
 */
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;

//格式化返回客户端数据格式（json）
public class ReturnFormat {
    private static Map<String,String>messageMap = new HashedMap();
    //初始化状态码与文字说明
    static {
        messageMap.put("0", "成功");

        messageMap.put("400", "Bad Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method Not Allowed");
        messageMap.put("406", "Not Acceptable");
        messageMap.put("500", "Internal Server Error");

        messageMap.put("1", "[服务器]运行时异常");
        messageMap.put("2", "[服务器]空值异常");
        messageMap.put("3", "[服务器]数据类型转换异常");
        messageMap.put("4", "[服务器]IO异常");
        messageMap.put("5", "[服务器]未知方法异常");
        messageMap.put("6", "[服务器]数组越界异常");
        messageMap.put("7", "[服务器]网络异常");

        messageMap.put("1001", "缺少参数或值为空");
        messageMap.put("1002", "未找到对应图片地址");
        messageMap.put("1003", "删除图片对象不存在");
        messageMap.put("1004", "图片的sourceSystem对应url地址未找到");

    }
    public static String retParam(int errorCode,Object data) {
        OutputJson json = new OutputJson(errorCode, messageMap.get(String.valueOf(errorCode)), data);
        return json.toString();
    }
}
