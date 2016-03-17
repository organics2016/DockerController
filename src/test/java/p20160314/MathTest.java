package p20160314;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by wanghc on 2016/3/14.
 */
public class MathTest {
    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(7244054446L);
        BigDecimal b = BigDecimal.valueOf(32400796150000000L);
        BigDecimal c = BigDecimal.valueOf(100L);

        BigDecimal d = a.divide(b, 6, BigDecimal.ROUND_HALF_UP);
        System.out.println(d.multiply(c, new MathContext(2, RoundingMode.HALF_UP)));

//        long a = 7244054446L;
//        long b = 32400796150000000L;
//
//        DecimalFormat df = new DecimalFormat("#.00");
//
//        System.out.println(df.format(a/b));

    }
}
