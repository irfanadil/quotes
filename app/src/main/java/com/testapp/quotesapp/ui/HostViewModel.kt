package com.testapp.quotesapp.ui

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.quotesapp.api.GenericApiResponse
import com.testapp.quotesapp.api.QuoteViewState
import com.testapp.quotesapp.ui.model.QuotesModelItem
import com.testapp.quotesapp.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(
    private val getAllQuotesUseCase: GetAllQuotesUseCase,
    private val getSingleQuoteUseCase: GetSingleQuoteUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val deleteQuoteUseCase: DeleteQuoteUseCase,
    private val updateQuoteUseCase: UpdateQuoteUseCase,

) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<QuoteViewState>(QuoteViewState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState = _uiState.asStateFlow()

    //val quoteResponse: SingleLiveEvent<GenericApiResponse<QuotesModel>> = SingleLiveEvent()

    private val _randomQuoteModelResponse = MutableLiveData<GenericApiResponse<QuotesModelItem>>()
    val randomQuoteModelResponse: LiveData<GenericApiResponse<QuotesModelItem>> = _randomQuoteModelResponse

    init {
        viewModelScope.launch {
            val result = getAllQuotesUseCase.execute()
            if (result is GenericApiResponse.Failure) {
                _uiState.value = QuoteViewState.Empty
            } else if (result is GenericApiResponse.Success) {
                _uiState.value = QuoteViewState.Success(result.value)
            }
        }
    }

    fun getSingleQuote(quoteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            //val result = getSingleQuoteUseCase.execute(quoteId)
            //quoteResponse.postValue(result)
        }
    }

    fun getRandomQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            _randomQuoteModelResponse.postValue(getRandomQuoteUseCase.execute())
        }
    }

    fun deleteQuoteById(quoteId: String) = viewModelScope.launch(Dispatchers.IO) {
        deleteQuoteUseCase.execute(quoteId)
    }

    fun updateUrlRecordById(urlId: Int) = viewModelScope.launch(Dispatchers.IO) {
        updateQuoteUseCase.execute(urlId)
    }

}