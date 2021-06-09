package com.aqube.mvi.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aqube.mvi.extensions.dismissLoadingDialog
import com.aqube.mvi.extensions.showLoadingDialog
import com.aqube.mvi.extensions.showSnackBar
import com.aqube.mvi.presentation.common.*
import timber.log.Timber

abstract class BaseFragment<
        INTENT : ViewIntent,
        ACTION : ViewAction,
        STATE : ViewState,
        VB : ViewBinding,
        ViewModel : BaseViewModel<INTENT, ACTION, STATE>> : Fragment(), IViewRenderer<STATE> {

    private var _binding: VB? = null
    val binding get() = _binding!!
    protected abstract val viewModel: ViewModel
    abstract fun getViewBinding(): VB

    private lateinit var _viewState: STATE
    val viewState get() = _viewState

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        viewModel.state.observe(viewLifecycleOwner, {
            _viewState = it
            render(it)
        })
        initDATA()
        initEVENT()
    }

    abstract fun initUI()
    abstract fun initDATA()
    abstract fun initEVENT()
    fun dispatchIntent(intent: INTENT) {
        viewModel.dispatchIntent(intent)
    }

    protected open fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoadingDialog() else dismissLoadingDialog()
    }

    protected open fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        dismissLoadingDialog()
        Timber.e(message)
        showSnackBar(binding.root, message)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}