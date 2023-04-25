package uz.itschool.elera.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.itschool.elera.R
import uz.itschool.elera.databinding.FragmentSignUpBinding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.AnimHelper
import uz.itschool.elera.util.User

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class SignUpFragment : Fragment() {
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
        val api = API.newInstance(requireContext())
        val animHelper = AnimHelper.newInstance()
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.signUpBackArrow.setOnClickListener {
            animHelper.animate(requireContext(), binding.signUpBackArrow, R.anim.button_press_anim, object : AnimHelper.EndAction{
                override fun endAction() {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }

            })
        }
        binding.signUpButton.setOnClickListener {
            val email = binding.signUpEmailEditText.text.toString().trim()
            val password = binding.signUpPasswordEditText.text.toString().trim()
            val user = User("Template", email, password, "Template", "Template", true, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTO9_01SZ7hFnlt1TGOQG_pQ1xe0PY2R9E9A&usqp=CAU", arrayListOf())
            if (api.registerUser(user)){
                val bundle = Bundle()
                bundle.putSerializable("param1", user)
                findNavController().navigate(R.id.action_signUpFragment_to_bodyFragment, bundle)
                Toast.makeText(requireContext(), "Successfully registered", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Email already registered", Toast.LENGTH_LONG).show()
            }
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}