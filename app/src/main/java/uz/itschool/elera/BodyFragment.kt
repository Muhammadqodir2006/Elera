package uz.itschool.elera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import uz.itschool.elera.databinding.FragmentBodyBinding
import uz.itschool.elera.util.MyViewPagerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BodyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BodyFragment : Fragment() {
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
        val binding = FragmentBodyBinding.inflate(inflater, container, false)
        val tabLayout = binding.bodyTabLayout
        val viewPager2 = binding.bodyViewPager
        viewPager2.adapter = MyViewPagerAdapter(
            childFragmentManager,
            lifecycle,
            arrayListOf(
                HomeFragment(),
                MyCourseFragment(),
                InboxFragment(),
                StoreFragment(),
                ProfileFragment()
            )
        )

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position){
                0->{
                    tab.setIcon(R.drawable.home_ic)
                }
                1->{
                    tab.setIcon(R.drawable.my_courses_ic)
                }
                2->{
                    tab.setIcon(R.drawable.inbox_ic)
                }
                3->{
                    tab.setIcon(R.drawable.store_ic)
                }
                else->{
                    tab.setIcon(R.drawable.profile_ic)
                }
            }
        }.attach()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BodyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}