package org.guardteam.mentalguardians.domain.use_case

data class FeatureUseCase(
    val getPredict: GetPredict,
    val getContent: GetContent,
    val getContentById: GetContentById
)
