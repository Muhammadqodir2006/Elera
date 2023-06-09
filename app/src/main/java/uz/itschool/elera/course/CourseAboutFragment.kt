package uz.itschool.elera.course

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import uz.itschool.elera.databinding.FragmentCourseAboutBinding
import uz.itschool.elera.util.Course

private const val ARG_PARAM1 = "param1"
class CourseAboutFragment : Fragment() {
    private var param1: Course? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Course
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCourseAboutBinding.inflate(inflater, container, false)
        binding.courseDetailAboutCourse.text = param1!!.about
        binding.courseDetailEnrollButtton.text = "Enroll course - ${param1!!.prices.last()}$"
        binding.mentorImage.load(param1!!.mentor.image)
        binding.courseDetailMentorJob.text = param1!!.mentor.job
        binding.courseDetailMentorName.text = param1!!.mentor.firstName + " " + param1!!.mentor.firstName
        Log.d("TAG", "onCreateView: ")
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Course) =
            CourseAboutFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}