package kr.ac.hallym.prac07_jetpack_homework.Adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.ac.hallym.prac07_jetpack_homework.Fragment.MainFragment
import kr.ac.hallym.prac07_jetpack_homework.Fragment.RightFragment

/**
 * 오른쪽 슬라이드
 */
class MyFragmentViewAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity){
    val fragments : List<Fragment>
    init {
        fragments = listOf(MainFragment(),RightFragment())
        Log.d("namjung","fragments size : ${fragments.size}" )
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}