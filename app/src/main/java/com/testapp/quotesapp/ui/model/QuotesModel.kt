package com.testapp.quotesapp.ui.model

class QuotesModel : ArrayList<QuotesModelItem>()

//@Entity(tableName = "quotesTable")
data class QuotesModelItem(
    val author: String,
    val id: String,
    val text: String,
    var visibleState: Boolean = true,
    //@PrimaryKey(autoGenerate = true)
    //var autoId:Int = 0
)