package com.example.assignment.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment.R
import com.example.assignment.databinding.FragmentItemDetailBinding
import com.google.android.material.appbar.MaterialToolbar

class ItemDetailFragment : Fragment() {

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        toolbar.title = "Product details"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val image = requireArguments().getString("image")
        val name = requireArguments().getString("name")
        val price = requireArguments().getString("price")
        val description = requireArguments().getString("description")
        val rating = requireArguments().getString("rating")

        val sentences = description!!.split(Regex("(?<=\\.)\\s"))
        val builder = StringBuilder()

        for (str in sentences){
            builder.append("\u2022  $str").append("\n")
        }



        binding.itemImageDetail.setImageResource(image!!.toInt())
        binding.itemNameDetail.text = name
        binding.itemPriceDetail.text = getString(R.string.price,price)
        binding.rating.text = getString(R.string.rating,rating)
        binding.description.text = builder
        Log.d("description", sentences.toString())
        return root
    }

    companion object {

    }
}