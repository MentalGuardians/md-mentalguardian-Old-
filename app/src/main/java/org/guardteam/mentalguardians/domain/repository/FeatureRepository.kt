package org.guardteam.mentalguardians.domain.repository

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.model.ContentById
import org.guardteam.mentalguardians.domain.model.HistoryData
import org.guardteam.mentalguardians.domain.model.Prediction
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.domain.model.TherapistById
import org.guardteam.mentalguardians.domain.model.Transaction
import org.guardteam.mentalguardians.domain.model.Profile

interface FeatureRepository {

    fun predict(text: String): Flow<Result<Prediction>>
    fun history(): Flow<Result<HistoryData>>
    fun content(content: String): Flow<Result<Content>>
    fun contentById(contentId: String): Flow<Result<ContentById>>

    fun expert(expert: String): Flow<Result<Therapist>>

    fun expertById(therapistId: String): Flow<Result<TherapistById>>

    fun booking(
        therapistId: String,
        date: String,
        time: String,
        method: String
    ): Flow<Result<Response>>

    fun profile(): Flow<Result<Profile>>
    fun transaction(): Flow<Result<Transaction>>


    fun editProfile(
        username: String,
        password: String,
        email: String,
        phone: String,
        alamat: String,
    ): Flow<Result<Response>>

    fun cancelBooking(bookingId: String): Flow<Result<Response>>

}