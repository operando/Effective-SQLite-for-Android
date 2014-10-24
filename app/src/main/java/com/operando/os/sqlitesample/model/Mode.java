package com.operando.os.sqlitesample.model;

import android.content.Intent;

import lombok.Data;

@Data
public class Mode {

    private Intent intent;

    private String title;

    public Mode(Intent intent, String title) {
        this.intent = intent;
        this.title = title;
    }
}
