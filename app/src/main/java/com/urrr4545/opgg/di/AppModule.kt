/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.urrr4545.opgg.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.urrr4545.opgg.Constants
import com.urrr4545.opgg.data.network.OpggApi
import com.urrr4545.opgg.data.network.dto.GameDtoMapper
import com.urrr4545.opgg.data.network.dto.SummonerDtoMapper
import com.urrr4545.opgg.data.respository.OpggRepositoryImpl
import com.urrr4545.opgg.domain.repository.SummonerRepository
import com.urrr4545.opgg.domain.usecase.GetAnalysisUseCase
import com.urrr4545.opgg.domain.usecase.GetGameUseCase
import com.urrr4545.opgg.domain.usecase.GetSummonerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideMovieApiService(retrofitBuilder: Retrofit.Builder): OpggApi {
        return retrofitBuilder
            .build()
            .create(OpggApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSummonerDtoMapper(): SummonerDtoMapper {
        return SummonerDtoMapper()
    }

    @Singleton
    @Provides
    fun provideMatchDtoMapper(): GameDtoMapper {
        return GameDtoMapper()
    }

    @Singleton
    @Provides
    fun provideSummonerRepository(
        api: OpggApi,
        summonerDtoMapper: SummonerDtoMapper,
        matchDtoMapper: GameDtoMapper
    ) = OpggRepositoryImpl(api, summonerDtoMapper, matchDtoMapper) as SummonerRepository

    @Singleton
    @Provides
    fun provideGetSummonerUseCase(
        summonerRepository: SummonerRepository
    ): GetSummonerUseCase{
        return GetSummonerUseCase(summonerRepository)
    }

    @Singleton
    @Provides
    fun provideGetGameUseCase(
        summonerRepository: SummonerRepository
    ): GetGameUseCase{
        return GetGameUseCase(summonerRepository)
    }

    @Singleton
    @Provides
    fun provideGetAnalysisUseCase(
        summonerRepository: SummonerRepository
    ): GetAnalysisUseCase{
        return GetAnalysisUseCase(summonerRepository)
    }
}
