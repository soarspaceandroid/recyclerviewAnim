package com.soar.app

import android.graphics.Color
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlin.random.Random

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2022/2/24
 *※ Time : 5:38 下午
 *※ Project : App
 *※ Package : com.soar.app
 *----------------------------------------------------
 */
class TestAdapter:BaseQuickAdapter<String , BaseViewHolder>(R.layout.item_test) {
    var colors = arrayListOf<Int>()
    init {
        colors.add(Color.BLACK)
        colors.add(Color.YELLOW)
        colors.add(Color.GRAY)
        colors.add(Color.GREEN)
        colors.add(Color.RED)
        colors.add(Color.BLUE)
        colors.add(Color.CYAN)
        colors.add(Color.DKGRAY)
        colors.add(Color.MAGENTA)
    }
    var randow = Random.Default

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.itemText , item)

        Log.d("soar" , "===${colors.size}")
        holder.setBackgroundColor(R.id.itemText ,colors[randow.nextInt(9)])
    }
}