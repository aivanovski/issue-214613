package com.test.amissue;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private static final int REQUEST_CODE_PERMISSIONS = 1;

	private static final String ACCOUNT_TYPE = "com.test.amissue";
	private static final String DUMMY_PASSWORD = "1111";
	private static final String PERMISSION = Manifest.permission.READ_CONTACTS;

	private static final String TAG = MainActivity.class.getSimpleName();

	private EditText accountNameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		accountNameEditText = (EditText) findViewById(R.id.account_name_edit_text);

		findViewById(R.id.create_account_btn)
				.setOnClickListener(view -> onCreateAccountButtonClicked());

		if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[] { PERMISSION }, REQUEST_CODE_PERMISSIONS);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == REQUEST_CODE_PERMISSIONS
				&& (grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
			finish();
		}
	}

	private void onCreateAccountButtonClicked() {
		String accountName = accountNameEditText.getText().toString();

		boolean created = AccountManager.get(this).addAccountExplicitly(new Account(accountName, ACCOUNT_TYPE), DUMMY_PASSWORD, null);

		if (created) {
			Toast.makeText(this, "Successfully created", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Failed to create", Toast.LENGTH_SHORT).show();
		}
	}
}
