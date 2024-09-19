package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.List;


///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @param n The number of the threads
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int n) {
        ArrayList<PiDigitsThread> threads = new ArrayList<>();
        if (count % n == 0){
            int newCont = count/n;
            int newStart = start;
            for(int i = 0; i < n; i++){
                PiDigitsThread piDigitsThread = new PiDigitsThread(newStart, newCont);
                threads.add(piDigitsThread);
                piDigitsThread.start();
                newStart += newCont;
            }
        } else {
            int newCont = count/n;
            int newStart = start;
            for(int i = 0; i < n - 1; i++){
                PiDigitsThread piDigitsThread = new PiDigitsThread(newStart, newCont);
                threads.add(piDigitsThread);
                piDigitsThread.start();
                newStart += newCont;
            }
            PiDigitsThread piDigitsThread = new PiDigitsThread(newStart, newCont + (count%n));
            threads.add(piDigitsThread);
            piDigitsThread.start();
        }

        for (PiDigitsThread pDT: threads){
            try {
                pDT.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        byte[] digits = new byte[count];
        int index = 0;

        for (PiDigitsThread pDT: threads){
            for(byte i: pDT.getAns()){
                digits[index] = i;
                index += 1;
            }
        }

        return digits;

    }


}
