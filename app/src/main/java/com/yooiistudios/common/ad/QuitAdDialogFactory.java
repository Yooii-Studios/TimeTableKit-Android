package com.yooiistudios.common.ad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.sulga.yooiitable.R;

/**
 * Created by Wooseong Kim in News L from Yooii Studios Co., LTD. on 2014. 10. 16.
 *
 * AdDialogFactory
 *  종료시 애드뷰를 띄워주는 팩토리 클래스
 */
public class QuitAdDialogFactory {
    private QuitAdDialogFactory() { throw new AssertionError("Must not create this class!"); }

    public static final String AD_UNIT_ID = "ca-app-pub-2310680050309555/7539796228";

    public static AdView initAdView(Context context, AdSize adSize,
                                    final com.google.android.gms.ads.AdRequest adRequest) {
        // make AdView again for next quit dialog
        // prevent child reference
        AdView adView = new AdView(context);
        adView.setAdSize(adSize);
        adView.setAdUnitId(QuitAdDialogFactory.AD_UNIT_ID);
        adView.loadAd(adRequest);
        return adView;
    }

    public static AlertDialog makeDialog(final Activity activity, final AdView adView) {
        Context context = activity.getApplicationContext();
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.quit_ad_dialog_title_text);
        builder.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                activity.finish();
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel), null);

        final AlertDialog wakeDialog = builder.create();
        // 세로 전용 앱이라서 동현 파트를 제거할 경우 아래 주석을 해제할 것
        wakeDialog.setView(adView); // Android L 에서 윗 공간이 좀 이상하긴 하지만 기본으로 가야할듯

        // 기타 필요한 설정
        wakeDialog.setCanceledOnTouchOutside(false);

        return wakeDialog;
    }
}
