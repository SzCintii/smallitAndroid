package com.example.demo.recycleviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.demo.R
//import androidx.fragment.app.viewModels


class BasicInfoFragment : Fragment() {
    private val viewModel: BasicInfoFragmentVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BasicInfoFragment.inflate(layoutInflater)
        val adapter = BasicInfoAdapter(BasicInfoClickListener {
            viewModel.basicInfoClicked(it)
        })
        binding.rwListBasic.adapter = adapter
        viewModel.BasicList.observe(viewLifecycleOwner, Observer { List<Basic>!
            it?.let { List<Basic>
            adapter.submitList(it)}
        })

        val manager = LinearLayoutManager(activity)
        binding.rwListBasic.layoutManager = manager
        return binding.root
    }

}
