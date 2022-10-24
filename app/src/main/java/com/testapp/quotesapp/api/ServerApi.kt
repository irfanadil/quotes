package com.testapp.quotesapp.api

import com.testapp.quotesapp.ui.model.QuotesModel
import com.testapp.quotesapp.ui.model.QuotesModelItem
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ServerApi {

    @GET("api/quotes")
    suspend fun getAllQuotes() : QuotesModel

    @GET("api/quotes")
    suspend fun getSingleQuote() : QuotesModelItem

    @GET("api/quotes/random")
    suspend fun getRandomQuote() : QuotesModelItem

    @POST("api/quotes")
    suspend fun createQuote() : QuotesModelItem

    @DELETE ("api/quotes")
    suspend fun deleteQuote(quoteId:String) : QuotesModelItem

    @PUT ("api/quotes")
    suspend fun updateQuote(quoteId:String) : QuotesModelItem

}


