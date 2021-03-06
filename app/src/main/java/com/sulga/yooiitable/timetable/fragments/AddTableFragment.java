package com.sulga.yooiitable.timetable.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sulga.yooiitable.R;
import com.sulga.yooiitable.constants.RequestCodes;
import com.sulga.yooiitable.data.TimetableDataManager;
import com.sulga.yooiitable.overlapviewer.OverlapTablesViewerActivity;
import com.sulga.yooiitable.timetable.TimetableActivity;
import com.yooiistudios.common.ad.AdUtils;

import java.util.ArrayList;


public class AddTableFragment extends Fragment {
	public static AddTableFragment newInstance() {
		AddTableFragment addTableFragment = new AddTableFragment();

		return addTableFragment;
	}


	private View addTableView;
	private ImageButton addTableButton;
//	private ImageButton showAllButton;
	private ImageButton overlapButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		addTableView = inflater.inflate(R.layout.fragment_to_addtable, container, false);
		addTableButton = (ImageButton) addTableView.findViewById(R.id.fragment_to_addtable_button_add);
//		showAllButton = (ImageButton) addTableView.findViewById(R.id.fragment_to_addtable_button_showall);
		overlapButton = (ImageButton) addTableView.findViewById(R.id.fragment_to_addtable_button_overlap);
		
		addTableButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if( ( TimetableDataManager.getTimetables().size() == 4 ) &&
						(!TimetableDataManager.getCurrentFullVersionState(getActivity())) ){
//					ToastMaker.popupUnlockFullVersionToast(getSupportApplication(),
//							ToastMaker.UNLOCK_FULL_VERSION_TOAST_OVERFLOW_PAGENUM);
                    AdUtils.showInHouseStoreAd(getActivity());
					return;
				}
				TimetableActivity ta = (TimetableActivity) AddTableFragment.this.getActivity();

				//ta.addPage(TimetableFragment.newInstance(new Timetable(5,10,60)), 1);

				//				TimetableDataManager.getInstance().addTimetableAtHead(
				//						new Timetable(Timetable.MON, Timetable.FRI, Timetable.DEFAULT_PERIOD_NUM)
				//						);
				ta.addPageAt(1, null, true);

				TimetableDataManager.writeDatasToExternalStorage();
				//				Bundle b = new Bundle();
				//				b.putParcelable("Timetable", new Timetable(6,10,60));
				//				//ta.getPagerAdapter().add(TimetableFragment.class, b, 1);
				//				
				//				ta.getViewPager().setCurrentItem(1);
				//				ta.getViewPager().invalidate();

			}
		});

//		showAllButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(
//						AddTableFragment.this.getActivity(), ShowAllTimetablesActivity.class);
//				//TimetableActivity ta = (TimetableActivity) getActivity();
//
////				ArrayList<Fragment> fragments = new ArrayList<Fragment>();
////				for(int i = 1; i < ta.getPagerAdapter().getCount() - 1 ; i++){
////					fragments.add(ta.getPagerAdapter().getItem(i));
////				}
////				
////				//TimetablePreviewBitmapHolder.initiate();
////				for(int i = 0; i < fragments.size() ; i++){
////					fragments.get(i).getView().buildDrawingCache();
////					//fragmentImageCaches.add(fragments.get(i).getView().getDrawingCache());
////					TimetablePreviewBitmapHolder.addBitmap(
////							fragments.get(i).getView().getDrawingCache()
////							);
////				}
//				
//				//intent.putExtra("TimetableImages", fragmentImageCaches);
//				
//				getActivity().startActivityForResult(
//						intent, RequestCodes.CALL_ACTIVITY_SHOW_ALL);
//			}
//		});
		
		overlapButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						AddTableFragment.this.getActivity(), OverlapTablesViewerActivity.class);
				
				ArrayList<Integer> checkedItems = new ArrayList<>();
				for(int i = 0; i < TimetableDataManager.getTimetables().size() ; i++){
					checkedItems.add(i);
				}
				intent.putExtra("OverlapIndex", checkedItems);
				intent.putExtra("DirectOverlap", true);
			
				getActivity().startActivityForResult(
						intent, RequestCodes.CALL_ACTIVITY_OVERLAP);

			}
		});
		return addTableView;
	}
}
