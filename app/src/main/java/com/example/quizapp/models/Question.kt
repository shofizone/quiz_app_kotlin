package com.example.quizapp.models

import android.os.Parcel
import android.os.Parcelable

data class Question (
    val id:Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAns: Int
)