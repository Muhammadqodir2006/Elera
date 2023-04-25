package uz.itschool.elera.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.itschool.elera.R
import uz.itschool.elera.databinding.FragmentSignInBinding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val api = API.newInstance(requireContext())
        val animHelper = AnimHelper.newInstance()
        val binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.signInButton.setOnClickListener {
            val email = binding.signInEmailEditText.text.toString().trim()
            val password = binding.signInPasswordEditText.text.toString().trim()

            val user = api.getUser(email)
            if (email == "" || password == ""){
                return@setOnClickListener
            }
            if (user == null) {
                Toast.makeText(requireContext(), "Username or email incorrect", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (user.password != password) {
                Toast.makeText(requireContext(), "Password incorrect", Toast.LENGTH_SHORT).show()
                binding.signInPasswordEditText.text?.clear()
                return@setOnClickListener
            }
            api.setLoggedInUser(user)
            val bundle = Bundle()
            bundle.putSerializable("param1", user)
            findNavController().navigate(R.id.action_signInFragment_to_bodyFragment, bundle)
        }


        binding.signInBackArrow.setOnClickListener {
            animHelper.animate(
                requireContext(),
                binding.signInBackArrow,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }

                })
        }


        binding.signInSignUpText.setOnClickListener {
            animHelper.animate(
                requireContext(),
                binding.signInSignUpText,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
                    }

                })
        }


        return binding.root
    }
}