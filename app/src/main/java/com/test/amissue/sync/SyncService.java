package com.test.amissue.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncService extends Service {

	private SyncAdapter syncAdapter;

	@Override
	public void onCreate() {
		super.onCreate();
		syncAdapter = new SyncAdapter(getApplicationContext());
	}

	@Override
	public IBinder onBind(Intent intent) {
		return syncAdapter.getSyncAdapterBinder();
	}
}
