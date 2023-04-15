package uz.itschool.elera.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.itschool.elera.databinding.HomeCourseItemBinding
import uz.itschool.elera.util.Course

class CourseRecyclerAdapter(var courses : ArrayList<Course>): RecyclerView.Adapter<CourseRecyclerAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(HomeCourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return courses.size
    }

}