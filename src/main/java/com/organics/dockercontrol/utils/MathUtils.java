package com.organics.dockercontrol.utils;

import java.math.BigDecimal;

/**
 * Created by wanghc on 2016/3/14.
 */
public class MathUtils {

    /**
     * 向上舍入的除法运算 并计算百分比 (dividend/divisor)*100
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public static BigDecimal divide(final long dividend, final long divisor) {
        BigDecimal bigDividend = BigDecimal.valueOf(dividend);
        BigDecimal bigDivisor = BigDecimal.valueOf(divisor);
        BigDecimal bigResult = bigDividend.divide(bigDivisor, 6, BigDecimal.ROUND_HALF_UP);
        return bigResult.multiply(BigDecimal.valueOf(1000L)).setScale(3);
    }

    public static BigDecimal conversions(final long bytes) {
        BigDecimal f = BigDecimal.valueOf(1024L).pow(2);
        return BigDecimal.valueOf(bytes).divide(f, 2, BigDecimal.ROUND_HALF_UP);
    }
}
