package uz.itschool.elera.more

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.elera.R
import uz.itschool.elera.databinding.FragmentSearchBinding
import uz.itschool.elera.home.CourseRecyclerAdapter
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.Course
import uz.itschool.elera.util.Mentor

class SearchFragment : Fragment() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var cur = 0
        val api = API.newInstance(requireContext())
        val animHelper = AnimHelper.newInstance()
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchCancelButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        val courseAdapter = CourseRecyclerAdapter(api.getCourses(), api, animHelper, requireContext(), object : CourseRecyclerAdapter.OnClick{
            override fun onPressed(course: Course) {
                val bundle = Bundle()
                bundle.putSerializable("param1", course)
                findNavController().navigate(R.id.action_searchFragment_to_courseDetailFragment, bundle)
            }

        })
        val mentorAdapter = MentorsRecyclerAdapter2(requireContext(), api, object : MentorsRecyclerAdapter2.ItemClick{
            override fun onItemClick(mentor: Mentor) {
                val bundle = Bundle()
                bundle.putSerializable("param1", mentor)
                findNavController().navigate(R.id.action_searchFragment_to_mentorFragment, bundle)
            }
        }, animHelper)
        binding.searchEditText.requestFocus()

        binding.searchEditText.addTextChangedListener {
            val text = binding.searchEditText.text
                if (cur == 0){
                    courseAdapter.courses = api.searchCourses(text.toString().trim())
                    courseAdapter.notifyDataSetChanged()
                }else{
                    mentorAdapter.mentors = api.searchMentors(text.toString().trim())
                    mentorAdapter.notifyDataSetChanged()
                }

        }
        binding.searchMentors.setOnClickListener {
            cur = 1
            binding.searchCourses.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.searchMentors.setCardBackgroundColor(resources.getColor(R.color.primary))
            binding.searchMentorsText.setTextColor(resources.getColor(R.color.white))
            binding.searchCoursesText.setTextColor(resources.getColor(R.color.black))
            binding.searchRecycler.adapter = mentorAdapter
        }
        binding.searchCourses.setOnClickListener {
            cur = 0
            binding.searchCourses.setCardBackgroundColor(resources.getColor(R.color.primary))
            binding.searchMentors.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.searchMentorsText.setTextColor(resources.getColor(R.color.black))
            binding.searchCoursesText.setTextColor(resources.getColor(R.color.white))

            binding.searchRecycler.adapter = courseAdapter
        }

        binding.searchRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.searchRecycler.adapter = courseAdapter

        return binding.root
    }
}