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

    /**
         * Retrieves a list of currently open job positions, optionally filtered by tags.
         *
         * @param tags Optional list of tags to filter job positions.
         * @return A JSON response containing the list of job positions.
         */
        @Operation(summary = "전체허용 채용중인 공고 리스트 조회")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun positionList(@RequestParam tags: List<String>?): Any =
        runCatching {
            KsResponse.KS_SUCCESS.toDataResponse(mapOf("data" to positionService.list(tags)))
        }.getOrElse { e ->
            handleException(e)
        }

    /**
     * Handles a job application request for a specific job position by an authenticated user.
     *
     * Extracts the member ID from the JWT token in the HTTP request, then processes the application using the provided job position ID and resume ID.
     * Returns a JSON response indicating the result of the application.
     *
     * @param jobPositionId The ID of the job position to apply for.
     * @param dto The request body containing the resume ID.
     * @return A JSON response with the result of the application.
     */
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

    /**
     * Converts a thrown exception into an appropriate HTTP response.
     *
     * If the exception is a `KsException`, returns its associated response; otherwise, returns a generic internal server error response.
     *
     * @param e The exception to handle.
     * @return The HTTP response corresponding to the exception.
     */
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