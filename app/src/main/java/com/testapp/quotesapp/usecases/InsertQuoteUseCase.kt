package com.testapp.quotesapp.usecases

import com.testapp.quotesapp.ui.main.repo.QuotesRepo
import com.testapp.quotesapp.ui.model.QuotesModelItem
import javax.inject.Inject

class InsertQuoteUseCase @Inject constructor(private val repo: QuotesRepo) {

    suspend fun execute(quotesModelItem: QuotesModelItem) = repo.insertQuote(quotesModelItem)

}