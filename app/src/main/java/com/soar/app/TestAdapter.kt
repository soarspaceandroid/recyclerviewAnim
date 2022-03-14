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
class TestAdapter:BaseQuickAdapter<ItemData , BaseViewHolder>(R.layout.item_test) {

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    override fun convert(holder: BaseViewHolder, item: ItemData) {
        holder.setText(R.id.itemText , item.str)
        Log.e("soar" , "convert  ${holder.adapterPosition}   ${item.transX}   ${item.isVisiableImage}")
        holder.itemView.post{
            if(item.transX != 0){
                holder.itemView.translationX = item.transX.toFloat()
            }
        }
        holder.setGone(R.id.image , !item.isVisiableImage)

    }
}