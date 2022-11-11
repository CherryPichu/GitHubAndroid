package kr.ac.hallym.prac07_jetpack_homework.Adapter

import android.content.Context
import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.NonDisposableHandle.parent
import kr.ac.hallym.prac07_jetpack_homework.R
import kr.ac.hallym.prac07_jetpack_homework.databinding.ActivityMainBinding
import kr.ac.hallym.prac07_jetpack_homework.databinding.FragmentMainBinding
import kr.ac.hallym.prac07_jetpack_homework.databinding.ItemRecyclerviewBinding



/**
 * @param (this, binding, )
 */
class MyAdapter (val context : Context,  val contents : MutableList<String> , val contents_sub : MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    /**
     * 싱글톤 패턴
     */
//    companion object{
//        @Volatile
//        private var instance : MyAdapter? = null
//
//        fun getInstance(context : Context, contents : MutableList<String> , contents_sub : MutableList<String>) =
//            instance ?: synchronized(MyAdapter::class.java){
//                instance ?: MyAdapter(context, contents, contents_sub).also{
//                    instance = it
//                }
//            }
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = MyViewHolder(
        ItemRecyclerviewBinding.inflate(LayoutInflater.from( parent.context ), parent, false))

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return LayoutInflater.from( context).inflate(R.layout.item_recyclerview, parent, false) as ViewHolder
//    }

    override fun getItemCount(): Int {
        Log.d("namjung", "init contents size : ${contents.size}")
        return contents.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("namjung", "onBindViewHolder : $position")
        val binding = (holder as MyViewHolder).binding
        // 뷰에 데이터 출력
        binding.itemData.text = contents[position]
        binding.itemSubdata.text = contents_sub[position]
        // 부에 이벤트 추가
        binding.itemRoot.setOnClickListener{
            Log.d("namjung", "item root click : $position")
        }
    }

}

class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

