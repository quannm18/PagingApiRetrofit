package com.example.clonepaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clonepaging.view.adapter.MyPagingAdapter
import com.example.clonepaging.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private val rcvMain: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcvMain) }
    private val rcvAdapter: MyPagingAdapter = MyPagingAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        rcvMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rcvAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                rcvAdapter.submitData(it)
            }
        }
    }
}