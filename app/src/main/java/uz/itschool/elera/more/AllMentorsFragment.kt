package uz.itschool.elera.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.elera.R
import uz.itschool.elera.databinding.FragmentAllMentorsBinding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.Mentor

class AllMentorsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val api = API.newInstance(requireContext())
        val animHelper = AnimHelper.newInstance()
        val binding = FragmentAllMentorsBinding.inflate(inflater, container, false)
        binding.allMentorsBackArrow.setOnClickListener {
            animHelper.animate(requireContext(), binding.allMentorsBackArrow, R.anim.button_press_anim, object : AnimHelper.EndAction{
                override fun endAction() {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            })
        }

        binding.allMentorsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.allMentorsRecycler.adapter = MentorsRecyclerAdapter2(requireContext(), api, object : MentorsRecyclerAdapter2.ItemClick{
            override fun onItemClick(mentor: Mentor) {
                val bundle = Bundle()
                bundle.putSerializable("param1", mentor)
                findNavController().navigate(R.id.action_allMentorsFragment_to_mentorFragment, bundle)
            }
        }, animHelper)

        return binding.root
    }
}