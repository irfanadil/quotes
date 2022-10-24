package com.testapp.quotesapp.usecases

import com.testapp.quotesapp.api.GenericApiResponse
import com.testapp.quotesapp.ui.main.repo.QuotesRepo
import com.testapp.quotesapp.ui.model.QuotesModel
import javax.inject.Inject

class GetAllQuotesUseCase @Inject constructor(
    private val repo: QuotesRepo

) {
    suspend fun execute(): GenericApiResponse<QuotesModel> {

         return repo.getAllQuotes()

    }

}