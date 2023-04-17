package uz.itschool.elera.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.google.android.material.tabs.TabLayoutMediator
import uz.itschool.elera.R
import uz.itschool.elera.course.CourseAboutFragment
import uz.itschool.elera.course.CourseReviewsFragment
import uz.itschool.elera.databinding.FragmentCourseDEtailBinding
import uz.itschool.elera.mentor.MentorCoursesFragment
import uz.itschool.elera.mentor.MentorReviewsFragment
import uz.itschool.elera.mentor.MentorStudentsFragment
import uz.itschool.elera.util.API
import uz.itschool.elera.util.Course
import uz.itschool.elera.util.MyViewPagerAdapter

private const val ARG_PARAM1 = "param1"

class CourseDetailFragment : Fragment() {
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
        val api = API.newInstance(requireContext())
        val binding = FragmentCourseDEtailBinding.inflate(inflater, container, false)
        val hour = param1!!.durationHour.toString() + (param1!!.durationMin/60).toString().substring(1,3)

        binding.courseDetailCategory.text = param1!!.category.namE
        binding.courseDetailHours.text = hour
        binding.courseDetailImage.load(param1!!.image)
        binding.mentorName.text = param1!!.name
        binding.courseDetailRating.text = api.getRating(param1!!, api.getReviews()).toString().substring(0, 3)
        binding.bookmarksBackArrow.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        if (!param1!!.hasCertificate){
            binding.courseDetailGivesCertificate.visibility = View.INVISIBLE
        }
        binding.courseDetailReviewCount.text = "(${api.getReviews(param1!!).size} reviews)"
        binding.courseDetailPrice.text = "$" + param1!!.prices.last().toString()
        if (param1!!.prices.size > 1) {
            binding.courseDetailOldPrice.text = "$" + param1!!.prices[param1!!.prices.size-2].toString()
        } else {
            binding.courseDetailOldPrice.visibility = View.INVISIBLE
        }

        val viewPager = binding.courseViewPager
        viewPager.adapter = MyViewPagerAdapter(parentFragmentManager, lifecycle, arrayListOf(
            CourseAboutFragment.newInstance(param1!!), CourseReviewsFragment.newInstance(param1!!)))

        val tabLayout = binding.courseTabLayout

        TabLayoutMediator(tabLayout, viewPager){tab, position->
            when (position){
                0->{
                    tab.text = "About"
                }
                else->{
                    tab.text = "Reviews"
                }
            }
        }.attach()

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Course) =
            CourseDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}