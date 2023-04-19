package uz.itschool.elera.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.itschool.elera.databinding.FragmentFrag1Binding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Frag1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFrag1Binding.inflate(inflater, container, false)
        binding.far1Img.setImageResource(param1!!)
        binding.far1Text.text = param2!!
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(resId: Int, text: String) =
            Frag1Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, resId)
                    putString(ARG_PARAM2, text)
                }
            }
    }
}