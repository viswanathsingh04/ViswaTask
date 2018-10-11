package com.viswanath.task.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.viswanath.task.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Locale;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class GlobalFragment extends Fragment {

    private static final int TAG = 1;
    public Animation animation_slide_in_right;
    public Typeface Light, Regular, Robo_light, slabo, grb, grbk, grbl;
    public ConnectionDetector connectionDetector;
    public boolean isInternetAvailable, isNetAvailable;
    public ProgressDialog progressDialog;
    public int currentApiVersion;
    Locale locale = Locale.getDefault();
    public int PHONE_PERMISSION_CODE = 03;
    ProgressBar mProgressBar;
    SharedPreferences sharedPreferences;
    private String STORE_FILE_NAME = "list";
    private static SharedPreferences.Editor editor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
        connectionDetector = new ConnectionDetector(getActivity().getApplicationContext());
        animation_slide_in_right = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in_left);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            grbk = ResourcesCompat.getFont(getActivity(), R.font.gothamroundedbook);
            grbl = ResourcesCompat.getFont(getActivity(), R.font.gothamroundedlight);
        } else {
            grbk = Typeface.createFromAsset(getActivity().getAssets(), "gothamroundedbook.ttf");
            grbl = Typeface.createFromAsset(getActivity().getAssets(), "gothamroundedlight.ttf");
        }
        isInternetAvailable = connectionDetector.isConnectingToInternet();
        isNetAvailable = connectionDetector.isNetworkAvailable();
    }

    public double RoundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void hideKeyBoard(Context context) {
        if (getActivity().getCurrentFocus() != null) {
            if (getActivity().getCurrentFocus().getWindowToken() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                assert inputMethodManager != null;
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public void showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        //progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public ConnectionDetector getInternetConnection() {
        return connectionDetector;
    }

    public void window() {
        currentApiVersion = Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getActivity().getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }

    public float PixelsToDp(Context context, int pixels) {
        Resources resource = context.getResources();
        float dp = pixels / (resource.getDisplayMetrics().densityDpi / 160f);
        return dp;
    }

    public static float convertSpToPixels(float sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void FindDeviceResolution() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;
        if (density == DisplayMetrics.DENSITY_HIGH) {
            Toast.makeText(getActivity(), "DENSITY_HIGH... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_MEDIUM) {
            Toast.makeText(getActivity(), "DENSITY_MEDIUM... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_LOW) {
            Toast.makeText(getActivity(), "DENSITY_LOW... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Density is neither HIGH, MEDIUM OR LOW.  Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        }
    }

    public static int getSoftButtonsBarSizePort(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    public static void setLightStatusBar(View view, Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    public void showToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void Validate(EditText et, String message) {
        et.setError(message);
    }

    /*public List<Detail> getDataFromSharedPreferences() {
        List<Detail> productFromShared = new ArrayList<>();
        for (int i = 0; i > productFromShared.size(); i++) {
            Toast.makeText(getApplicationContext(), i, Toast.LENGTH_SHORT).show();
        }
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("list", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonPreferences = sharedPreferences.getString("list", "");
        Type type = new TypeToken<List<Detail>>() {
        }.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);
        return productFromShared;
    }

    public void empty_alert(String alert, String msg) {
        TextView alert_title, alert_msg;
        Button btn_close;
        AlertDialog.Builder bmneu = new AlertDialog.Builder(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        LayoutInflater inflate = getLayoutInflater();
        View v = inflate.inflate(R.layout.empty, null);
        alert_title = v.findViewById(R.id.alert_title);
        alert_title.setText(alert);
        alert_msg = v.findViewById(R.id.alert_msg);
        alert_msg.setText(msg);
        btn_close = (Button) v.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
               *//* Intent intent = getIntent();
                finish();
                startActivity(intent);*//*
            }
        });
        bmneu.setView(v);
        AlertDialog alertHelp = bmneu.create();
        alertHelp.show();

        ScrollView.LayoutParams params = (ScrollView.LayoutParams) v.getLayoutParams();
        params.height = getResources().getDisplayMetrics().heightPixels;
        params.width = getResources().getDisplayMetrics().widthPixels;
        v.setLayoutParams(params);
    }*/

    private void showDialogMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(TAG);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }


}