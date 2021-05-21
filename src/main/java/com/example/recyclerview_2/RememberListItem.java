package com.example.recyclerview_2;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Comparator;


@Entity
public class RememberListItem implements Serializable, Comparable<RememberListItem> {
    @PrimaryKey
    public long mDeadline;

    public String mName;
    public int isFinished;

    public String strDeadline;

    public RememberListItem() {
    }

    public RememberListItem(String mName, String strDeadline, int isFinished, long mDeadline) {
        this.mName = mName;
        this.strDeadline = strDeadline;
        this.mDeadline = mDeadline;
        this.isFinished = isFinished;

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getDeadline() {
        return strDeadline;
    }

    public void setDeadline(String mDeadline) {
        this.strDeadline = mDeadline;
    }

    public int isFinished() {
        return isFinished;
    }

    public void setFinished(int finished) {
        isFinished = finished;
    }

    @Override
    public int compareTo(RememberListItem o) {
        return (int)(this.mDeadline - o.mDeadline);
    }
}

