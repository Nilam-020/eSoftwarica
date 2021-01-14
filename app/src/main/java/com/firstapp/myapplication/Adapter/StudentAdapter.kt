package com.firstapp.myapplication.Adapter

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.myapplication.R
import com.firstapp.myapplication.model.Students
import com.firstapp.myapplication.ui.dashboard.DashboardFragment
import de.hdodenhof.circleimageview.CircleImageView

class StudentAdapter(
    val lstStudents: ArrayList<Students>,
    val context: Context
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvEdit: TextView //delete from home fragment
        val studentImage: CircleImageView
        val tvStudentName: TextView
        val tvStudentAge: TextView
        val gender: TextView
        val tvAddress: TextView

        init {
            studentImage = view.findViewById(R.id.studentImage)
            tvStudentName = view.findViewById(R.id.tvStudentName)
            tvStudentAge = view.findViewById(R.id.tvStudentAge)
            gender = view.findViewById(R.id.tvGender)
            tvAddress = view.findViewById(R.id.tvAddress)
            tvEdit=view.findViewById(R.id.tvEdit)
        }
    }

    //for delete
    var deletedMovie = null
    fun del(position: Int) {


        lstStudents.removeAt(position)
        notifyDataSetChanged()


        Toast.makeText(context, "Student removed", Toast.LENGTH_SHORT).show()
    }

    //update

//    var btnCancel: Button = dialog.findViewById(R.id.btnCancel)


//    btnCancel.setOnClickListener
//    {
//        dialog.cancel()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_detail_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = lstStudents[position]
        holder.tvStudentName.text = student.studentName
        holder.tvStudentAge.text = student.studentAge.toString()
        holder.gender.text = student.gender
        holder.tvAddress.text = student.studentAddress

        //image load with glide library
        Glide.with(context)
            .load(student.studentImage)
            .into(holder.studentImage)

        holder.studentImage.setOnClickListener {
//            val intent=intent(context,DashboardFragment::Class.java)
            Toast.makeText(context,"student details: \n ${student.studentName}",Toast.LENGTH_LONG).show()
        }
        //update code
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.update_student_detail)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        var etName: EditText = dialog.findViewById(R.id.etName)
        var etAge: EditText = dialog.findViewById(R.id.etAge)
        var etAddress: EditText = dialog.findViewById(R.id.etAddress)
        var etUrl: EditText = dialog.findViewById(R.id.etUrl)
        var radioGroup: RadioGroup = dialog.findViewById(R.id.radioGroup)
        var btnUpdate: Button = dialog.findViewById(R.id.btnUpdate)
        var gender = ""

        holder.tvEdit.setOnClickListener{
            etUrl.setText(student.studentImage)
            etName.setText(student.studentName.toString())
            etAge.setText(student.studentAge.toString())
            etAddress.setText(student.studentAddress)
            when (student.gender) {
                "Male" -> radioGroup.check(R.id.rbMale)
                "Female" -> radioGroup.check(R.id.rbFemale)
                "Others" -> radioGroup.check(R.id.rbOthers)
            }
            dialog.show()
        }
        radioGroup.setOnCheckedChangeListener{
                group, checkedId ->
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
        btnUpdate.setOnClickListener{
            if (TextUtils.isEmpty(etName.text)) {
                etName.error = "Enter Firstname"
                etName.requestFocus()
            } else if (TextUtils.isEmpty(etAge.text)) {
                etAge.error = "Enter Age"
                etAge.requestFocus()
            } else if (TextUtils.isEmpty(etAddress.text)) {
                etAddress.error = "Enter Address"
                etAddress.requestFocus()
            } else if (TextUtils.isEmpty(etUrl.text)) {
                etUrl.error = "Enter Profile image"
                etUrl.requestFocus()
            } else {
                lstStudents[position].studentName = etName.text.toString()
                lstStudents[position].studentAddress = etAddress.text.toString()
                lstStudents[position].studentImage = etUrl.text.toString()
                lstStudents[position].studentAge = etAge.text.toString().toInt()
                lstStudents[position].gender = gender
                notifyDataSetChanged()
                dialog.cancel()
            }
        }

    }

    override fun getItemCount(): Int {
        return lstStudents.size
    }


}

