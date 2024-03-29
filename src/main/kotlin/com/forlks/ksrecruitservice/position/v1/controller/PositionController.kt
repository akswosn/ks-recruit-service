package com.forlks.ksrecruitservice.position.v1.controller

import com.forlks.ksrecruitservice.common.component.JwtTokenProvider
import com.forlks.ksrecruitservice.common.exception.KsException
import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.position.v1.service.PositionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/position")
class PositionController (
    private val positionService: PositionService,
    private val jwtTokenProvider: JwtTokenProvider
){
    private val log = KotlinLogging.logger{}


    @Operation(summary = "전체허용 채용중인 공고 리스트 조회")
    @GetMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun positionList(@RequestParam tags: List<String>?) = try {
        KsResponse.KS_SUCCESS.toDataResponse(mapOf("data" to positionService.list(tags)))
    } catch (e : KsException){
        log.warn("#### ksException :: $e")
        e.ksResponse().toResponse()
    } catch (e : Exception){
        log.error("#### Unchecked Exception :: $e")
        KsResponse.KS_INTERNAL_SERVER_ERROR.toResponse()
    }

    @SecurityRequirement(name = "Authorization")
    @Operation(summary = "입사지원 (로그인후 이용가능)")
    @PostMapping(value = ["/{jobPositionId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun apply(
        @PathVariable("jobPositionId") jobPositionId: Long,
        @RequestBody dto: ApplyReqDto,
        request: HttpServletRequest) = try {
        val token = jwtTokenProvider.resolveToken(request)
        val memberId = jwtTokenProvider.getMemberId(token)

        log.info("### memberId: $memberId")

        KsResponse.KS_SUCCESS.toDataResponse(mapOf("result" to positionService.apply(memberId, jobPositionId, dto.resumeId)))
    } catch (e : KsException){
        log.warn("#### ksException :: $e")
        e.ksResponse().toResponse()
    } catch (e : Exception){
        log.error("#### Unchecked Exception :: $e")
        KsResponse.KS_INTERNAL_SERVER_ERROR.toResponse()
    }

    class ApplyReqDto (var resumeId: Long = 0){}
}
