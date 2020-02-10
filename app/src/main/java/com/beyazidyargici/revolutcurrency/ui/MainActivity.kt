package com.beyazidyargici.revolutcurrency.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.commit
import com.beyazidyargici.revolutcurrency.R
import com.beyazidyargici.revolutcurrency.base.BaseActivity
import com.beyazidyargici.revolutcurrency.ui.currencyrates.CurrencyRatesFragment
import com.beyazidyargici.revolutcurrency.util.CustomAlertDialog
import com.beyazidyargici.revolutcurrency.util.DialogInterface
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

/**
 *  All fragments building on this activity to apply one activity multiple fragment approach
 *  Created by beyazid on 10/02/2020.
 */
class MainActivity : BaseActivity(), DialogInterface {

    var context: Context? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun getLayout(): Int =
        R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        supportFragmentManager.commit(allowStateLoss = true) {
            val fragment = CurrencyRatesFragment()
            replace(R.id.ac_main_fragment_container, fragment, fragment.tag)
        }
    }

    private val customAlertDialog = CustomAlertDialog(this)

    override fun showDialog() = customAlertDialog.createAlertDialog()


}
