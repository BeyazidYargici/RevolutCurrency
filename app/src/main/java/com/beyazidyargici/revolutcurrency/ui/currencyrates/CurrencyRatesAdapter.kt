package com.beyazidyargici.revolutcurrency.ui.currencyrates

import android.annotation.SuppressLint
import android.content.Context
import android.icu.util.Currency
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beyazidyargici.revolutcurrency.R
import com.beyazidyargici.revolutcurrency.data.model.dto.CurrencyItem
import com.beyazidyargici.revolutcurrency.util.*
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_currency_item.view.*
import java.util.*
import kotlin.Comparator


/**
 *  This adapter fills the list of recyclerview
 *  Created by beyazid on 10/02/2020.
 */
class CurrencyRatesAdapter(val context: Context,
                           private val currencies: MutableList<CurrencyItem>,
                           val currencyListener: CurrencyListener) : RecyclerView.Adapter<CurrencyRatesAdapter.CurrencyRatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRatesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_currency_item, parent, false)
        return CurrencyRatesViewHolder(v)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: CurrencyRatesViewHolder, position: Int) =holder.bindItem(position)

    override fun onBindViewHolder(holder: CurrencyRatesViewHolder, position: Int, payloads: List<Any>) = when {
            payloads.isEmpty() -> onBindViewHolder(holder,position)
            else -> {
                val o = payloads[0] as Bundle
                for (key in o.keySet()) {
                    when (key) {
                        VALUE_CHANGED -> holder.changeAmount(position)
                        NAME_CHANGED -> holder.bindItem(position)
                    }
                }
            }
        }

    /**
     *  Called by [CurrencyRatesFragment] to update changed items in the list
     */
    fun submitItems(newCurrencies: MutableList<CurrencyItem>) {
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(DiffCallback(currencies, newCurrencies))
        diffResult.dispatchUpdatesTo(this)
        currencies.clear()
        currencies.addAll(newCurrencies)
    }

    fun selectBaseCurrencyItem(currencyItem: CurrencyItem) {
        val selectedItemIndex = currencies.indexOfFirst { it.code == currencyItem.code }
        if (selectedItemIndex != 0) Collections.swap(currencies, 0, selectedItemIndex)
        currencyListener.changeBaseValue(DEFAULT_BASE_VALUE)
        currencyListener.changeBaseCurrency(currencyItem.code, selectedItemIndex)
    }


    inner class CurrencyRatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val container = view.currency_item_container
        private val tvCode = view.row_currency_item_tv_code
        private val tvName = view.row_currency_item_tv_name
        private val etAmount = view.row_currency_item_et_amount
        private val tvAmount = view.row_currency_item_tv_amount
        private val ivFlag = view.row_currency_item_iv_flag
        private val tvSymbol = view.row_currency_item_tv_symbol

        /**
         * [CurrencyRatesAdapter] adapter calls this method for binding items to the list
         */
        fun bindItem(pos: Int) {
            val currencyItem = currencies[pos]
            val symbol = getCurrencySymbol(currencyItem)
            val code = currencyItem.code
            val name = getCurrencyNameByCode(currencyItem.code)
            tvName.text = name
            tvName.slideFromTop()
            tvCode.text = code
            tvCode.slideFromTop()
            circularCountryFlag(code)
            setAmount(currencyItem)
            tvSymbol.text = symbol
            currencyItemClickListener(currencyItem)
        }

        fun changeAmount(pos: Int) {
            val currencyItem = currencies[pos]
            setAmount(currencyItem)
        }

        @SuppressLint("DefaultLocale")
        private fun circularCountryFlag(currencyCode: String) {
            val drawableResId = currencyCode.getFlagResId(context)
            GlideApp.with(context).load(drawableResId)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .apply(RequestOptions.circleCropTransform())
                .skipMemoryCache(false)
                .into(ivFlag)
            // alpha animation from extensions
            ivFlag.alphaAnimation()
        }

        private fun baseAmountListener(currencyItem: CurrencyItem) {
            if (currencyItem.isBase) {
                etAmount.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {
                        if (currencyItem.isBase && s.toString().isNotBlank()) currencyListener.changeBaseValue(s.toString().toDouble())
                    }

                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit
                })
            }
        }

        private fun getCurrencySymbol(currencyItem: CurrencyItem): String =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) SymbolUtils.getCurrencySymbol(currencyItem.code).toString() else ""

        private fun setAmount(currencyItem: CurrencyItem){
            if (currencyItem.isBase) {
                baseAmountListener(currencyItem)
                etAmount.setSelection(etAmount.text.toString().length)
                etAmount.visible()
                etAmount.setText(currencies[0].rate.formattedAmount())
                tvAmount.invisible()
            } else {
                etAmount.gone()
                tvAmount.visible()
                tvAmount.text = (currencies[0].rate * currencyItem.rate).formattedAmount()
            }
        }

        private fun currencyItemClickListener(currencyItem: CurrencyItem){
            container.setOnClickListener {
                currencyItem.rate = DEFAULT_BASE_VALUE
                selectBaseCurrencyItem(currencyItem)
            }
        }
    }


    internal object SymbolUtils {
        private var currencyLocaleMap: SortedMap<Currency?, Locale?>? = null
        fun getCurrencySymbol(currencyCode: String?): String? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val currency = Currency.getInstance(currencyCode)
                currency.getSymbol(currencyLocaleMap?.get(currency))
            } else ""

        init {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                currencyLocaleMap =
                    TreeMap(Comparator { c1, c2 -> c1!!.currencyCode.compareTo(c2!!.currencyCode) })
                for (locale in Locale.getAvailableLocales()) {
                    try {
                        val currency: Currency = Currency.getInstance(locale)
                        (currencyLocaleMap as TreeMap<Currency?, Locale?>)[currency] = locale
                    } catch (e: Exception) {

                    }
                }
            }
        }
    }

}