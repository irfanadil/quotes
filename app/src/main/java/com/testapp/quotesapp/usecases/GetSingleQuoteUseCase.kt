package com.testapp.quotesapp.usecases

import com.testapp.quotesapp.ui.main.repo.QuotesRepo
import javax.inject.Inject

class GetSingleQuoteUseCase @Inject constructor(private val repo: QuotesRepo) {

    suspend fun execute(quoteId:Int) = repo.getSingleQuote(quoteId)

}