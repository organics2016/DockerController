package p20160318.service;

import Ice.Current;
import p20160318.ice._PrinterDisp;

/**
 * Created by wanghc on 2016/3/18.
 */
public class Printerl extends _PrinterDisp {
    @Override
    public void printString(String s, Current __current) {
        System.out.println(s + " sh!");
    }
}
