package com.forlks.ksrecruitservice.domain.v1.member.service

import com.forlks.ksrecruitservice.common.component.JwtTokenProvider
import com.forlks.ksrecruitservice.common.dto.JwtPayloadDto
import com.forlks.ksrecruitservice.common.exception.KsException
import com.forlks.ksrecruitservice.common.exception.KsServiceException
import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.common.utils.EncryptUtils
import com.forlks.ksrecruitservice.database.repository.ApplicantTrackingRepository
import com.forlks.ksrecruitservice.database.repository.MemberRepository
import com.forlks.ksrecruitservice.domain.v1.member.controller.MemberController
import com.forlks.ksrecruitservice.domain.v1.member.jobposition.MyApplyJobPosition
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val encryptUtils: EncryptUtils,
    private val jwtTokenProvider: JwtTokenProvider,
    private val applicantTrackingRepository: ApplicantTrackingRepository
) {
    private val log = KotlinLogging.logger {}

    @Transactional(readOnly = false, rollbackFor = [Exception::class, KsServiceException::class])
    fun signIn(dto: MemberController.SigninReqDto): String = try {
        val member = memberRepository.findUsersEntityByUserIdAndDelete(dto.userId, "N")
            .orElseThrow { KsServiceException(KsResponse.KS_NOT_USER, Exception()) }
        log.info("### member ::: $member")
        if (encryptUtils.isPasswordCheck(dto.password, member.password!!)) {
            //검증성공~ token 생성
            val payload = JwtPayloadDto(
                memberId = member.id,
                userId = member.userId!!,
                roles = member.memberRoleId.toString()
            )
            jwtTokenProvider.createToken(payload);
        } else {
            log.warn("#### PASSWORD Fail !!! $dto")
            throw KsServiceException(KsResponse.KS_INVALID_MEMBER_PASSWORD, Exception())
        }
    } catch (e: KsException) {
        log.error("### user login Error KsException ::: $e")
        throw e
    } catch (e: Exception) {
        log.error("### user login Error ::: $e")
        throw KsServiceException(KsResponse.KS_INTERNAL_SERVER_ERROR, e)
    }

    @Transactional
    fun jobPosition(memberId: Long): List<MyApplyJobPosition> = try {
        val data = applicantTrackingRepository.findAllByMemberIdAndDelete(memberId, "N")
        log.info("#### jobPosition ::: $data")
        data.map { it -> MyApplyJobPosition(
            process = it.JobProcessStateByApplicantTracking?.name,
            jobPositionName = it.jobPositionByApplicantTracking?.title,
            requestedAt = it.createdAt
        ) }.toList()
    } catch (e: KsException) {
        log.error("### user jobPosition Error KsException ::: $e")
        throw e
    } catch (e: Exception) {
        log.error("### user jobPosition Error ::: $e")
        throw KsServiceException(KsResponse.KS_INTERNAL_SERVER_ERROR, e)
    }
}
