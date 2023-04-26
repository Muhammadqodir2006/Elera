package uz.itschool.elera.more

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.itschool.elera.R
import uz.itschool.elera.databinding.MentorItem2Binding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.Mentor

class MentorsRecyclerAdapter2(val context: Context, val api: API, val itemClick: ItemClick, val animHelper: AnimHelper): RecyclerView.Adapter<MentorsRecyclerAdapter2.ViewHolder>() {
    var mentors = api.getMentors()

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val imagE = itemView.findViewById<ImageView>(R.id.mentor_item2_image)
        val namE = itemView.findViewById<TextView>(R.id.mentor_item2_name)
        val job = itemView.findViewById<TextView>(R.id.mentor_item2_job)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MentorItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun getItemCount(): Int {
        return mentors.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mentor = mentors[position]
        holder.imagE.load(mentor.image)
        holder.job.text = mentor.job
        holder.namE.text = mentor.firstName + " " + mentor.lastName
        holder.itemView.setOnClickListener {
            animHelper.animate(context, holder.itemView, R.anim.button_press_anim, object : AnimHelper.EndAction{
                override fun endAction() {
                    itemClick.onItemClick(mentor)
                }
            })
        }
    }
    interface ItemClick{
        fun onItemClick(mentor: Mentor)
    }
}