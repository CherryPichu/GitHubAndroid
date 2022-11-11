package kr.ac.hallym.prac07_jetpack_homework.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.prac07_jetpack_homework.Adapter.MyAdapter

import kr.ac.hallym.prac07_jetpack_homework.MainActivity
import kr.ac.hallym.prac07_jetpack_homework.R
import kr.ac.hallym.prac07_jetpack_homework.databinding.ActivityMainBinding
import kr.ac.hallym.prac07_jetpack_homework.databinding.FragmentMainBinding
import java.security.AccessController.getContext

class MainFragment() : Fragment() {
//    val binding by lazy {
//        FragmentMainBinding.inflate(layoutInflater)
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView : ViewGroup = inflater.inflate(R.layout.fragment_main, container, true) as ViewGroup
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        val contents = mutableListOf<String>("Moblie Programming", "Computer Graphics", "Game Programming")
        val contents_sub = mutableListOf<String>("2022년 2학기", " 2022년 1학기", " 2022년 2학기")

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =  LinearLayoutManager( requireActivity()) // requireActivity(): 현재 활상화중인 Activity 가져오기
        recyclerView.adapter = MyAdapter(requireActivity(), contents, contents_sub)
        recyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))

        return  rootView
//        return view
    }




}