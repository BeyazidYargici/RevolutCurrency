package com.beyazidyargici.revolutcurrency.ui.currencyrates


import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyazidyargici.revolutcurrency.R
import com.beyazidyargici.revolutcurrency.base.BaseFragment
import com.beyazidyargici.revolutcurrency.data.model.CurrencyMapper
import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel
import com.beyazidyargici.revolutcurrency.ui.MainActivity
import com.beyazidyargici.revolutcurrency.util.DEFAULT_BASE_CURRENCY
import com.beyazidyargici.revolutcurrency.util.DEFAULT_BASE_VALUE
import com.beyazidyargici.revolutcurrency.util.init
import kotlinx.android.synthetic.main.fragment_currency_rates.*
import javax.inject.Inject


/**
 *  Created by beyazid on 10/02/2020.
 *  This fragment is responsible for showing the currency rates
 */
class CurrencyRatesFragment : BaseFragment(), CurrencyListener {


    override fun getLayout(): Int = R.layout.fragment_currency_rates

    @Inject
    lateinit var vmFactoryRates: CurrencyRatesViewModelFactory

    @Inject
    lateinit var viewModel: CurrencyRatesViewModel

    @Inject
    lateinit var currencyMapper: CurrencyMapper

    lateinit var adapter: CurrencyRatesAdapter

    var baseCurrency = DEFAULT_BASE_CURRENCY
    var baseValue = DEFAULT_BASE_VALUE

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, vmFactoryRates).get(CurrencyRatesViewModel::class.java)
        recyclerView = fr_currency_rates_recyclerview
        initAdapter()
    }

    /**
     *  fetch currency response from the repository
     *  if [CurrencyModel] is not null, convert and send items to the adapter for filling list
     */
    private fun getData(baseCurrency: String) {
        viewModel.stopTimer()
        viewModel.intervalRequest(baseCurrency)
        // observer for the response
        viewModel.currencyModel.observe(this, Observer {
            if (it == null) return@Observer
            val currencies = currencyMapper.convertResponseToCurrencyList(it, baseValue)
            adapter.submitItems(currencies)
        })
        // observer for network availability
        viewModel.isNetworkAvailable.observe(this, Observer {
            if (it == null) return@Observer
            if (!it) (activity as MainActivity).showDialog()
        })
    }

    /**
     *  initialize the adapter
     */
    private fun initAdapter() {
        adapter = CurrencyRatesAdapter(activity!!, mutableListOf(), this)
        recyclerView.init(adapter)
    }

    /**
     *  continue to fetching data when fragment is foreground
     */
    override fun onResume() {
        super.onResume()
        getData(baseCurrency)
    }

    override fun onPause() {
        super.onPause()
        // cancel timer for avoiding memory leak and unnecessary data usage
        viewModel.stopTimer()
    }

    override fun changeBaseCurrency(newBaseCurrency: String, index: Int) {
        baseCurrency = newBaseCurrency
        Handler().postDelayed({ (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(0, 20); }, 400)
        Handler().postDelayed({ adapter.notifyItemMoved(index,0) }, 400)
        Handler().postDelayed({ adapter.notifyDataSetChanged() }, 700)
        getData(baseCurrency)
    }

    override fun changeBaseValue(amount: Double) {
        baseValue = amount
    }

}
