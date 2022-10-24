package com.testapp.quotesapp.usecases

import com.testapp.quotesapp.ui.main.repo.QuotesRepo
import javax.inject.Inject

class UpdateQuoteUseCase @Inject constructor(private val repo: QuotesRepo) {

    suspend fun execute(urlId: Int)  {

    }

}