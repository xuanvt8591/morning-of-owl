package com.itplus.ad0618e.morningofowl.models;

public class UserMovie {
    private int userMovieID;
    private int movieID;
    private String userID;
    private boolean viewLater;

    public int getUserMovieID() {
        return userMovieID;
    }

    public void setUserMovieID(int userMovieID) {
        this.userMovieID = userMovieID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isViewLater() {
        return viewLater;
    }

    public void setViewLater(boolean viewLater) {
        this.viewLater = viewLater;
    }
}
