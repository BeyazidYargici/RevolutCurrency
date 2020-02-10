package com.beyazidyargici.revolutcurrency.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beyazidyargici.revolutcurrency.R
import java.text.DecimalFormat

fun RecyclerView.init(adapter: RecyclerView.Adapter<*>) {
    val fallDown = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down_animation)
    this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    this.layoutAnimation = fallDown
    this.setHasFixedSize(true)
    this.itemAnimator = DefaultItemAnimator()
    this.adapter = adapter
    adapter.notifyDataSetChanged()
}

fun View.visible() = run { this.visibility = View.VISIBLE }

fun View.invisible() = run { this.visibility = View.INVISIBLE}

fun View.gone() = run { this.visibility = View.GONE }

fun TextView.slideFromTop() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_from_top))

fun ImageView.alphaAnimation()  {
    val alphaAnim = AlphaAnimation(0.4f, 1.00f)
    alphaAnim.duration=800
    this.animation = alphaAnim
    alphaAnim.start()
}

fun Double.formattedAmount(): String = DecimalFormat("#.##").format(this)

@SuppressLint("DefaultLocale")
fun String.getFlagResId(context : Context) = context.resources.getIdentifier("icn_".plus(this.toLowerCase()), "drawable", context.packageName)