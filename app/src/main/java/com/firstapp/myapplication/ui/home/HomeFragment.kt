package com.firstapp.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myapplication.Adapter.StudentAdapter
import com.firstapp.myapplication.R
import com.firstapp.myapplication.SwipeToDelete
import com.firstapp.myapplication.lstStudents

import com.firstapp.myapplication.model.Students

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var recyclerView: RecyclerView
//    private var lstStudents = ArrayList<Students>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
//        loadStudents()
        val adapter = StudentAdapter(lstStudents, container!!.context)
        recyclerView.layoutManager = LinearLayoutManager(container.context)
        recyclerView.adapter = adapter

        //delete to swipe Right
        val item = object : SwipeToDelete(container.context, 0, ItemTouchHelper.RIGHT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.del(viewHolder.adapterPosition)
            }

        }
        val itemTouchHelperPager2 = ItemTouchHelper(item)
        itemTouchHelperPager2.attachToRecyclerView(recyclerView)
        //delete to swipe left
        val itemPager2 = object : SwipeToDelete(container.context, 0, ItemTouchHelper.LEFT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.del(viewHolder.adapterPosition)
            }

        }
        val itemTouchHelper = ItemTouchHelper(itemPager2)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return root
    }


}