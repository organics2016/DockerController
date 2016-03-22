package p20160318.service;

import p20160318.ice.PrinterPrx;
import p20160318.ice.PrinterPrxHelper;

/**
 * Created by wanghc on 2016/3/18.
 */
public class Client {

    public static void main(String[] args) {
        int status = 0;
        Ice.Communicator ic = null;
        try {
            // 通信器
            ic = Ice.Util.initialize(args);

            Ice.ObjectPrx base = ic.stringToProxy("SimplePrinter:default -p 10111");
            PrinterPrx printerPrx = PrinterPrxHelper.checkedCast(base);
            if (printerPrx == null)
                throw new Error("Invalid proxy");

            printerPrx.printString("hello world");
        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }
        if (ic != null) {

            try {
                ic.destroy();
            } catch (Exception e) {
                e.printStackTrace();
                status = 1;
            }
        }
        System.exit(status);
    }
}
