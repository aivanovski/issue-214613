package com.test.amissue.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TestAuthenticatorService extends Service {

	private TestAuthenticator authenticator;

	@Override
	public void onCreate() {
		super.onCreate();
		authenticator = new TestAuthenticator(getApplicationContext());
	}

	@Override
	public IBinder onBind(Intent intent) {
		return authenticator.getIBinder();
	}
}
