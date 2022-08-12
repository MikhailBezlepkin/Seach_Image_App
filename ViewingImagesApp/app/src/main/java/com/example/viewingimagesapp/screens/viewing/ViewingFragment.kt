package com.example.viewingimagesapp.screens.viewing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.viewingimagesapp.APP
import com.example.viewingimagesapp.R
import com.example.viewingimagesapp.databinding.FragmentViewingBinding
import com.example.viewingimagesapp.model.ImagesItem

class ViewingFragment : Fragment() {

    lateinit var binding: FragmentViewingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewingBinding.inflate(layoutInflater, container, false)
        val image = arguments?.getString("image")
        binding.imageView.load(image)
        binding.itemTv.text = arguments?.getString("title")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_viewingFragment_to_searchFragment)
        }
    }
}