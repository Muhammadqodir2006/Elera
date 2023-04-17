package uz.itschool.elera.mentor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.elera.databinding.FragmentMentorreviewsBinding
import uz.itschool.elera.home.ReviewRecyclerAdapter
import uz.itschool.elera.util.API
import uz.itschool.elera.util.Mentor

private const val ARG_PARAM1 = "param1"

class MentorReviewsFragment : Fragment() {
    private var mentor: Mentor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mentor = it.getSerializable(ARG_PARAM1) as Mentor
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMentorreviewsBinding.inflate(inflater, container, false)
        val api = API.newInstance(requireContext())

        val adapter = ReviewRecyclerAdapter(api.getReviews(mentor!!))
        binding.mentorReviewRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.mentorReviewRecycler.adapter = adapter
//        if (adadpter.reviews.size == 0){
//            val view = ImageView(requireContext())
//            view.setImageResource(R.drawable.nothing_yet)
//            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
//            childFragmentManager.beginTransaction()
//             .add(R.id.mentor_review_recycler, NothingFragment()).commit()
//        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Mentor) =
            MentorReviewsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}