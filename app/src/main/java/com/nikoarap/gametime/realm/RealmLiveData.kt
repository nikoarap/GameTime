package com.nikoarap.gametime.realm

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

/**
 * LiveData class for observing changes in a RealmResults set.
 * This class allows you to observe a set of RealmResults and automatically receive updates
 * whenever the data in the RealmResults changes. It simplifies data synchronization between
 * your Realm database and your UI components, ensuring that your UI is always up to date.
 *
 * @param T The type of data in the RealmResults, usually a Realm model class.
 * @param results The initial RealmResults to observe.
 */
class RealmLiveData<T : RealmModel?>(
    private var results: RealmResults<T>?
): LiveData<RealmResults<T>?>() {

    private val listener = RealmChangeListener { value: RealmResults<T>? -> this.setValue(value) }

    /**
     * Initializes the RealmLiveData with the provided RealmResults.
     */
    init {
        value = results
    }

    /**
     * Sets a new set of RealmResults to observe.
     * When you call this method, it stops observing the previous set of RealmResults and begins
     * observing the new set. It also updates the LiveData value to the new set of results.
     *
     * @param results The new RealmResults to observe.
     */
    fun setResults(results: RealmResults<T>) {
        this.results?.removeChangeListener(listener)
        this.results = results
        value = results
        this.results?.addChangeListener(listener)
    }

    /**
     * Called when the LiveData becomes active (e.g. when there are active observers).
     * This method registers the RealmChangeListener to start listening for changes in theRealmResults.
     */
    override fun onActive() {
        results?.addChangeListener(listener)
    }

    /**
     * Called when the LiveData becomes inactive (e.g. when there are no active observers).
     * This method unregisters the RealmChangeListener to stop listening for changes in the
     * RealmResults. This helps optimize resource usage.
     */
    override fun onInactive() {
        results?.removeChangeListener(listener)
    }
}