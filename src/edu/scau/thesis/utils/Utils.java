package edu.scau.thesis.utils;


import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * 
 * @author pazezhe
 * 工具类集合
 */
public class Utils {
    //创建UUID，并去掉分隔符"-"
	public static String newUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
	//返回当前时间的Timestamp格式
    public static Timestamp getNow(){
        return new Timestamp(new Date().getTime());
    }

}
