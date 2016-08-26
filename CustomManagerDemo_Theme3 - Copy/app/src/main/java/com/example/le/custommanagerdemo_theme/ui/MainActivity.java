package com.example.le.custommanagerdemo_theme.ui;


import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.le.custommanagerdemo_theme.R;
import com.example.le.custommanagerdemo_theme.common.Final;
import com.example.le.custommanagerdemo_theme.database.DatabaseManager;
import com.example.le.custommanagerdemo_theme.model.Account;
import com.example.le.custommanagerdemo_theme.model.AccountResponse;
import com.example.le.custommanagerdemo_theme.network.AccountAPI;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "SiginWithGmail";
    private static final int PROFILE_PIC_SIZE = 150;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private ImageButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private LinearLayout llProfileLayout;
    private String email;
    private int idAccount=0;

    private Cursor cursor;//bang du lieu
    private DatabaseManager databaseManager;//lop lam viec voi DB
    private Final fina = new Final();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiDex.install(this);

        btnSignIn = (ImageButton) findViewById(R.id.btn_login);

        btnSignIn.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loginLayout = (LinearLayout)findViewById(R.id.login_layout);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

        View header = navigationView.getHeaderView(0);

        imgProfilePic = (ImageView) header.findViewById(R.id.imageAvataGoogle);
        txtName = (TextView) header.findViewById(R.id.tvNameUserGoogle);
        txtEmail = (TextView) header.findViewById(R.id.tvEmailGoogle);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {

        } else if (id == R.id.nav_diary) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_logout) {
            signOutFromGplus();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    private LinearLayout loginLayout;

    public void toMainActivity(View view) {
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        loginLayout.setVisibility(View.GONE);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mSignInClicked = false;

        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
        //nhan thong tin nguoi su dung

        getProfileInformation();
        //cap nhat giao dien nguoi dung sau khi dang nhap;
        updateUI(true);
        email =  Plus.AccountApi.getAccountName(mGoogleApiClient);
        getAccount(email);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
        updateUI(false);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        if(!connectionResult.hasResolution()){
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this,
                    0).show();
            return;
        }
        if(!mIntentInProgress){
            //Lưu trữ các ConnectionResult để sử dụng sau này;
            mConnectionResult = connectionResult;
            //khi nguoi dung da chon "SignIn" ta phai giai quyet tat ca cac loi
            //cho den khi nguoi dung dang nhap, hoac huy bo;
            if(mSignInClicked){
                resolveSignInError();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                signInWithGplus();
                break;
           case R.id.nav_logout:
                signOutFromGplus();
                revokeGplusAccess();
                break;
            /*case R.id.btn_revoke_access:
                revokeGplusAccess();
                break;*/
        }
    }

    private void revokeGplusAccess(){
        if(mGoogleApiClient.isConnected()){
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {

                public void onResult( Status status) {
                    Log.e(TAG, "User access revoked!");
                    mGoogleApiClient.connect();
                    updateUI(false);
                }
            });
        }
    }

    private void updateUI(Boolean isSignedIn) {

        if (isSignedIn) {
            loginLayout.setVisibility(View.GONE);
        } else {
            loginLayout.setVisibility(View.VISIBLE);
        }
    }

    private  void signInWithGplus(){
        if (!mGoogleApiClient.isConnecting()) {

            mSignInClicked = true;
            resolveSignInError();
        }
    }

    private void resolveSignInError(){
        if(mConnectionResult.hasResolution()){
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            }catch (IntentSender.SendIntentException e){
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode != RESULT_OK){
                mSignInClicked = false;
            }
            mIntentInProgress = false;

            if(!mGoogleApiClient.isConnecting()){
                mGoogleApiClient.connect();
            }else {
                mGoogleApiClient.disconnect();
                mGoogleApiClient.connect();
            }
        }
    }

    private void signOutFromGplus(){
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            databaseManager.delete();
            updateUI(false);
        }
    }

    private void getProfileInformation(){
        try{
            if(Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null){
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();
                String personGooglePlusProfile = currentPerson.getUrl();
                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

                txtName.setText(personName);
                txtEmail.setText(email);

                personPhotoUrl =personPhotoUrl.substring(0, personPhotoUrl.length() - 2)+PROFILE_PIC_SIZE;

                new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);
            }else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public LoadProfileImage(ImageView bmImage){
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisPlay = urls[0];
            Bitmap mIcon = null;

            try {
                InputStream in = new java.net.URL(urldisPlay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            }catch (Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }
        protected void onPostExecute(Bitmap result){
            bmImage.setImageBitmap(result);
        }
    }

    private void getAccount(final String email){
        AccountAPI.getAccount(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                AccountResponse accountResponse = response.body();
                List<Account> account = accountResponse.getData();
                if(account.size()!=0){

                    bindata(account);
                }else {
                    String str="{\"username\":\""+email+"\"}";
                    if (android.os.Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                    }

                    AccountAPI.sendAccount(str);

                    AccountAPI.getAccount(new Callback<AccountResponse>() {
                        @Override
                        public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                            AccountResponse accountResponse = response.body();
                            List<Account> account = accountResponse.getData();
                            bindata(account);


                        }

                        @Override
                        public void onFailure(Call<AccountResponse> call, Throwable t) {

                        }
                    },email);



                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {

            }
        },email);
    }
    private void bindata(List<Account> account){
        this.idAccount=account.get(0).getId();

        fina.setId(this.idAccount);
        databaseManager =new DatabaseManager(MainActivity.this);
        databaseManager.insert(this.idAccount);
        cursor= databaseManager.getList();
        cursor.moveToFirst();
        Log.d("id",String.valueOf(cursor.getInt(0)));

    }

    public void openCustomer(View view){
        Intent intent = new Intent(this, CustomerActivity.class);
        intent.putExtra("id", this.idAccount);
        startActivity(intent);
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void  toCustomer(View view){
        Intent it = new Intent(MainActivity.this, FileChooserActivityU.class);
        startActivity(it);
    }
}
