package com.example.dell.mycook;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.example.dell.mycook.Activities.VIdeoVIewActivity;
import com.example.dell.mycook.Activities.VideosListActivity;
import com.example.dell.mycook.Auth.AuthenicationUsers;
import com.example.dell.mycook.Core.BasicFeatures;
import com.example.dell.mycook.fragments.RecipeOfweek;
import com.example.dell.mycook.fragments.favorite;
import com.example.dell.mycook.fragments.home;
import com.example.dell.mycook.fragments.smartCook;
import com.example.dell.mycook.items.MenuRecyclerAdapter;
import com.example.dell.mycook.items.MymenuItem;
import com.example.dell.mycook.models.StaticDataHolder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;
import io.fabric.sdk.android.Fabric;

import static com.example.dell.mycook.Core.BasicFeatures.getRealPathFromURI;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAcivity";
    DrawerLayout drawer;
    private Toolbar toolbar;
    TextView logout;
    TextView clickHere;
    private ActionBarDrawerToggle drawerToggle;
    public GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    Button skiplogout;
    String receiPieOfThWeek = "Lg8fK5IrMZY";

    private final int VIDEO_CAPTURED = 999;

    MaterialSearchView searchView;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    View dpContainer;
    ImageView dpImage;
    TextView dpName;
    TextView dpEmail;
    FloatingActionButton uploadFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        dpContainer = findViewById(R.id.dpContainer);
        dpImage = (ImageView) findViewById(R.id.dpImage);
        dpName = (TextView) findViewById(R.id.dpName);
        dpEmail = (TextView) findViewById(R.id.dpEmail);
        mAuth = FirebaseAuth.getInstance();

        uploadFab = (FloatingActionButton) findViewById(R.id.uploadBtn);

        //for uploading video
        final AlertDialog uploadDialog = DialogForUploading();

        uploadFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploading video
                uploadDialog.show();
            }
        });

        FirebaseDatabase.getInstance().getReference().child("ofTheWeek").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                receiPieOfThWeek = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        updateDps();

//here we are configuring SoosleSigin option
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();
        // [END config_signin]

        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(MainActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this, "Authentication Initialization failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        StaticDataHolder.loadData();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        drawer.addDrawerListener(drawerToggle);
        setupRecycler();

        clickHere = (TextView) findViewById(R.id.email);
        logout = (TextView) findViewById(R.id.signout);
        skiplogout = (Button) findViewById(R.id.skip);
        final AlertDialog alertDialog = signInDialog();
        final AlertDialog Dialog = signOutDialog();

        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.show();
            }
        });
        switchFragment(new home());


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                smartCook sc = new smartCook();
                Bundle scdata = new Bundle();
                scdata.putString("searchQuery",query);
                sc.setArguments(scdata);
                switchFragment(sc);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
    }

    //show nam and dp of gmail user
    private void updateDps() {
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            Glide.with(this).load(user.getPhotoUrl()).skipMemoryCache(true).into(dpImage);
            dpName.setText(user.getDisplayName());
            dpName.setText(user.getEmail());
            dpContainer.setVisibility(View.VISIBLE);
            uploadFab.setVisibility(View.VISIBLE);
        } else {
            dpContainer.setVisibility(View.GONE);
            uploadFab.setVisibility(View.GONE);
        }
    }

    //id for sigin gmail
    public String getUid() {
        if (mAuth.getCurrentUser() != null) {
            return mAuth.getCurrentUser().getUid();
        }
        return null;
    }
//upload video

    private AlertDialog DialogForUploading() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.onfabclick, null);
        dialog.setView(dialogLayout);

        Button fromcamera = (Button) dialogLayout.findViewById(R.id.fromcamera);

        fromcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
                captureVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 59);
                captureVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                startActivityForResult(captureVideoIntent, VIDEO_CAPTURED);
            }
        });

        final AlertDialog alertDialog = dialog.create();
        return alertDialog;
    }

    //
    private AlertDialog signInDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.activity_google, null);
        dialog.setView(dialogLayout);

        final AlertDialog alertDialog = dialog.create();
        Button skipLogin = (Button) dialogLayout.findViewById(R.id.skipLogin);
        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        SignInButton sign_in_button = (SignInButton) dialogLayout.findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                alertDialog.dismiss();
            }
        });
        return alertDialog;
    }


    //for sign out
    private AlertDialog signOutDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.signout, null);
        dialog.setView(dialogLayout);

        final AlertDialog alertDialog = dialog.create();
        Button skipLogin = (Button) dialogLayout.findViewById(R.id.skip);
        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        Button sign_Out_button = (Button) dialogLayout.findViewById(R.id.Logout);
        sign_Out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Configure Google Sign Out

                signOut();
            }
        });

        return alertDialog;

    }

    private void signOut() {

        final AlertDialog alertDialog = signOutDialog();
        mAuth.signOut();
        updateDps();
        alertDialog.show();

    }

    private final int RC_SIGN_IN = 9199;

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.e("GS Responded", "");
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Log.e("GS Success", "");
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Log.e("GS Failed", result.getStatus().toString() + "");
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
        //for capturing video from camera
        else if (requestCode == VIDEO_CAPTURED) {
            Uri selectedImage = data.getData();
            String path = selectedImage.getPath();
            final String videoPath = getRealPathFromURI(selectedImage, MainActivity.this);
            Log.e("Video Path", videoPath);
            final ProgressDialog pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Uploading Video... please wait");

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText edittext = new EditText(this);
            alert.setTitle("Enter Video Title");

            alert.setView(edittext);

            alert.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    String vidTitle = edittext.getText().toString();
                    if (vidTitle.length() <= 0) {
                        edittext.setError("PLease enter title");
                        return;
                    }
                    BasicFeatures.uploadToYoutube(vidTitle, "Video description", new File(videoPath), new JsonHttpResponseHandler() {
                        @Override
                        public void onStart() {
                            pd.show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                String videoUrl = response.getString("url");
                                Log.e("videoUrl", videoUrl);

                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onProgress(long bytesWritten, long totalSize) {
                            Log.e("Progress Upload", bytesWritten + "/" + totalSize);
                        }

                        @Override
                        public void onFinish() {
                            pd.dismiss();
                        }
                    });
                }
            });

            alert.show();


        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        // [START_EXCLUDE silent]
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Signing in");
        pd.show();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication Successful.",
                                    Toast.LENGTH_SHORT).show();
                            updateDps();
                        }
                        // [START_EXCLUDE]
                        pd.dismiss();
                        // [END_EXCLUDE]
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    private void searchDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        //this is what I did to added the layout to the alert dialog
        View layout = inflater.inflate(R.layout.search_dialog, null);
        alert.setView(layout);

        final AlertDialog dialog = alert.create();

        final EditText searchEt = (EditText) layout.findViewById(R.id.searchEt);
        Button searchBtn = (Button) layout.findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTxt = searchEt.getText().toString();
                if (searchTxt.length() <= 0) {
                    searchEt.setError("Please enter some words to search!");
                    return;
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setupRecycler() {
        RecyclerView recycler = (RecyclerView) findViewById(R.id.menuRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new MenuRecyclerAdapter(new MymenuItem[]{
                new MymenuItem("Home", "&#xf187;", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchFragment(new home());
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }),
                new MymenuItem("Favourite", "&#xf004;", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchFragment(new favorite());
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }),
                new MymenuItem("SmartFood", "&#xf03d;", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchFragment(new smartCook());
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }),

                new MymenuItem("Recipe Of the Week", "&#xf007;", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //switchFragment(new RecipeOfweek());
                        Intent i = new Intent(MainActivity.this, VIdeoVIewActivity.class);
                        i.putExtra("videoId", receiPieOfThWeek);
                        i.putExtra("videoTitle", "Recipe Of the Week");
                        i.putExtra("videoImage", "");
                        startActivity(i);
                        drawer.closeDrawer(GravityCompat.START);
                    }
                })
        }
        );
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }else{
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void switchFragment(Fragment page) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.myfragment, page);
        ft.commit();
    }


}
