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

package com.cardinalblue.quiz.doodle.view

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.cardinalblue.quiz.doodle.DummyLogger
import com.cardinalblue.quiz.doodle.R
import com.cardinalblue.quiz.doodle.SketchContract
import com.cardinalblue.quiz.doodle.SketchEditorPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SketchEditorActivity : AppCompatActivity(),
                             SketchContract.IEditorView {

    // View.
    private val mBtnClear by lazy { findViewById<View>(R.id.btn_clear) }
    private val mSketchView by lazy { findViewById<SketchView>(R.id.sketch_editor) }

    // Presenter/controllers.
    private val mEditorPresenter by lazy {
        SketchEditorPresenter(
        Schedulers.computation(),
        AndroidSchedulers.mainThread(),
        DummyLogger())
    }

    override fun showProgress(message: String) {
    }

    override fun hideProgress() {
    }

    override fun showErrorAlertThenClose(error: Throwable) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Error")
            .setMessage(error.message)
            .setPositiveButton("Close") { dialog, which -> finish() }
            .show()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)

        // Set content view.
        setContentView(R.layout.activity_sketch_editor)

        // Presenter
        mEditorPresenter.bindViewOnCreate(this, mSketchView)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Force to hide progress.
        hideProgress()

        // Presenter.
        mEditorPresenter.unBindViewOnDestroy()
    }

    override fun onResume() {
        super.onResume()

        // Presenter.
        mEditorPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()

        // Presenter.
        mEditorPresenter.onPause()
    }
}
