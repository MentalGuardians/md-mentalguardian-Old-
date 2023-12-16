package org.guardteam.mentalguardians.domain.use_case

data class FeatureUseCase(
    val getPredict: GetPredict,
    val getHistory: GetHistory,
    val getContent: GetContent,
    val getContentById: GetContentById
)
