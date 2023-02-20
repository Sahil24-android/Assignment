package com.example.assignment.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.assignment.LoginActivity
import com.example.assignment.R
import com.example.assignment.data.PrefManager

class ProfileFragment : Fragment() {
   private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView =  inflater.inflate(R.layout.fragment_profile, container, false)
        prefManager = PrefManager(requireContext())

        val logOut = mView.findViewById<TextView>(R.id.logout)
        logOut.setOnClickListener {
            prefManager.setLogin(false)
            startActivity(Intent(requireContext(),LoginActivity::class.java))
        }

        return mView
    }

    companion object {

    }
}