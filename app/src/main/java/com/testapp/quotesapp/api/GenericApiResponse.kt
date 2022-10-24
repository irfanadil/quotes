package com.testapp.quotesapp.api

import com.testapp.quotesapp.ui.listing.QuoteItemListAdapter
import com.testapp.quotesapp.ui.model.QuotesModel
import com.testapp.quotesapp.ui.model.QuotesModelItem


sealed class GenericApiResponse<out T> {

    class Success<out T>(val value: T) : GenericApiResponse<T>()

    class Failure(
        val failureStatus: FailureStatus = FailureStatus.API_FAIL,
        val code: Int? = null,
        val message: String? = null
    ) : GenericApiResponse<Nothing>()

    object Loading : GenericApiResponse<Nothing>()

    object Default : GenericApiResponse<Nothing>()
}

enum class FailureStatus {
    EMPTY,
    API_FAIL,
    NO_INTERNET,
    OTHER
}


sealed class QuoteViewState {
    object Empty : QuoteViewState()
    object Loading : QuoteViewState()
    data class Success(val quoteModel: ArrayList<QuotesModelItem>) : QuoteViewState()
    data class Error(val exception: Throwable) : QuoteViewState()
}






