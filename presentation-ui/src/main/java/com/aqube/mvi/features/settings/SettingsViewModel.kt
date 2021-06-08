package com.aqube.mvi.features.settings

import androidx.lifecycle.ViewModel
import com.aqube.mvi.common.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider
) : ViewModel(){
}

