package com.shani.quizapp.data.di

import com.shani.quizapp.data.remote.QuizApi
import com.shani.quizapp.data.repository.QuizRepositoryImpl
import com.shani.quizapp.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideQuizApi():QuizApi{
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory
                .create()).build().create(QuizApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizRepository(quizApi: QuizApi):QuizRepository{
        return QuizRepositoryImpl(quizApi)
    }
}

