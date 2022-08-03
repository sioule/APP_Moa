package com.mobile.moa.mileage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/* written by KEH
 * date : 22.06.05 */

    class MileageShopVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MapFragment()
                else -> ShopListFragment()
            }
        }

    }
