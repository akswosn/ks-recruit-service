package com.forlks.ksrecruitservice.common.exception

import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.common.response.KsResponseEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(KsException::class)
    fun badRequestError(ex: KsException): ResponseEntity<KsResponseEntity>? {
        return ex.ksResponse().toResponse()
    }
}
