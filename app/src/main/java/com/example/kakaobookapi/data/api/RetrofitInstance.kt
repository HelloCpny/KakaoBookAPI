package com.example.kakaobookapi.data.api

import com.example.kakaobookapi.util.Contants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    //싱글톤으로 구현 Object와 lazy활용
    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder() //객체만들기
            .addConverterFactory(MoshiConverterFactory.create()) //디티오 변환에 사용할 모시컨버터팩토리를 제이슨컨버터로 지정
            .client(okHttpClient) //레트로핏 객체를 설정할때 클라이언트 속성에 okhttpclient를 이용하여 데이터를 인터셉터
            .baseUrl(BASE_URL)
            .build()
    }
    val api: BookSearchApi by lazy {
        retrofit.create(BookSearchApi::class.java)
    }
}