package p20160321;

/**
 * Created by wanghc on 2016/3/21.
 */
public class SplitTest {
    public static void main(String[] args) {

        String urls = "10.11.10.12;";
        String[] ss = urls.split(";");
        for (String s : ss) {
            System.out.println(s);
        }
    }
}
