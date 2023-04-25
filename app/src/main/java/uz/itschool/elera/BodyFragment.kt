package uz.itschool.elera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import uz.itschool.elera.databinding.FragmentBodyBinding
import uz.itschool.elera.util.MyViewPagerAdapter
import uz.itschool.elera.util.User

private const val ARG_PARAM1 = "param1"

class BodyFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: User? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getSerializable(ARG_PARAM1) as User
//        }
//    }

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
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.home_ic)
                    tab.view.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50)
                }
                1 -> {
                    tab.setIcon(R.drawable.my_courses_ic)
                    tab.view.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50)
                }
                2 -> {
                    tab.setIcon(R.drawable.inbox_ic)
                    tab.view.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50)
                }
                3 -> {
                    tab.setIcon(R.drawable.store_ic)
                    tab.view.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50)
                }
                else -> {
                    tab.setIcon(R.drawable.profile_ic)
                    tab.view.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50)
                }
            }
        }.attach()

        return binding.root
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: User) =
//            BodyFragment().apply {
//                arguments = Bundle().apply {
//                    putSerializable(ARG_PARAM1, param1)
//                }
//            }
//    }
}