package uz.itschool.elera.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.itschool.elera.R
import uz.itschool.elera.databinding.FragmentWelcomeBinding
import uz.itschool.elera.util.MyViewPagerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
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
        val binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val viewPager = binding.welcomeViewpager
        val adapter = MyViewPagerAdapter(
            childFragmentManager,
            lifecycle,
            arrayListOf(
                Frag1Fragment.newInstance(
                    R.drawable.im1,
                    "We provide the best learning courses & great mentors!"
                ),
                Frag1Fragment.newInstance(
                    R.drawable.im2,
                    "Learn anytime and anywhere easily and conventionally"
                ),
                Frag1Fragment.newInstance(
                    R.drawable.im3,
                    "Let's improve your skills together with Elera right now!"
                )
            )
        )
        viewPager.adapter = adapter
        binding.welcomeNextButton.setOnClickListener {
            if (viewPager.currentItem == 2){
                findNavController().navigate(R.id.action_welcomeFragment_to_signInFragment)
            }else{
                viewPager.setCurrentItem(viewPager.currentItem+1, true)
            }
        }

        return binding.root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}