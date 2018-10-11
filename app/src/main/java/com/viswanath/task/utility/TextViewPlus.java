package com.viswanath.task.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by VPS on 13-09-2017.
 */

public class TextViewPlus extends android.support.v7.widget.AppCompatTextView {
    private static OpenSans instance;
    private static Typeface typeface;

    public TextViewPlus(Context context) {
        super(context);
        setTypeface(OpenSans.getInstance(context).getTypeFace());
    }

    public TextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(OpenSans.getInstance(context).getTypeFace());
    }

    public TextViewPlus(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(OpenSans.getInstance(context).getTypeFace());
    }

    private static class OpenSans {
        public static OpenSans getInstance(Context context) {
            synchronized (OpenSans.class) {
                if (instance == null) {
                    instance = new OpenSans();
                    typeface = Typeface.createFromAsset(context.getResources().getAssets(), "Montserrat-Bold.ttf");
                }
                return instance;
            }
        }

        public Typeface getTypeFace() {
            return typeface;
        }
    }
}
