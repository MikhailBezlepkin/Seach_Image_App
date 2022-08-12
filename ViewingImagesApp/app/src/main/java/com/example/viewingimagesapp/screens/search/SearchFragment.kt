package com.example.viewingimagesapp.screens.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.viewingimagesapp.*
import com.example.viewingimagesapp.adapter.ImageAdapter
import com.example.viewingimagesapp.databinding.FragmentSearchBinding
import com.example.viewingimagesapp.model.ImagesItem


class SearchFragment : Fragment(), ImageAdapter.ClickListener {
    lateinit var binding: FragmentSearchBinding
    lateinit var adapter: ImageAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDelete.visibility = View.GONE
        init()
/*if(!InternetConnection.checkConnection(requireActivity())==true){
    binding.searchEd.setText("Internet true")
}
        else{
            binding.searchEd.setText("Internet fALSE")
        }*/
    }

    override fun onItemClick(imagesItem: ImagesItem) {
        val bundle = Bundle()
        bundle.putString("image", imagesItem.urls.full)
        bundle.putString("title", imagesItem.user.name)
        APP.navController.navigate(R.id.action_searchFragment_to_viewingFragment, bundle)
    }

    private fun init() {
        val viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        adapter = ImageAdapter(this)
        recyclerView = binding.rvImages
        recyclerView.adapter = adapter

        viewModel.myImagesList.observe(viewLifecycleOwner) { list ->
            adapter.setList(list)
        }

        // Работа с кнопками
        binding.searchEd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.searchEd.text.toString() != "") {
                    binding.btnDelete.visibility = View.VISIBLE
                } else {
                    binding.btnDelete.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.btnSearch.setOnClickListener {
            if (binding.searchEd.text.toString() != "") {
                viewModel.getImagesVM("${binding.searchEd.text}")
                viewModel.myImagesList.observe(viewLifecycleOwner) { list ->
                    adapter.setList(list)
                }
            }
        }
        binding.btnDelete.setOnClickListener {
            binding.searchEd.setText("")
        }

        //настройка скрола

    }
}