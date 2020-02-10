package com.beyazidyargici.revolutcurrency.util

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.beyazidyargici.revolutcurrency.data.model.dto.CurrencyItem

/**
 *  Created for updating recyclerview data and improve performance
 *  Created by beyazid on 10/02/2020.
 */

class DiffCallback(private val oldList: List<CurrencyItem>, private val newList: List<CurrencyItem>)
    : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int)= true

    override fun areContentsTheSame(oldPos: Int, newPos: Int) = if (newList[newPos].isBase) true else oldList[oldPos].rate == newList[newPos].rate

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
        val diffBundle = Bundle()
        if (!newItem.isBase) {
            when {
                newItem.rate != oldItem.rate -> diffBundle.putDouble(VALUE_CHANGED, newItem.rate)
                newItem.code != oldItem.code -> diffBundle.putString(NAME_CHANGED, newItem.code)
            }
        }
        return if (diffBundle.size() == 0) null else diffBundle
    }

}
