package com.zeemyself.pangyacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swas on 9/29/2015.
 */
public class FacebookFragment extends Fragment {
    private ProfilePictureView profilePictureView;
    private CallbackManager mCallbackManager;
    private LoginFB fb;
    private AccessTokenTracker accessTokenTracker;
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();

            Profile profile = Profile.getCurrentProfile();
            if(profile!=null) {
                fb.isVIP(profile.getId());
                fb.onLogin("Welcome " + profile.getName());
                accessTokenTracker.startTracking();
            }
        }

        @Override
        public void onCancel() {
            fb.onLogin("Cancel");
        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    /**
     *
     * @return true if user already login else return false
     */
    public boolean isLogin(){
        if(AccessToken.getCurrentAccessToken()!=null)
            return true;
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.facebookbutton, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        List<String> permission = new ArrayList<String>();
        permission.add("user_friends");
        loginButton.setReadPermissions(permission);
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fb = (LoginFB) getActivity();
        accessTokenTracker = new AccessTokenTracker(){

            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null){
                    fb.onLogin("You Logout?");
                }
            }
        };
    }
}
