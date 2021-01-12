package com.firstapp.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
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
        val studentImage: CircleImageView
        val tvStudentName: TextView
        val tvStudentAge: TextView
        val gender:TextView
        val tvAddress: TextView

        init {
            studentImage = view.findViewById(R.id.studentImage)
            tvStudentName = view.findViewById(R.id.tvStudentName)
            tvStudentAge = view.findViewById(R.id.tvStudentAge)
            gender=view.findViewById(R.id.tvGender)
            tvAddress = view.findViewById(R.id.tvAddress)
        }
    }
    //for delete
    fun del(position: Int){
        lstStudents.removeAt(position)
        notifyDataSetChanged()
        Toast.makeText(context,"Student removed",Toast.LENGTH_SHORT).show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_detail_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = lstStudents[position]
        holder.tvStudentName.text = student.studentName
        holder.tvStudentAge.text = student.studentAge.toString()
        holder.gender.text=student.gender
        holder.tvAddress.text = student.studentAddress

        //image load with glide library
        Glide.with(context)
            .load(student.studentImage)
            .into(holder.studentImage)

        holder.studentImage.setOnClickListener{
//            val intent=intent(context,DashboardFragment::Class.java)
//            Toast.makeText(context,"student details: \n ${student.studentName} \n ${}")
        }

    }

    override fun getItemCount(): Int {
        return lstStudents.size
    }


}

