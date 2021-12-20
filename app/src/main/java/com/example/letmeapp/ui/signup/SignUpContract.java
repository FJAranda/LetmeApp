package com.example.letmeapp.ui.signup;

import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.base.IProgressView;

public interface SignUpContract {

    interface SignUpCallback{
        void onSuccess(User user);
        void onFailure(String message);
    }

    interface View extends IProgressView, SignUpCallback {
        void setUserEmptyError();
        void setUserShortError();
        void setEmailEmptyError();
        void setEmailError();
        void setPasswordEmptyError();
        void setPasswordError();
        void setConfirmPasswordError();

    }

    interface Presenter{
        void validate(User user, String password);
    }

    interface Interactor{
        void onSuccess(User user);
        void onFailure(String message);
        void onUserEmptyError();
        void onUserShortError();
        void onEmailEmptyError();
        void onEmailError();
        void onPasswordEmptyError();
        void onPasswordError();
        void onConfirmPasswordError();
    }

    interface Repository{
        void signUp(User user);
    }
}
