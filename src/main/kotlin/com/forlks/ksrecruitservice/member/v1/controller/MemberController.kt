package com.forlks.ksrecruitservice.member.v1.controller

import com.forlks.ksrecruitservice.common.component.JwtTokenProvider
import com.forlks.ksrecruitservice.common.exception.KsException
import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.common.response.KsResponseEntity
import com.forlks.ksrecruitservice.member.v1.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val encoder: BCryptPasswordEncoder,
    private val memberService: MemberService,
    private val jwtTokenProvider: JwtTokenProvider
) {
    private val log = KotlinLogging.logger {}


    @Operation(hidden = true)
    @PostMapping("/password")
    fun encPassword(@RequestBody dto: PasswordReqDto): ResponseEntity<KsResponseEntity> =
        KsResponse.KS_SUCCESS.toDataResponse(mapOf("encrypt" to encoder.encode(dto.password)))


    @Operation(summary = "로그인")
    @PostMapping(value = ["/sign-in"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun signIn(dto: SigninReqDto) = try {
        KsResponse.KS_SUCCESS.toDataResponse(mapOf("token" to memberService.signIn(dto)))
    } catch (e: KsException) {
        log.warn("#### ksException :: $e")
        e.ksResponse().toResponse()
    } catch (e: Exception) {
        log.error("#### Unchecked Exception :: $e")
        KsResponse.KS_INTERNAL_SERVER_ERROR.toResponse()
    }

    @SecurityRequirement(name = "Authorization")
    @Operation(summary = "진행중인 채용 프로세스 (로그인후 이용가능)")
    @PostMapping(value = ["/job-position"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun jobPosition(request: HttpServletRequest) = try {
        val token = jwtTokenProvider.resolveToken(request)
        val memberId = jwtTokenProvider.getMemberId(token!!)

        KsResponse.KS_SUCCESS.toDataResponse(mapOf("data" to memberService.jobPosition(memberId)))
    } catch (e: KsException) {
        log.warn("#### ksException :: $e")
        e.ksResponse().toResponse()
    } catch (e: Exception) {
        log.error("#### Unchecked Exception :: $e")
        KsResponse.KS_INTERNAL_SERVER_ERROR.toResponse()
    }

    class PasswordReqDto(var password: String = "")

    class SigninReqDto(var userId: String = "", var password: String = "")
}
