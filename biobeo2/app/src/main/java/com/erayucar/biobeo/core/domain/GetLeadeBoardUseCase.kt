package com.erayucar.biobeo.core.domain

import com.erayucar.biobeo.core.data.RouteRepository
import javax.inject.Inject

class GetLeadeBoardUseCase @Inject constructor(
    private val repository: RouteRepository
){

    suspend operator fun invoke() = repository.getLeaderBoard()

}