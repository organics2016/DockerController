package p20160318.service;

/**
 * Created by wanghc on 2016/3/18.
 */
public class Service {
    public static void main(String[] args) {
        int status = 0;
        // 通信器
        Ice.Communicator ic = Ice.Util.initialize(args);
        try {

            // 对象适配器
            Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10111");
            // 对象
            Ice.Object object = new Printerl();
            adapter.add(object, ic.stringToIdentity("SimplePrinter"));
            adapter.activate();
            new Thread(() -> ic.waitForShutdown()).start();

            System.out.println("xxxxxxxxxxxxxxxxxx");
            Thread.sleep(Long.MAX_VALUE);
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
