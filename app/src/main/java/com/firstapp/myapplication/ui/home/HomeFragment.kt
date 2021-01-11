package com.firstapp.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myapplication.Adapter.StudentAdapter
import com.firstapp.myapplication.R
import com.firstapp.myapplication.model.Students

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private  var lstStudents=ArrayList<Students>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        loadStudents()
        val adapter = StudentAdapter(lstStudents, container!!.context)
        recyclerView.layoutManager = LinearLayoutManager(container!!.context)
        recyclerView.adapter = adapter
        return root
    }

    private fun loadStudents() {
        lstStudents.add(
            Students(
                "https://cdn.pixabay.com/photo/2016/01/19/18/04/man-1150058__340.jpg",
                "Sagar Mishra",
                21,
                "Kathmandu"
            )
        )
        lstStudents.add(
            Students(
                "https://cdn.pixabay.com/photo/2016/03/09/10/23/model-1246028__340.jpg",
                "Abishek Basent",
                21,
                "Kathmandu"
            )
        )
        lstStudents.add(
            Students(
                "https://cdn.pixabay.com/photo/2015/07/31/15/01/man-869215__340.jpg",
                "Ashish Lal Pandey",
                21,
                "Kathmandu"
            )
        )
    }


}