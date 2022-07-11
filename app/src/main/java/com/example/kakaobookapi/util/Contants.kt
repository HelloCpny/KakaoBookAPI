package com.example.kakaobookapi.util

import com.example.kakaobookapi.BuildConfig


object Contants {
    const val BASE_URL = "https://dapi.kakao.com/"
    const val API_KEY = BuildConfig.bookApiKey

    //    const val API_KEY = "32803cba2b19a0296a3745b15f02ba28" //모든사람이 API_KEY에 접근하면 안됨
    const val SEARCH_BOOKS_TIME_DELAY = 100L
}