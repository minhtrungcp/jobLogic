package com.example.joblogic.di

import android.content.Context
import com.example.joblogic.common.Constants
import com.example.joblogic.data.Api
import com.example.joblogic.data.repository.customer.CustomerModelMapperImpl
import com.example.joblogic.data.repository.customer.CustomerRepositoryImpl
import com.example.joblogic.data.repository.buy_product.BuyProductModelMapperImpl
import com.example.joblogic.data.repository.buy_product.BuyProductRepositoryImpl
import com.example.joblogic.data.repository.sell_product.DataBaseHelper
import com.example.joblogic.data.repository.sell_product.SellProductModelMapperImpl
import com.example.joblogic.data.repository.sell_product.SellProductRepositoryImpl
import com.example.joblogic.domain.repository.customer.CustomerRepository
import com.example.joblogic.domain.repository.product.BuyProductRepository
import com.example.joblogic.domain.repository.product.SellProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder().addInterceptor(logging)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .baseUrl(Constants.BASE_URL).build().create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(api: Api, mapper: CustomerModelMapperImpl) : CustomerRepository = CustomerRepositoryImpl(api, mapper)

    @Provides
    @Singleton
    fun provideBuyProductRepository(api: Api, mapperBuy: BuyProductModelMapperImpl) : BuyProductRepository = BuyProductRepositoryImpl(api, mapperBuy)

    @Provides
    @Singleton
    fun provideDatabaseHelper(@ApplicationContext context: Context) : DataBaseHelper = DataBaseHelper(context)

    @Provides
    @Singleton
    fun provideSellProductRepository(dataBaseHelper: DataBaseHelper, mapperSell: SellProductModelMapperImpl) : SellProductRepository = SellProductRepositoryImpl(dataBaseHelper, mapperSell)
}