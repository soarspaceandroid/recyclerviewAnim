package com.soar.app

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
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
    var testData = arrayListOf<ItemData>()

    var isImageVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        list.layoutManager = layoutManager


        list.adapter = testAdapter
        testData.add(ItemData("测试1" , 0 , true))
        testData.add(ItemData("测试2" , 0, true))
        testData.add(ItemData("测试3" , 0 , true))
        testData.add(ItemData("测试4" , 0 , true))
        testData.add(ItemData("测试5" , 0 , true))
        testData.add(ItemData("测试6" , 0 , true))
        testData.add(ItemData("测试7" , 0 , true))
        testData.add(ItemData("测试8" , 0 , true))
        testData.add(ItemData("测试9" , 0 , true))
        testData.add(ItemData("测试10", 0 , true))
        testData.add(ItemData("测试11" , 0 , true))
        testData.add(ItemData("测试12" , 0 , true))
        testData.add(ItemData("测试13" , 0 , true))
        testData.add(ItemData("测试14" , 0, true))
        testData.add(ItemData("测试15" , 0 , true))
        testData.add(ItemData("测试16" , 0 , true))
        testData.add(ItemData("测试17" , 0 , true))
        testData.add(ItemData("测试18" , 0 , true))
        testData.add(ItemData("测试19" , 0 , true))
        testData.add(ItemData("测试20" , 0 , true))
        testData.add(ItemData("测试21" , 0 , true))
        testData.add(ItemData("测试22", 0 , true))
        testData.add(ItemData("测试23" , 0 , true))
        testData.add(ItemData("测试24" , 0 , true))


        testAdapter.setNewData(testData)


        testFirst.setOnClickListener {
            if (isImageVisible) {
                isImageVisible = false
                var height = resources.getDimensionPixelSize(R.dimen.height)
                var firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                Log.e("soar", "    (wid/ itemwid).toInt() ")
                var lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                Log.e("soar", "last  ${lastVisibleItem}")
                var temlist = (firstVisibleItem..lastVisibleItem).toList()
                temlist.forEachIndexed { index, s ->
                    Log.e(
                        "soar",
                        "index  ${index}  ${temlist[index]}   ${layoutManager.getChildAt(index)}"
                    )
                    var view = layoutManager.findViewByPosition(temlist[index])
                    if (view != null) {
                        var image = view.findViewById<LinearLayout>(R.id.image)
                        var layoutParams = image.layoutParams
                        var animXChild = ValueAnimator.ofInt(height, 0)
                        animXChild.addUpdateListener {
                            Log.e("soar" , "height   ${it.animatedValue}")
                            layoutParams.height = it.animatedValue as Int
                            image.layoutParams = layoutParams

                        }
                        animXChild.addListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(animation: Animator?) {
                                testAdapter.data[temlist[index]].isVisiableImage = false
                            }

                            override fun onAnimationEnd(animation: Animator?) {
                                image.visibility = View.GONE
                                testAdapter.data.forEachIndexed { index, itemData ->
                                    if(!temlist.contains(index)){
                                        Log.e("soar" , "=== ${index}")
                                        testAdapter.data[index].isVisiableImage = false
                                    }

                                }
                                testAdapter.notifyDataSetChanged()

                            }

                            override fun onAnimationCancel(animation: Animator?) {
                                image.visibility = View.GONE
                                testAdapter.data.forEachIndexed { index, itemData ->
                                    if(!temlist.contains(index)){
                                        testAdapter.data[index].isVisiableImage = false
                                    }
                                }
                                testAdapter.notifyDataSetChanged()
                            }

                            override fun onAnimationRepeat(animation: Animator?) {
                            }

                        })
                        animXChild.duration = 500
                        animXChild.start()
                    }


                }
            }else{
                isImageVisible = true
                var height = resources.getDimensionPixelSize(R.dimen.height)
                var firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                var lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                var temlist = (firstVisibleItem..lastVisibleItem).toList()
                temlist.forEachIndexed { index, s ->
                    Log.e(
                        "soar",
                        "index  ${index}  ${temlist[index]}   ${layoutManager.getChildAt(index)}"
                    )
                    var view = layoutManager.findViewByPosition(temlist[index])
                    if (view != null) {
                        var image = view.findViewById<LinearLayout>(R.id.image)
                        var layoutParams = image.layoutParams
                        var animXChild = ValueAnimator.ofInt(0, height)
                        animXChild.addUpdateListener {
                            Log.e("soar" , "height   ${it.animatedValue}")
                            layoutParams.height = it.animatedValue as Int
                            image.layoutParams = layoutParams

                        }
                        animXChild.addListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(animation: Animator?) {
                                image.visibility = View.VISIBLE
                                testAdapter.data[temlist[index]].isVisiableImage = true
                            }

                            override fun onAnimationEnd(animation: Animator?) {
                                testAdapter.data.forEachIndexed { index, itemData ->
                                    if(!temlist.contains(index)){
                                        testAdapter.data[index].isVisiableImage = true
                                    }

                                }
                                testAdapter.notifyDataSetChanged()

                            }

                            override fun onAnimationCancel(animation: Animator?) {
                                testAdapter.data.forEachIndexed { index, itemData ->
                                    if(!temlist.contains(index)){
                                        testAdapter.data[index].isVisiableImage = true
                                    }

                                }
                                testAdapter.notifyDataSetChanged()
                            }

                            override fun onAnimationRepeat(animation: Animator?) {
                            }

                        })
                        animXChild.duration = 500
                        animXChild.start()
                    }


                }
            }
        }

        test.setOnClickListener {
            if(tem.visibility == View.VISIBLE){
                //动画



                var animXTem = ObjectAnimator.ofFloat(tem , "alpha",1f, 0f )
                animXTem.duration = 800
                animXTem.addListener(object :Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        tem.visibility = View.GONE
                        var wid = resources.getDimensionPixelSize(R.dimen.width_ext)
                        var itemwid = resources.getDimensionPixelSize(R.dimen.width)
                        var firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                        Log.e("soar" , "    (wid/ itemwid).toInt()  ${ (wid/ itemwid).toInt()}")
                        var last = layoutManager.findLastVisibleItemPosition() + (wid / itemwid).toInt() + 1
                        if(last > testData.size - 1 ){
                            last  = testData.size - 1
                        }

                        Log.e("soar" , "last  ${last}")
                        var offsetPosition = arrayListOf<Int>()
                        var temlist = (firstVisibleItem..last).toList()
                            temlist.forEachIndexed { index, s ->
                                Log.e("soar" , "index  ${index}  ${temlist[index]}   ${layoutManager.getChildAt(index)}")
                                var view = layoutManager.findViewByPosition(temlist[index])
                                if (view == null){
                                    offsetPosition.add(temlist[index])
                                    testData[temlist[index]].transX = wid
                                    testAdapter.notifyItemChanged(temlist[index])
                                }else{
                                    view.translationX = wid.toFloat()
                                }


                            }

                        list.post {
                            temlist.forEachIndexed { index, s ->
                                layoutManager.findViewByPosition(temlist[index])?.let {
                                    var animXChild = ObjectAnimator.ofFloat(
                                        it,
                                        "translationX",
                                        wid.toFloat(),
                                        0f
                                    )
                                    animXChild.duration = 300
                                if(index == temlist.size -1){
                                    animXChild.addListener(object :Animator.AnimatorListener{
                                        override fun onAnimationStart(animation: Animator?) {
                                        }

                                        override fun onAnimationEnd(animation: Animator?) {
                                            offsetPosition.forEachIndexed { index, i ->
                                                testData[i].transX = 0
                                                testAdapter.notifyItemChanged(i)
                                            }

                                        }

                                        override fun onAnimationCancel(animation: Animator?) {
                                            offsetPosition.forEachIndexed { index, i ->
                                                testData[i].transX = 0
                                                testAdapter.notifyItemChanged(i)
                                            }
                                        }

                                        override fun onAnimationRepeat(animation: Animator?) {
                                        }

                                    })
                                }
                                    animXChild.startDelay = (index * 100).toLong()
                                    animXChild.start()
                                }

                            }
                        }
                    }

                    override fun onAnimationCancel(animation: Animator?) {
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
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                })
                animXTem.start()










            }else{



                var wid = resources.getDimensionPixelSize(R.dimen.width_ext)
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
                                    tem.alpha = 1f
                                    var animatoerSet = AnimatorSet()
                                    var scaleX = ObjectAnimator.ofFloat(tem , "scaleX",0f,1f )
                                    var scaleY = ObjectAnimator.ofFloat(tem , "scaleY",0f,1f )
                                    animatoerSet.setDuration(500)
                                    animatoerSet.playTogether(scaleX,scaleY)
                                    animatoerSet.start()

                                    temlist.forEachIndexed { index, s ->
                                        layoutManager.findViewByPosition(temlist[index])?.let {
                                            it.translationX = 0f
                                        }

                                    }
                                }

                                override fun onAnimationCancel(animation: Animator?) {
                                    tem.visibility = View.VISIBLE
                                    tem.alpha = 1f
                                    var animatoerSet = AnimatorSet()
                                    var scaleX = ObjectAnimator.ofFloat(tem , "scaleX",0f,1f )
                                    var scaleY = ObjectAnimator.ofFloat(tem , "scaleY",0f,1f )
                                    animatoerSet.setDuration(500)
                                    animatoerSet.playTogether(scaleX,scaleY)
                                    animatoerSet.start()
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




}