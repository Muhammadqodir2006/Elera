package uz.itschool.elera.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.itschool.elera.R
import uz.itschool.elera.databinding.HomeCourseItemBinding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.Course

class CourseRecyclerAdapter(
    var courses: ArrayList<Course>,
    private val api: API,
    private val animHelper: AnimHelper,
    val context: Context,
    private val onPressed: OnClick
) :
    RecyclerView.Adapter<CourseRecyclerAdapter.MyViewHolder>() {
    private val reviews = api.getReviews()
    private val bookmarks = api.getBookmarks()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.home_course_item_image)
        val category: TextView = itemView.findViewById(R.id.home_course_item_category)
        val namE: TextView = itemView.findViewById(R.id.home_course_item_name)
        val price: TextView = itemView.findViewById(R.id.course_detail_price)
        val oldPrice: TextView = itemView.findViewById(R.id.course_detail_old_price)
        val rating: TextView = itemView.findViewById(R.id.course_detail_rating)
        val studentNum: TextView = itemView.findViewById(R.id.course_detail_review_count)
        val bookmark: ImageView = itemView.findViewById(R.id.home_course_item_bookmark)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            HomeCourseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val animation = AnimationUtils.loadAnimation(context, R.anim.all_appear)
        holder.itemView.startAnimation(animation)

        val course = courses[position]
        holder.image.load(course.image) {
            placeholder(R.drawable.img)
            error(R.drawable.no_internet)
        }
        holder.category.text = course.category.namE
        holder.namE.text = course.name
        holder.price.text = "${course.prices.last()} $"
        holder.rating.text = api.getRating(course, reviews).toString().substring(0, 3)
        holder.studentNum.text = api.getStudentsCount(course).toString()
        holder.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        if (course.prices.size > 1) {
            holder.oldPrice.text = course.prices[course.prices.lastIndex - 1].toString() + " $"
            holder.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.oldPrice.visibility = View.INVISIBLE
        }

        if (bookmarks.contains(course)) {
            holder.bookmark.setImageResource(R.drawable.bookmark_selected)
        } else {
            holder.bookmark.setImageResource(R.drawable.bookmark)
        }
        holder.bookmark.setOnClickListener {
            if (api.getUser(api.getLoggedInUser()!!.email)!!.bookMarks.contains(course)) {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Do you want to remove the course from bookmarks?")

                builder.setPositiveButton("Yes") { dialog, which ->
                    animHelper.animate(
                        context,
                        holder.bookmark,
                        R.anim.unbookmarked_anim_part1,
                        object : AnimHelper.EndAction {
                            override fun endAction() {
                                holder.bookmark.setImageResource(R.drawable.bookmark)
                                val anim = AnimationUtils.loadAnimation(
                                    context,
                                    R.anim.unbookmarked_anim_part2
                                )
                                holder.bookmark.startAnimation(anim)
                                api.updateBookmarks(course)
                            }
                        })
                }

                builder.setNegativeButton("No") { dialog, which ->
                }
                builder.show()
                return@setOnClickListener
            }
            api.updateBookmarks(course)
            animHelper.animate(
                context,
                holder.bookmark,
                R.anim.bookmarked_anim_part1,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        holder.bookmark.setImageResource(R.drawable.bookmark_selected)
                        val anim =
                            AnimationUtils.loadAnimation(context, R.anim.bookmarked_anim_part2)
                        holder.bookmark.startAnimation(anim)
                    }

                })

        }
        holder.itemView.setOnClickListener {
            animHelper.animate(
                context,
                holder.itemView,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        onPressed.onPressed(course)
                    }

                })
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    interface OnClick {
        fun onPressed(course: Course)
    }

}