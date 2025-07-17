package com.forlks.ksrecruitservice.domain.v1.position.controller

import com.forlks.ksrecruitservice.common.component.JwtTokenProvider
import com.forlks.ksrecruitservice.common.exception.KsException
import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.domain.v1.position.service.PositionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/position")
class PositionController(
    private val positionService: PositionService,
    private val jwtTokenProvider: JwtTokenProvider
) {
    private val log = KotlinLogging.logger {}

    @Operation(summary = "전체허용 채용중인 공고 리스트 조회")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun positionList(@RequestParam tags: List<String>?): Any =
        runCatching {
            KsResponse.KS_SUCCESS.toDataResponse(mapOf("data" to positionService.list(tags)))
        }.getOrElse { e ->
            handleException(e)
        }

    @SecurityRequirement(name = "Authorization")
    @Operation(summary = "입사지원 (로그인후 이용가능)")
    @PostMapping("/{jobPositionId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun apply(
        @PathVariable jobPositionId: Long,
        @RequestBody dto: ApplyReqDto,
        request: HttpServletRequest
    ): Any = runCatching {
        val token = jwtTokenProvider.resolveToken(request)
        val memberId = jwtTokenProvider.getMemberId(token)
        log.info { "memberId: $memberId" }
        KsResponse.KS_SUCCESS.toDataResponse(
            mapOf("result" to positionService.apply(memberId, jobPositionId, dto.resumeId))
        )
    }.getOrElse { e ->
        handleException(e)
    }

    private fun handleException(e: Throwable): Any = when (e) {
        is KsException -> {
            log.warn { "ksException: $e" }
            e.ksResponse().toResponse()
        }
        else -> {
            log.error { "Unchecked Exception: $e" }
            KsResponse.KS_INTERNAL_SERVER_ERROR.toResponse()
        }
    }

    data class ApplyReqDto(val resumeId: Long = 0)
}