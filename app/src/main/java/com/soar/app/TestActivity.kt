package com.soar.app

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_test.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2022/2/24
 *※ Time : 5:29 下午
 *※ Project : App
 *※ Package : com.soar.app
 *----------------------------------------------------
 */
class TestActivity:AppCompatActivity() {


    var testAdapter = TestAdapter()
    var testData = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        list.layoutManager = layoutManager
        list.adapter = testAdapter

        testData.add("测试1")
        testData.add("测试2")
        testData.add("测试3")
        testData.add("测试4")
        testData.add("测试5")
        testData.add("测试6")
        testData.add("测试7")
        testData.add("测试8")
        testData.add("测试9")
        testData.add("测试10")
        testData.add("测试11")
        testData.add("测试12")

        testAdapter.setNewData(testData)


        list.recycledViewPool.setMaxRecycledViews(0,0);

        test.setOnClickListener {
            if(tem.visibility == View.VISIBLE){
                //动画

                tem.visibility = View.GONE


                var wid = resources.getDimensionPixelSize(R.dimen.width)
                var firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                var lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                var temlist = (firstVisibleItem..lastVisibleItem).toList()
                temlist.forEachIndexed { index, s ->
                    layoutManager.findViewByPosition(temlist[index])?.let {
                        it.translationX = wid.toFloat()
                    }

                }




                temlist.forEachIndexed { index, s ->
                    layoutManager.findViewByPosition(temlist[index])?.let {
                        var animXChild = ObjectAnimator.ofFloat(it , "translationX",wid.toFloat(), 0f )
                        animXChild.duration = 300
                        animXChild.startDelay = (index * 100).toLong()
                        animXChild.start()
                    }

                }

            }else{

                var wid = resources.getDimensionPixelSize(R.dimen.width)
                var firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                var lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                var temlist = (firstVisibleItem..lastVisibleItem).toList()

                temlist.forEachIndexed { index, s ->
                    Log.e("soar" , "index  ${index}")
                    layoutManager.findViewByPosition(temlist[temlist.size-1 - index])?.let {
                        var animXChild = ObjectAnimator.ofFloat(it , "translationX",0f  , wid.toFloat())
                        animXChild.duration = 300
                        if(index == temlist.size - 1) {
                            animXChild.addListener(object : Animator.AnimatorListener {
                                override fun onAnimationStart(animation: Animator?) {
                                }

                                override fun onAnimationEnd(animation: Animator?) {
                                    tem.visibility = View.VISIBLE
                                    temlist.forEachIndexed { index, s ->
                                        layoutManager.findViewByPosition(temlist[index])?.let {
                                            it.translationX = 0f
                                        }

                                    }
                                }

                                override fun onAnimationCancel(animation: Animator?) {
                                    tem.visibility = View.VISIBLE
                                    temlist.forEachIndexed { index, s ->
                                        layoutManager.findViewByPosition(temlist[index])?.let {
                                            it.translationX = -wid.toFloat()
                                        }

                                    }
                                }

                                override fun onAnimationRepeat(animation: Animator?) {
                                }

                            })
                        }
                        animXChild.startDelay = index * 100.toLong()
                        animXChild.start()
                    }

                }

            }
        }

    }


    fun dp2px(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }



}