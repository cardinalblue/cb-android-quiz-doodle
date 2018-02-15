// Copyright (c) 2017-present CardinalBlue
//
// Author: boy@cardinalblue.com
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.cardinalblue.quiz.doodle.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cardinalblue.quiz.doodle.DummyLogger;
import com.cardinalblue.quiz.doodle.R;
import com.cardinalblue.quiz.doodle.SketchContract;
import com.cardinalblue.quiz.doodle.SketchEditorPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SketchEditorActivity
    extends AppCompatActivity
    implements SketchContract.IEditorView {

    // View.
    View mBtnClear;
    SketchView mSketchView;
    ProgressDialog mProgressDialog;

    // Presenter/controllers.
    SketchEditorPresenter mEditorPresenter;

    @Override
    public void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }

        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog == null) return;

        mProgressDialog.dismiss();
    }

    @Override
    public void showErrorAlertThenClose(Throwable error) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Error")
               .setMessage(error.getMessage())
               .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       finish();
                   }
               })
               .show();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        // Set content view.
        setContentView(R.layout.activity_sketch_editor);

        // Views.
        mBtnClear = findViewById(R.id.btn_clear);
        mSketchView = findViewById(R.id.sketch_editor);

        // Presenter
        mEditorPresenter = new SketchEditorPresenter(
            Schedulers.computation(),
            AndroidSchedulers.mainThread(),
            new DummyLogger());
        mEditorPresenter.bindViewOnCreate(this, mSketchView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Force to hide progress.
        hideProgress();

        // Presenter.
        mEditorPresenter.unBindViewOnDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Presenter.
        mEditorPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Presenter.
        mEditorPresenter.onPause();
    }
}
