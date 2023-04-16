package uz.itschool.elera

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.elera.databinding.FragmentHomeBinding
import uz.itschool.elera.home.CategoryRecyclerAdapter
import uz.itschool.elera.home.CourseRecyclerAdapter
import uz.itschool.elera.home.MentorsRecyclerAdapter
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.Category

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val animHelper = AnimHelper.newInstance()
        val api = API.newInstance(requireContext())
        val binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.bookMarkIv.setOnClickListener {
            animHelper.animate(
                requireContext(),
                binding.bookMarkIv,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        findNavController().navigate(R.id.action_bodyFragment_to_bookmarksFragment)
                    }
                })
        }
        binding.notificationsIv.setOnClickListener {
            animHelper.animate(
                requireContext(),
                binding.notificationsIv,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        findNavController().navigate(R.id.action_bodyFragment_to_notificationsFragment)
                    }
                })
        }
        binding.offerView.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_press_anim)
            binding.offerView.startAnimation(animation)
        }

        binding.mentorsSeeAll.setOnClickListener {
            animHelper.animate(
                requireContext(),
                binding.mentorsSeeAll,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        // TODO: go to all mentors
                    }
                })
        }
        binding.coursesSeeAll.setOnClickListener {
            animHelper.animate(
                requireContext(),
                binding.coursesSeeAll,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        // TODO: go to all courses
                    }
                })
        }




        binding.mentorsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.mentorsRecyclerView.adapter =
            MentorsRecyclerAdapter(api.getMentors(), animHelper, requireContext(), object :MentorsRecyclerAdapter.OnPressed{
                override fun onPressed() {
                    findNavController().navigate(R.id.action_bodyFragment_to_mentorFragment)
                }

            })


        binding.coursesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val courseAdapter =
            CourseRecyclerAdapter(api.getCourses(), api, animHelper, requireContext())
        binding.coursesRecyclerView.adapter = courseAdapter



        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter =
            CategoryRecyclerAdapter(object : CategoryRecyclerAdapter.OnCategoryChanged {
                @SuppressLint("NotifyDataSetChanged")
                override fun onCategoryChanged(category: Category?) {
                    val new = if (category == null) {
                        api.getCourses()
                    } else {
                        api.getCourses(category)
                    }
                    courseAdapter.courses = new
                    courseAdapter.notifyDataSetChanged()
                }
            })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}