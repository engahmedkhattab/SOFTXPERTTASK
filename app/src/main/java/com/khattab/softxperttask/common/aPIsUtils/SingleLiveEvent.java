package com.khattab.softxperttask.common.aPIsUtils;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

/*
* A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.

This avoids a common problem with events: on configuration change (like rotation) an update can be
 emitted if the observer is active. This LiveData only calls the observable if there's an explicit
  call to setValue() or call().
*/

// First extend the MutableLiveData class to create an ActionLiveData

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @Override
    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        // Being strict about the observer numbers is up to you
        // I thought it made sense to only allow one to handle the event
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                // We ignore any null values and early return
                // We set the value to null straight after emitting the change to the observer
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t) {
        mPending.set(true);
        super.setValue(t);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}