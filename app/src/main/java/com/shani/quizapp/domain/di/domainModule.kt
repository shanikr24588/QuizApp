package com.shani.quizapp.domain.di

import com.shani.quizapp.domain.UseCase.GetQuizzesUseCases
import com.shani.quizapp.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object domainModule {
    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository):GetQuizzesUseCases{
        return GetQuizzesUseCases(quizRepository)
    }
}