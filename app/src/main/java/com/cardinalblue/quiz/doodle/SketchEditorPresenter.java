// Copyright (c) 2017-present Cardinalblue
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

package com.cardinalblue.quiz.doodle;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public class SketchEditorPresenter {

    // Given
    private final Scheduler mWorkerScheduler;
    private final Scheduler mUiScheduler;
    private final ILogger mLogger;

    // View.
    private SketchContract.IEditorView mEditorView = null;
    private SketchContract.ISketchView mSketchView = null;

    // RxJava.
    private final CompositeDisposable mDisposablesOnCreate = new CompositeDisposable();
    private final CompositeDisposable mDisposablesOnResume = new CompositeDisposable();

    // TODO: Inject it.
    public SketchEditorPresenter(Scheduler workerScheduler,
                                 Scheduler uiScheduler,
                                 ILogger logger) {
        mWorkerScheduler = workerScheduler;
        mUiScheduler = uiScheduler;

        mLogger = logger;
    }

    public void bindViewOnCreate(SketchContract.IEditorView editorView,
                                 SketchContract.ISketchView sketchView) {
        mEditorView = editorView;
        mSketchView = sketchView;

        // TODO: Implement any necessary code below.
    }

    public void unBindViewOnDestroy() {
        mDisposablesOnCreate.clear();
    }

    public void onResume() {
        // TODO: Implement any necessary code below.
    }

    public void onPause() {
        mDisposablesOnResume.clear();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

}
