package com.example.assignment.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.assignment.adapter.ItemRecyclerAdapter
import com.example.assignment.R
import com.example.assignment.adapter.RecentItemAdapter
import com.example.assignment.data.ItemListDataClass
import com.example.assignment.data.RecentItemData
import com.example.assignment.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), RecentItemAdapter.OnItemClick {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list: ArrayList<ItemListDataClass> = ArrayList()
    private val recentList: ArrayList<RecentItemData> = ArrayList()


    private var imageIds =
        intArrayOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
    private var currentPage = 0
    private val handler = Handler(Looper.getMainLooper())

    private val autoSlide: Runnable = object : Runnable {
        override fun run() {
            if (currentPage == imageIds.size) {
                currentPage = 0
            }
            binding.viewPager.setCurrentItem(currentPage++, false)
            handler.postDelayed(this, 1000)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.viewPager.adapter = SlideShowPagerAdapter()
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        data()
        binding.itemRecyclerView.adapter = ItemRecyclerAdapter(list)
        binding.itemRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        binding.itemRecyclerView.layoutManager = GridLayoutManager(requireContext(),4)
        binding.itemRecyclerView.setHasFixedSize(true)

        recentData()
        binding.recentRecyclerView.adapter = RecentItemAdapter(recentList, this)
        binding.recentRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        binding.itemRecyclerView.layoutManager = GridLayoutManager(requireContext(),4)
        binding.recentRecyclerView.setHasFixedSize(true)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(autoSlide, 1000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(autoSlide)
    }

    private fun data() {
        list.add(ItemListDataClass(R.drawable.grocerie, "Groceries"))
        list.add(ItemListDataClass(R.drawable.fashion, "Fashion"))
        list.add(ItemListDataClass(R.drawable.electronics, "Electronics"))
        list.add(ItemListDataClass(R.drawable.medicine, "Medicine"))
        list.add(ItemListDataClass(R.drawable.vege, "Food"))


    }

    private fun recentData() {
        recentList.add(RecentItemData(R.drawable.iphone, "Iphone 14(Blue, 128GB)", "74,900",4.6,"15.40 cm (6.1-inch) Super Retina XDR display. Advanced camera system for better photos in any light. Cinematic mode now in 4K Dolby Vision up to 30 fps. Action mode for smooth, steady, handheld videos. Vital safety technology — Crash Detection calls for help when you can’t"))
        recentList.add(RecentItemData(R.drawable.iphone, "Iphone 14(Blue, 128GB)", "74,900",4.6,"15.40 cm (6.1-inch) Super Retina XDR display.Advanced camera system for better photos in any light.Cinematic mode now in 4K Dolby Vision up to 30 fps.Action mode for smooth, steady, handheld videos. Vital safety technology — Crash Detection calls for help when you can’t"))
        recentList.add(RecentItemData(R.drawable.iphone, "Iphone 14(Blue, 128GB)", "74,900",4.6,"15.40 cm (6.1-inch) Super Retina XDR display.Advanced camera system for better photos in any light.Cinematic mode now in 4K Dolby Vision up to 30 fps.Action mode for smooth, steady, handheld videos. Vital safety technology — Crash Detection calls for help when you can’t"))


    }

    companion object {

    }

    private inner class SlideShowPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return imageIds.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(activity)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setImageResource(imageIds[position])
            container.addView(imageView)

            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as ImageView)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

    }

    override fun itemClick(position: Int) {
        val fragment = ItemDetailFragment()
        val current = recentList[position]
        val bundle = Bundle()

        bundle.putString("image",current.image.toString())
        bundle.putString("name",current.name)
        bundle.putString("price",current.price)
        bundle.putString("description",current.description)
        bundle.putString("rating",current.rating.toString())
        fragment.arguments = bundle
        loadFragment(fragment)


    }

    private fun loadFragment(fragment: Fragment) {
        val manager = (requireContext() as AppCompatActivity).supportFragmentManager
        manager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
            commit()
        }
    }
}