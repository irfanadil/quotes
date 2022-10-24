package com.testapp.quotesapp.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.testapp.quotesapp.R
import com.testapp.quotesapp.api.GenericApiResponse
import com.testapp.quotesapp.api.QuoteViewState
import com.testapp.quotesapp.common.AppConstant
import com.testapp.quotesapp.databinding.FragmentItemListBinding
import com.testapp.quotesapp.extensions.hide
import com.testapp.quotesapp.extensions.show
import com.testapp.quotesapp.extensions.showToast
import com.testapp.quotesapp.ui.HostViewModel
import com.testapp.quotesapp.ui.model.QuotesModelItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class QuoteListFragment : Fragment(), QuoteAdapterClickListener {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HostViewModel by activityViewModels()
    private lateinit var quoteItemListAdapter: QuoteItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        observeResponse()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        quoteItemListAdapter = QuoteItemListAdapter(this@QuoteListFragment)
        binding.quoteRecycleView.apply {
            adapter = quoteItemListAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeResponse() {

        viewModel.randomQuoteModelResponse.observe(viewLifecycleOwner){
            when(it) {
                is GenericApiResponse.Success -> {
                    val result = it.value
                    binding.randomAuthorName.text = result.author
                    binding.randomQuoteText.text =  result.text
                }
                is GenericApiResponse.Default ->{
                    //binding.randomAuthorName.text =
                    //binding.randomQuoteText.text =
                }
                is GenericApiResponse.Failure -> {
                    requireContext().showToast("Random Api Failure, Reason is = " + it.message)
                }
                else -> {
                    requireContext().showToast("Random Quote Unknown Failure...")
                }
            }
        }

        viewModel.getRandomQuote()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is QuoteViewState.Loading -> binding.progress.show()
                        is QuoteViewState.Success -> {
                            binding.progress.hide()
                            Timber.e("QuoteViewState.Success")
                            onQuoteModelLoaded(uiState.quoteModel)
                        }
                        is QuoteViewState.Error -> {
                            binding.progress.hide()
                            Timber.e("QuoteViewState.Error")
                            requireActivity().showToast("Error...")
                        }
                        is QuoteViewState.Empty -> {
                            Timber.e("QuoteViewState.Empty")
                            binding.progress.hide()
                            showEmptyState()
                        }
                    }
                }
            }
        }
    }

    private fun showEmptyState() {
        Timber.e("showEmptyState fun running")
    }


    private fun onQuoteModelLoaded(itemList: List<QuotesModelItem>) {
        quoteItemListAdapter.updateInsideAdapter(itemList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun copyUrlEvent(quotesModelItem: QuotesModelItem) {
        val bundle = bundleOf(AppConstant.AUTHOR_NAME to quotesModelItem.author, AppConstant.AUTHOR_QUOTE to quotesModelItem.text)
        findNavController().navigate(R.id.show_item_detail, bundle)
    }

    override fun deleteUrlEvent(quotesModelItem: QuotesModelItem) {
        // viewModel.deleteQuoteById(quotesModelItem.id)
    }


}