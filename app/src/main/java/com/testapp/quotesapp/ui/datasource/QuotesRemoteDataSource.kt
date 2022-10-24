package com.testapp.quotesapp.ui.datasource

import com.testapp.quotesapp.api.FailureStatus
import com.testapp.quotesapp.api.GenericApiResponse
import com.testapp.quotesapp.api.ServerApi
import com.testapp.quotesapp.ui.model.QuotesModel
import com.testapp.quotesapp.ui.model.QuotesModelItem
import timber.log.Timber
import javax.inject.Inject

class QuotesRemoteDataSource  @Inject constructor(private val serverApi: ServerApi) {

    suspend fun getAllQuotes(): GenericApiResponse<QuotesModel> {
        return try {
            val response = serverApi.getAllQuotes()
            Timber.e(" something should come up = $response")
            GenericApiResponse.Success(response)
        } catch (e: Throwable) {
            GenericApiResponse.Failure(FailureStatus.API_FAIL, 60,e.message)
        }
    }

    suspend fun getSingleQuote(quoteId: Int): GenericApiResponse<QuotesModelItem> {
        return try {
            val response = serverApi.getSingleQuote()
            GenericApiResponse.Success(response)
        } catch (e: Throwable) {
            GenericApiResponse.Failure(FailureStatus.API_FAIL, 60,e.message)
        }
    }

    suspend fun getRandomQuote(): GenericApiResponse<QuotesModelItem> {
        return try {
            val response = serverApi.getRandomQuote()
            GenericApiResponse.Success(response)
        } catch (e: Throwable) {
            GenericApiResponse.Failure(FailureStatus.API_FAIL, 60,e.message)
        }
    }

    suspend fun insertQuote(quotesModelItem:QuotesModelItem): GenericApiResponse<QuotesModel> {
        return try {
            val response = serverApi.getAllQuotes()
            GenericApiResponse.Success(response)
        } catch (e: Throwable) {
            GenericApiResponse.Failure(FailureStatus.API_FAIL, 60,e.message)
        }
    }

    suspend fun updateQuote(quoteId:String): GenericApiResponse<QuotesModelItem> {
        return try {
            val response = serverApi.updateQuote(quoteId)
            GenericApiResponse.Success(response)
        } catch (e: Throwable) {
            GenericApiResponse.Failure(FailureStatus.API_FAIL, 60,e.message)
        }
    }

    suspend fun deleteQuote(quoteId:String): GenericApiResponse<QuotesModelItem> {
        return try {
            val response = serverApi.deleteQuote(quoteId)
            GenericApiResponse.Success(response)
        } catch (e: Throwable) {
            GenericApiResponse.Failure(FailureStatus.API_FAIL, 60,e.message)
        }
    }
}