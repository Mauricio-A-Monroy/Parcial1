/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.util.Arrays;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) {
        //System.out.println(bytesToHex(PiDigits.getDigits(0, 10)));
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100)));
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 1000000)));

        /*
        PiDigitsThread piDigitsThread3 = new PiDigitsThread(1, 100);
        piDigitsThread3.start();
        PiDigitsThread piDigitsThread2 = new PiDigitsThread(10, 100);
        piDigitsThread2.start();*/

        //PiDigits.getDigits(1, 100, 3);

        byte[] ans1 = PiDigits.getDigits(1, 100, 20);

        String str1 = "";
        for (byte i: ans1){
            str1 += i;
        }
        System.out.println(str1);

        PiDigitsThread piDigitsThread1 = new PiDigitsThread(1, 100);
        piDigitsThread1.start();
        try {
            piDigitsThread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        byte[] ans2 = piDigitsThread1.getAns();

        String str2 = "";
        for (byte i: ans2){
            str2 += i;
        }
        System.out.println(str2);

        //System.out.println(bytesToHex(piDigitsThread);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }

}
