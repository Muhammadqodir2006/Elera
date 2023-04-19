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
import coil.api.load
import uz.itschool.elera.databinding.FragmentHomeBinding
import uz.itschool.elera.home.CategoryRecyclerAdapter
import uz.itschool.elera.home.CourseRecyclerAdapter
import uz.itschool.elera.home.MentorsRecyclerAdapter
import uz.itschool.elera.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as User
        }
    }

    @SuppressLint("SetTextI18n")
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
                override fun onPressed(mentor: Mentor) {
                    val bundle = Bundle()
                    bundle.putSerializable("param1", mentor)
                    findNavController().navigate(R.id.action_bodyFragment_to_mentorFragment, bundle)
                }

            })


        binding.coursesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val courseAdapter =
            CourseRecyclerAdapter(api.getCourses(), api, animHelper, requireContext(), object : CourseRecyclerAdapter.OnClick{
                override fun onPressed(course: Course) {
                    val bundle = Bundle()
                    bundle.putSerializable("param1", course)
                    findNavController().navigate(R.id.action_bodyFragment_to_courseDetailFragment, bundle)
                }

            })
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

        binding.homeUserImageIv.load(param1!!.image)
        binding.homeUserName.text = param1!!.firstName + " " + param1!!.lastName

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: User) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}