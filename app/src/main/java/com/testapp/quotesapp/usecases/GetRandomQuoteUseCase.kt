package com.testapp.quotesapp.usecases

import android.content.SharedPreferences
import com.testapp.quotesapp.api.GenericApiResponse
import com.testapp.quotesapp.common.AppConstant
import com.testapp.quotesapp.ui.main.repo.QuotesRepo
import com.testapp.quotesapp.ui.model.QuotesModelItem
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repo: QuotesRepo,
    private val randomQuote:Pair<String, String>,
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) {
    suspend fun execute(): GenericApiResponse<QuotesModelItem> {
        val storedDateTime = sharedPreferences.getLong(AppConstant.CURRENT_DAY_TIME,System.currentTimeMillis())
        return if(!sameDay(storedDateTime,System.currentTimeMillis()) || randomQuote.first == "") {
            val result = repo.getRandomQuote()
            if(result is GenericApiResponse.Success) {
                sharedPreferencesEditor.putLong(AppConstant.CURRENT_DAY_TIME, System.currentTimeMillis()).apply()
                sharedPreferencesEditor.putString(AppConstant.AUTHOR_NAME, result.value.author).apply()
                sharedPreferencesEditor.putString(AppConstant.AUTHOR_QUOTE,result.value.text).apply()
            }
            result
        } else
            GenericApiResponse.Success(QuotesModelItem(randomQuote.first,"dummyIdNotNeeded", randomQuote.second ))
    }

    private fun sameDay(a: Long, b: Long): Boolean {
        return sameDay(Date(a), Date(b))
    }

    private fun sameDay(a: Date, b: Date): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = a
        cal2.time = b
        Timber.e("change day = "+cal1[Calendar.DAY_OF_YEAR]+"  "+ cal2[Calendar.DAY_OF_YEAR])
        return (cal1[Calendar.YEAR] == cal2[Calendar.YEAR]
                && cal1[Calendar.DAY_OF_YEAR] == cal2[Calendar.DAY_OF_YEAR])
    }
}

