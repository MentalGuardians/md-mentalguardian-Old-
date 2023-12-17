package org.guardteam.mentalguardians.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.guardteam.mentalguardians.BuildConfig
import org.guardteam.mentalguardians.data.manager.LocalDataManagerImpl
import org.guardteam.mentalguardians.data.remote.ApiService
import org.guardteam.mentalguardians.data.repository.AuthRepositoryImpl
import org.guardteam.mentalguardians.data.repository.FeatureRepositoryImpl
import org.guardteam.mentalguardians.domain.manager.LocalDataManager
import org.guardteam.mentalguardians.domain.repository.AuthRepository
import org.guardteam.mentalguardians.domain.repository.FeatureRepository
import org.guardteam.mentalguardians.domain.use_case.AuthUseCase
import org.guardteam.mentalguardians.domain.use_case.ClearUserData
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import org.guardteam.mentalguardians.domain.use_case.GetContent
import org.guardteam.mentalguardians.domain.use_case.GetContentById
import org.guardteam.mentalguardians.domain.use_case.GetDarkTheme
import org.guardteam.mentalguardians.domain.use_case.GetDynamicColor
import org.guardteam.mentalguardians.domain.use_case.GetHistory
import org.guardteam.mentalguardians.domain.use_case.GetLoginState
import org.guardteam.mentalguardians.domain.use_case.GetOnBoarding
import org.guardteam.mentalguardians.domain.use_case.GetPredict
import org.guardteam.mentalguardians.domain.use_case.GetProfile
import org.guardteam.mentalguardians.domain.use_case.GetTherapist
import org.guardteam.mentalguardians.domain.use_case.GetTherapistById
import org.guardteam.mentalguardians.domain.use_case.GetTransaction
import org.guardteam.mentalguardians.domain.use_case.GetUserData
import org.guardteam.mentalguardians.domain.use_case.OnBoardingUseCase
import org.guardteam.mentalguardians.domain.use_case.PostBooking
import org.guardteam.mentalguardians.domain.use_case.PostEditProfile
import org.guardteam.mentalguardians.domain.use_case.PostLogin
import org.guardteam.mentalguardians.domain.use_case.PostRegister
import org.guardteam.mentalguardians.domain.use_case.PutCancelBooking
import org.guardteam.mentalguardians.domain.use_case.SaveDarkTheme
import org.guardteam.mentalguardians.domain.use_case.SaveDynamicColor
import org.guardteam.mentalguardians.domain.use_case.SaveOnBoarding
import org.guardteam.mentalguardians.domain.use_case.SaveUserData
import org.guardteam.mentalguardians.domain.use_case.ThemeUseCase
import org.guardteam.mentalguardians.domain.use_case.UserDataUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataManager(
        @ApplicationContext context: Context
    ): LocalDataManager = LocalDataManagerImpl(context = context)

    @Provides
    @Singleton
    fun provideOnBoardingUseCase(localDataManager: LocalDataManager): OnBoardingUseCase {
        return OnBoardingUseCase(
            saveOnBoarding = SaveOnBoarding(localDataManager),
            getOnBoarding = GetOnBoarding(localDataManager)
        )
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository =
        AuthRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            postRegister = PostRegister(authRepository),
            postLogin = PostLogin(authRepository)
        )
    }

    @Provides
    @Singleton
    fun provideUserDataUseCase(localDataManager: LocalDataManager): UserDataUseCase {
        return UserDataUseCase(
            getUserData = GetUserData(localDataManager),
            saveUserData = SaveUserData(localDataManager),
            getLoginState = GetLoginState(localDataManager),
            clearUserData = ClearUserData(localDataManager)
        )
    }

    @Provides
    @Singleton
    fun provideFeatureRepository(
        apiService: ApiService,
        localDataManager: LocalDataManager
    ): FeatureRepository =
        FeatureRepositoryImpl(
            apiService = apiService,
            localDataManager = localDataManager
        )

    @Provides
    @Singleton
    fun provideFeatureUseCase(
        featureRepository: FeatureRepository
    ): FeatureUseCase {
        return FeatureUseCase(
            getPredict = GetPredict(featureRepository),
            getHistory = GetHistory(featureRepository),
            getContent = GetContent(featureRepository),
            getContentById = GetContentById(featureRepository),
            getTherapist = GetTherapist(featureRepository),
            getTherapistById = GetTherapistById(featureRepository),
            postBooking = PostBooking(featureRepository),
            getProfile = GetProfile(featureRepository),
            getTransaction = GetTransaction(featureRepository),
            postEditProfile = PostEditProfile(featureRepository),
            putCancelBooking = PutCancelBooking(featureRepository)
        )
    }

    @Provides
    @Singleton
    fun provideThemeUseCase(
        localDataManager: LocalDataManager
    ): ThemeUseCase {
        return ThemeUseCase(
            GetDynamicColor(localDataManager = localDataManager),
            GetDarkTheme(localDataManager),
            SaveDynamicColor(localDataManager),
            SaveDarkTheme(localDataManager)
        )
    }

}