package com.sulga.yooiitable.timetable.fragments.dialogbuilders;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;

import com.sulga.yooiitable.R;
import com.sulga.yooiitable.timetable.fragments.TimetableFragment;

public class StartConnectorTutorialDialogBuilder {

	public static Dialog createDialog(final Context context,
									  final TimetableFragment parentFrag){
		View dialogView = View.inflate(context, R.layout.dialog_tutorial_connector, null);

		final Dialog dialog =  new AlertDialog.Builder(context)
		.setCancelable(true)
		.setView(dialogView)
		.create();		

		ImageButton ok = (ImageButton) dialogView
				.findViewById(R.id.dialog_tutorial_connector_button_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
//				String uuid = new DeviceUuidFactory(context).getDeviceUuid().toString();
////				TimeTableNetworkTool.getUploadInfo(uuid);
//								
//				TimetableNetworkManager.updateConnectorUseInfo(uuid, context, 
//						new TimetableNetworkManager.OnFinishedConnectorAsync() {
//					
//					@Override
//					public void onFinished(boolean isSucceed) {
//						// TODO Auto-generated method stub
//						parentFrag.dismissProgressDialog();
//						ShareDataDialogBuilder.createDialog(context, 
//								parentFrag.getTimetableDataFromManager(), parentFrag)
//						.show();
//					}
//				});
				parentFrag.updateConnectorUseInfoAndShowShareDialog();
				
			}
		});

		return dialog;
	}
}
