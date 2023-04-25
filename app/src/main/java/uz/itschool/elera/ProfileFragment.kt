package uz.itschool.elera

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import okhttp3.internal.userAgent
import uz.itschool.elera.databinding.FragmentProfileBinding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.User

private const val ARG_PARAM1 = "param1"
class ProfileFragment : Fragment() {
//    private var param1: User? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getSerializable(ARG_PARAM1) as User
//        }
//    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val api = API.newInstance(requireContext())
        val curUser = api.getLoggedInUser()!!
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.profileLogOutButton.setOnClickListener {
            // TODO: Warning dialog
            api.logOut()
            findNavController().navigate(R.id.action_bodyFragment_to_welcomeFragment)
        }
        binding.profilePhoto.load(curUser.image)
        binding.profileName.text = curUser.firstName + " " + curUser.lastName
        binding.profileUsername.text = curUser.username
        return binding.root
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: User) =
//            ProfileFragment().apply {
//                arguments = Bundle().apply {
//                    putSerializable(ARG_PARAM1, param1)
//                }
//            }
//    }
}