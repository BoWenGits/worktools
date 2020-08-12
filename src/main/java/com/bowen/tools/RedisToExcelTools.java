package com.bowen.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

/**
 * java导出到excel
 * @author jintao
 *
 */
public class RedisToExcelTools extends WorkTools {
	
	public void start(){
		//Jedis jedis =new Jedis("127.0.0.1",6379);
		Scanner scanner = new Scanner(System.in);
		System.out.print(">>> 输入需要导出库的IP：");
		Jedis jedis =new Jedis(scanner.nextLine(),6379);
		System.out.print(">>> 当前库是否设置密码？（有则输入，没有则留空）：");
		String password = scanner.nextLine().trim();
		if(password.length()>0) {
			jedis.auth(password);
		}
		
		System.out.print(">>> 输入需要导出的库[请输入:0/1/2/3 ...]：");
		int dbport = Integer.parseInt(scanner.nextLine());
		jedis.select(dbport);
		
		try {
			readallRediskeys(jedis,dbport);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(">>> 执行失败");
		}
		
		System.out.println(">>> 执行完成");
	}
	
	
	public static void readallRediskeys(Jedis jedis,int dbport) throws IOException {
        Set<?> s = jedis.keys("*");
        Iterator<?> it = s.iterator();
        String FILEPATH = "./";
        FileWriter writer = new FileWriter(FILEPATH + "redis导出_数据库_"+dbport+".txt", true);
        while(it.hasNext()) {
            String key = (String) it.next();
            String value = jedis.get(key);
            System.out.println(key + ": " + value);
            writer.write(key + "," + value);
            writer.write(System.getProperty("line.separator"));
        }
        writer.close();
    }
}
