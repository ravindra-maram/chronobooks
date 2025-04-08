package com.example.chronobooks.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

public class ThemeUtils {

    public static int getThemeColor(Context context, int resId) {
        return context.getResources().getColor(resId, context.getTheme());
    }

    public static int dpToPx(int dp) {
        return Math.round(dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
