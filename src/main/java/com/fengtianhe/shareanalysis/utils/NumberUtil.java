package com.fengtianhe.shareanalysis.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
    public static int roundToInt(double num){
        return new BigDecimal(num).setScale(0, RoundingMode.HALF_UP).intValue();
    }
}
