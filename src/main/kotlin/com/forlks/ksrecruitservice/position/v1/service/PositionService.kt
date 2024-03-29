package com.forlks.ksrecruitservice.position.v1.service

import com.forlks.ksrecruitservice.common.dto.JwtPayloadDto
import com.forlks.ksrecruitservice.common.exception.KsException
import com.forlks.ksrecruitservice.common.exception.KsServiceException
import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.database.entity.ApplicantTrackingEntity
import com.forlks.ksrecruitservice.database.entity.JobPositionEntity
import com.forlks.ksrecruitservice.database.repository.ApplicantTrackingRepository
import com.forlks.ksrecruitservice.database.repository.JobPositionRepository
import com.forlks.ksrecruitservice.position.v1.controller.PositionController
import com.forlks.ksrecruitservice.position.v1.vo.PositionListVo
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.ZonedDateTime


@Service
class PositionService(
    private val jobPositionRepository: JobPositionRepository,
    private val applicantTrackingRepository: ApplicantTrackingRepository
) {
    private val log = KotlinLogging.logger {}

    private val jobPositionApplyState: Long = 1

    /**
     * 채용 공고 조화
     */
    @Transactional
    fun list(tags: List<String>?): List<PositionListVo> = try {
        var now = ZonedDateTime.now()
        val list = jobPositionRepository.findAllByEndAfterAndDelete(now, "N")
        log.info("### list ::: ${list.size}")
        list.map { PositionListVo(
            positionId = it.id, title = it.title, positionDetail = it.positionDetail
        ) }.toList()
    } catch (e: KsException) {
        log.error("### list Error KsException ::: $e")
        throw e
    } catch (e: Exception) {
        log.error("### list Error ::: $e")
        throw KsServiceException(KsResponse.KS_INTERNAL_SERVER_ERROR, e)
    }

    @Transactional(readOnly = false, rollbackFor = [Exception::class, KsServiceException::class])
    fun apply(memberId: Long, jobPositionId: Long, resumeId: Long):Boolean = try {
        var count = applicantTrackingRepository.countAllByMemberIdAndJobPositionIdAndDelete(memberId, jobPositionId,
            "N")
        log.info("### count ::: $count")

        if (count > 0) throw KsServiceException(KsResponse.KS_DUPLICATE_APPLY, java.lang.Exception("이미 지원한 공고"))

        val apply = ApplicantTrackingEntity(
            jobPositionId = jobPositionId, memberId = memberId,
            memberResumeId = resumeId, processStateId = jobPositionApplyState,
            createdAt = ZonedDateTime.now(), delete = "N"
        )

        applicantTrackingRepository.save(apply)

        true
    } catch (e: KsException) {
        log.error("### apply Error KsException ::: $e")
        throw e
    } catch (e: Exception) {
        log.error("### apply Error ::: $e")
        throw KsServiceException(KsResponse.KS_INTERNAL_SERVER_ERROR, e)
    }
}



