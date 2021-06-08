package com.aqube.mvi.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqube.mvi.common.mvi.IModel
import com.aqube.mvi.common.mvi.ViewAction
import com.aqube.mvi.common.mvi.ViewIntent
import com.aqube.mvi.common.mvi.ViewState
import com.aqube.mvi.common.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState>(
    private val contextProvider: CoroutineContextProvider
) : ViewModel(), IModel<STATE, INTENT> {

    protected val viewState = MutableLiveData<STATE>()
    override val state: LiveData<STATE>
        get() {
            return viewState
        }

    final override fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }

    abstract fun intentToAction(intent: INTENT): ACTION
    abstract fun handleAction(action: ACTION)

    private val job: Job = Job()

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.io + job) {
            block()
        }
    }

    protected fun launchCoroutineMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.main + job) {
            block()
        }
    }

    public override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}