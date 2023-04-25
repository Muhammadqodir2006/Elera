package uz.itschool.elera.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.elera.R
import uz.itschool.elera.databinding.FragmentAllCoursesBinding
import uz.itschool.elera.home.CourseRecyclerAdapter
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.Course

class AllCoursesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val api = API.newInstance(requireContext())
        val animHelper = AnimHelper.newInstance()
        val binding = FragmentAllCoursesBinding.inflate(inflater, container, false)
        binding.allCoursesBackArrow.setOnClickListener {
            animHelper.animate(requireContext(), binding.allCoursesBackArrow, R.anim.button_press_anim, object : AnimHelper.EndAction{
                override fun endAction() {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            })
        }
        binding.allCoursesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.allCoursesRecycler.adapter = CourseRecyclerAdapter(api.getCourses(), api, animHelper, requireContext(), object : CourseRecyclerAdapter.OnClick{
            override fun onPressed(course: Course) {
                val bundle = Bundle()
                bundle.putSerializable("param1", course)
                findNavController().navigate(R.id.action_allCoursesFragment_to_courseDetailFragment, bundle)
            }

        })
        return binding.root
    }

}