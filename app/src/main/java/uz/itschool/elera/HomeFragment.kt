package uz.itschool.elera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.elera.databinding.FragmentHomeBinding
import uz.itschool.elera.home.CourseRecyclerAdapter
import uz.itschool.elera.home.MentorsRecyclerAdapter
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_press_anim)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationEnd(p0: Animation?) {
                    findNavController().navigate(R.id.action_bodyFragment_to_bookmarksFragment)
                }
            })
            binding.bookMarkIv.startAnimation(animation)
        }
        binding.notificationsIv.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_press_anim)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationEnd(p0: Animation?) {
                    findNavController().navigate(R.id.action_bodyFragment_to_notificationsFragment)
                }
            })
            binding.notificationsIv.startAnimation(animation)
        }
        binding.offerView.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_press_anim)
            binding.offerView.startAnimation(animation)
        }
        binding.mentorsSeeAll.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_press_anim)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationEnd(p0: Animation?) {

                }
            })
            binding.mentorsSeeAll.startAnimation(animation)
        }
        binding.coursesSeeAll.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_press_anim)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationEnd(p0: Animation?) {

                }
            })
            binding.coursesSeeAll.startAnimation(animation)
        }

        binding.mentorsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.mentorsRecyclerView.adapter = MentorsRecyclerAdapter(api.getMentors())


        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.coursesRecyclerView.adapter = CourseRecyclerAdapter(api.getCourses(), api, animHelper, requireContext())


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
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