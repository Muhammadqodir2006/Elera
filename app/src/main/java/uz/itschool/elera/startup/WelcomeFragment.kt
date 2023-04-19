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

class WelcomeFragment : Fragment() {


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

}