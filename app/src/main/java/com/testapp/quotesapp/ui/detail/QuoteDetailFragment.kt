package com.testapp.quotesapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.testapp.quotesapp.common.AppConstant
import com.testapp.quotesapp.databinding.FragmentItemDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class QuoteDetailFragment : Fragment() {

    private lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        toolbarLayout = binding.toolbarLayout
        itemDetailTextView = binding.itemDetail
        Timber.e("arguments = "+arguments?.getString(AppConstant.AUTHOR_QUOTE))
        itemDetailTextView.text = arguments?.getString(AppConstant.AUTHOR_QUOTE)
        binding.detailToolbar.title = arguments?.getString(AppConstant.AUTHOR_NAME)
        return rootView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}