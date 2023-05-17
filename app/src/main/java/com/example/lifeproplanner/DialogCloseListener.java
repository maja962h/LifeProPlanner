package com.example.lifeproplanner;

import android.content.DialogInterface;

public interface DialogCloseListener {
    public void handleDialogClose(DialogInterface dialog);

    void deleteTask(int position);

    void onDeleteClick(int position);
}