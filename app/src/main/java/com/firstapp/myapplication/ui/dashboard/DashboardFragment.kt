package com.firstapp.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.firstapp.myapplication.R
import com.firstapp.myapplication.lstStudents
import com.firstapp.myapplication.model.Students

class DashboardFragment : Fragment() {

    //    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var etUrl: EditText
    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var etAddress: EditText
    private lateinit var btnSave: Button
//    var lstStudents:ArrayList<Students> = arrayListOf()
    var gender = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        dashboardViewModel =
//                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        etUrl = root.findViewById(R.id.etUrl)
        etName = root.findViewById(R.id.etName)
        etAge = root.findViewById(R.id.etAge)
        radioGroup = root.findViewById(R.id.radioGroup)
        etAddress = root.findViewById(R.id.etAddress)
        btnSave = root.findViewById(R.id.btnSave)

        radioGroupListener()

        btnSave.setOnClickListener {
            parselData()
            Toast.makeText(context,"Student added!!",Toast.LENGTH_LONG).show()

        }


        return root

    }

    private fun parselData() {
        val image = etUrl.text.toString()
        val name = etName.text.toString()
        val age = etAge.text.toString().toInt()
        val address = etAddress.text.toString()
        lstStudents.add(Students(image, name, age, gender, address))

    }


    private fun radioGroupListener() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbMale -> {
                    gender = "Male"

                }
                R.id.rbFemale -> {
                    gender = "Female"
                }
                R.id.rbOthers -> {
                    gender = "Others"
                }

            }
        }
        radioGroup.check(R.id.rbFemale)
    }


}