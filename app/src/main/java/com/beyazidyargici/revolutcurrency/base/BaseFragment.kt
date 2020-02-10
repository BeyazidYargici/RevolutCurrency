package com.beyazidyargici.revolutcurrency.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.beyazidyargici.revolutcurrency.util.gone
import com.beyazidyargici.revolutcurrency.util.visible
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 *  All fragments should be extended from this fragment
 *  Created by beyazid on 10/02/2020.
 */
abstract class BaseFragment : Fragment(), CoroutineScope, HasAndroidInjector {

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    // prevent any kind of exception or crashes
    private lateinit var job: Job
    // progressbar will be showing while fetching data from network
    lateinit var progressBar: ProgressBar
    // RecyclerView will be listing response items
    lateinit var recyclerView: RecyclerView


    /**
     * This method will override in fragments to inflate the view
     *
     * @return the layout id which will inflated
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // this job will be running on the main dispatcher(main thread)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayout(), container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    /**
     * ProgressBar will be visible when fetching the data and will be gone when fetching finish
     */
    fun progressBarVisibility(isVisible: Boolean) = if (isVisible) progressBar.visible() else progressBar.gone()


}