package com.forlks.ksrecruitservice.domain.v1.member.service

import com.forlks.ksrecruitservice.common.component.JwtTokenProvider
import com.forlks.ksrecruitservice.common.dto.JwtPayloadDto
import com.forlks.ksrecruitservice.common.exception.KsException
import com.forlks.ksrecruitservice.common.exception.KsServiceException
import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.common.utils.EncryptUtils
import com.forlks.ksrecruitservice.database.entity.MemberEntity
import com.forlks.ksrecruitservice.database.repository.ApplicantTrackingRepository
import com.forlks.ksrecruitservice.database.repository.MemberRepository
import com.forlks.ksrecruitservice.domain.v1.member.controller.MemberController
import com.forlks.ksrecruitservice.domain.v1.member.jobposition.MyApplyJobPosition
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val encryptUtils: EncryptUtils,
    private val jwtTokenProvider: JwtTokenProvider,
    private val applicantTrackingRepository: ApplicantTrackingRepository
) {
    private val log = KotlinLogging.logger {}

    /**
     * Authenticates a member using the provided credentials and returns a JWT token upon successful sign-in.
     *
     * Throws a `KsServiceException` if the user does not exist, the password is invalid, or an internal error occurs.
     *
     * @param dto The sign-in request containing user ID and password.
     * @return A JWT token string if authentication is successful.
     */
    @Transactional(readOnly = false, rollbackFor = [Exception::class, KsServiceException::class])
    fun signIn(dto: MemberController.SignInReqDto): String = try {
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

    /**
     * 신규 회원을 등록합니다.
     *
     * userId 형식 검증, 중복 체크, 비밀번호 강도 검증을 수행한 뒤
     * BCrypt로 암호화하여 저장합니다.
     *
     * @param dto 회원가입 요청 DTO (userId, password)
     * @return 등록 성공 여부
     * @throws KsServiceException 유효성 검증 실패 또는 중복 시
     */
    @Transactional(readOnly = false, rollbackFor = [Exception::class, KsServiceException::class])
    fun signUp(dto: MemberController.SignUpReqDto): Boolean = try {
        // 1. 빈 값 체크
        if (dto.userId.isBlank() || dto.password.isBlank()) {
            throw KsServiceException(KsResponse.KS_INVALID_PARAM, Exception())
        }

        // 2. userId 형식 검증 (영문/숫자 4~20자)
        val userIdPattern = "^[a-zA-Z0-9]{4,20}$".toRegex()
        if (!userIdPattern.matches(dto.userId)) {
            throw KsServiceException(KsResponse.KS_INVALID_USER_ID_FORMAT, Exception())
        }

        // 3. userId 중복 체크
        val existingMember = memberRepository.findUsersEntityByUserIdAndDelete(dto.userId, "N")
        if (existingMember.isPresent) {
            throw KsServiceException(KsResponse.KS_EXIST_MEMBER, Exception())
        }

        // 4. 비밀번호 강도 검증 (8자 이상, 대소문자/숫자/특수문자 포함)
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{8,}$".toRegex()
        if (!passwordPattern.matches(dto.password)) {
            throw KsServiceException(KsResponse.KS_WEAK_PASSWORD, Exception())
        }

        // 5. 비밀번호 암호화 및 회원 저장
        val encryptedPassword = encryptUtils.encryptPassword(dto.password)
        val newMember = MemberEntity(
            userId = dto.userId,
            password = encryptedPassword,
            memberRoleId = 1,
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now(),
            delete = "N"
        )
        memberRepository.save(newMember)
        log.info("#### 신규 회원 등록 완료: ${dto.userId}")
        true
    } catch (e: KsException) {
        log.error("### signUp Error KsException ::: $e")
        throw e
    } catch (e: Exception) {
        log.error("### signUp Error ::: $e")
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
