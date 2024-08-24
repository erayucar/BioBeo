package com.erayucar.biobeo.core.domain

import com.erayucar.biobeo.core.data.RouteRepository
import com.erayucar.biobeo.core.network.dto.SignUpBody
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val routeRepository: RouteRepository
){

    suspend operator fun invoke(body: SignUpBody) = routeRepository.getAuth(body)
}