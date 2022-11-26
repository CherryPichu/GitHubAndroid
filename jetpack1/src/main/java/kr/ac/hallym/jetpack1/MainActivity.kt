package kr.ac.hallym.jetpack1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import kr.ac.hallym.jetpack1.databinding.ActivityMainBinding
import kr.ac.hallym.jetpack1.databinding.FragmentMainBinding
import kr.ac.hallym.prac07_jetpack_homework.Adapter.MyFragmentViewAdapter

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val mainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /**
         * 햄버거 아이콘
         */
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.draw_open , R.string.draw_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //햄버거 아이콘 만듬
        toggle.syncState()




        /**
         * 오른쪽 슬라이드
         */
        binding.viewPager.adapter = MyFragmentViewAdapter(this)



    }



    /**
     * 햄버거 아이콘
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }




    /**
     * 검색 아이콘 및 기능.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)


        val menuItem = menu?.findItem(R.id.serach_icon)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("namjung", "onQueryTextChange")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("namjung", "onQueryTextSubmit")
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}