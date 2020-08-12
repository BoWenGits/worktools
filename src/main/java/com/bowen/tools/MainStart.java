package com.bowen.tools;

import java.util.Scanner;

/**
 * @Author BoWen
 * @Date 2020/8/12 17:38
 * @Version 1.0
 */
public class MainStart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WorkTools workTools = null;
        System.out.println("请选择需要使用的功能:");
        System.out.println(">>>1:redis数据库导出Excel!");
        System.out.println(">>>2:kafka导出Excel!");
        System.out.print("请选择:");
        int temp = scanner.nextInt();
        switch (temp){
            case 1:
                workTools = new RedisToExcelTools();
                break;
            case 2:
                workTools = new KafkaToExcelTools();
                break;
            default:
                workTools = new DefaultTools();
        }
        workTools.start();
    }
}
