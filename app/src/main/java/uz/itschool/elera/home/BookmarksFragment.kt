package uz.itschool.elera.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.elera.R
import uz.itschool.elera.databinding.FragmentBookmarksBinding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.Course

class BookmarksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val animHelper = AnimHelper.newInstance()
        val api = API.newInstance(requireContext())
        val binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        binding.bookmarksBackArrow.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_press_anim)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationEnd(p0: Animation?) {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            })
            binding.bookmarksBackArrow.startAnimation(animation)
        }
        binding.bookmarksReycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.bookmarksReycler.adapter = CourseRecyclerAdapter(api.getBookmarks(), api, animHelper, requireContext(), object : CourseRecyclerAdapter.OnClick{
            override fun onPressed(course: Course) {
                val bundle = Bundle()
                bundle.putSerializable("param1", course)
                findNavController().navigate(R.id.action_bodyFragment_to_courseDetailFragment, bundle)
            }

        })

        binding.bookmarksMore.setOnClickListener {
            animHelper.animate(requireContext(), binding.bookmarksMore, R.anim.button_press_anim)
        }
        return binding.root
    }

}