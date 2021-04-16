package com.example.error_book.database

data class Note(
    var ID: Long,
    var questionTitle : String,
    var questionImage : String,
    var answerImage : String,
    var answerConclusion: String,
    var timeCreated: String,
    var reviewedTimes: Int
)
