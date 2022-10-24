package com.testapp.quotesapp.ui.main.repo

import com.testapp.quotesapp.api.GenericApiResponse
import com.testapp.quotesapp.ui.datasource.QuotesRemoteDataSource
import com.testapp.quotesapp.ui.model.QuotesModel
import com.testapp.quotesapp.ui.model.QuotesModelItem
import javax.inject.Inject

class QuotesRepo @Inject constructor(
    private val remoteDataSource : QuotesRemoteDataSource
    ):Repository
    {
        suspend fun getAllQuotes(): GenericApiResponse<QuotesModel> {
            return remoteDataSource.getAllQuotes()
        }

        suspend fun insertQuote(quotesModelItem: QuotesModelItem) = remoteDataSource.insertQuote(quotesModelItem)

        suspend fun getSingleQuote(quoteId:Int) = remoteDataSource.getSingleQuote(quoteId)

        suspend fun getRandomQuote() = remoteDataSource.getRandomQuote()

        suspend fun updateQuote(quoteId: String) = remoteDataSource.updateQuote(quoteId)

        suspend fun deleteQuote(quoteId:String) = remoteDataSource.deleteQuote(quoteId)

        /*
        suspend fun updateClickUrlToCopiedState(urlId:Int) {
            localDataSource.resetSelectUrlToInitialState()
            localDataSource.updateClickUrlToCopiedState(urlId)
        }

        fun getAllStoredUrl() = localDataSource.getAllStoredUrl()

         */


    }



