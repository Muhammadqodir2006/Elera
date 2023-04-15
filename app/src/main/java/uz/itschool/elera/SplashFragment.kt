package uz.itschool.elera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.itschool.elera.databinding.FragmentSplashBinding
import uz.itschool.elera.util.API

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        var animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.logo_anim)
        binding.logo.startAnimation(animation)

        animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_anim)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                check()
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.loadingIcon.startAnimation(animation)

        return binding.root
    }

    private fun check() {
        val api = API.newInstance(requireContext())
        api.hasData()

        findNavController().navigate(R.id.action_splashFragment_to_bodyFragment)
    }

}