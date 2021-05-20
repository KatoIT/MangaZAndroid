package com.example.mangaz.Model;

public class Follow {
    private String UserNameFollow;
    private String User_Username;
    private User mUser;

    public Follow(String userNameFollow, String user_Username, User mUser) {
        UserNameFollow = userNameFollow;
        User_Username = user_Username;
        this.mUser = mUser;
    }

    public String getUserNameFollow() {
        return UserNameFollow;
    }

    public void setUserNameFollow(String userNameFollow) {
        UserNameFollow = userNameFollow;
    }

    public String getUser_Username() {
        return User_Username;
    }

    public void setUser_Username(String user_Username) {
        User_Username = user_Username;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
