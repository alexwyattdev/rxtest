package hu.alexujvary.intertickettest.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.alexujvary.intertickettest.ui.pages.first.FirstPageFragment
import hu.alexujvary.intertickettest.ui.pages.fourth.FourthPageFragment
import hu.alexujvary.intertickettest.ui.pages.second.SecondPageFragment
import hu.alexujvary.intertickettest.ui.pages.third.ThirdPageFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> SecondPageFragment()
            2 -> ThirdPageFragment()
            3 -> FourthPageFragment()
            else -> FirstPageFragment()
        }
    }
}