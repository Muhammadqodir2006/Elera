package uz.itschool.elera

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import coil.load
import okhttp3.internal.userAgent
import uz.itschool.elera.databinding.FragmentProfileBinding
import uz.itschool.elera.util.API
import uz.itschool.elera.util.User

private const val ARG_PARAM1 = "param1"
class ProfileFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val api = API.newInstance(requireContext())
        val curUser = api.getLoggedInUser()!!
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.profileLogOutButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Do you really want to log out?")

            builder.setPositiveButton("Yes") { dialog, which ->
                api.logOut()
                findNavController().navigate(R.id.action_bodyFragment_to_welcomeFragment)
            }

            builder.setNegativeButton("No") { dialog, which ->
            }
            builder.show()

        }
        binding.profilePhoto.load(curUser.image)
        binding.profileName.text = curUser.firstName + " " + curUser.lastName
        binding.profileUsername.text = curUser.username
        return binding.root
    }


}