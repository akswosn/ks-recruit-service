package com.forlks.ksrecruitservice.domain.v1.position.service

import com.forlks.ksrecruitservice.common.exception.KsException
import com.forlks.ksrecruitservice.common.exception.KsServiceException
import com.forlks.ksrecruitservice.common.response.KsResponse
import com.forlks.ksrecruitservice.database.entity.ApplicantTrackingEntity
import com.forlks.ksrecruitservice.database.repository.ApplicantTrackingRepository
import com.forlks.ksrecruitservice.database.repository.JobPositionRepository
import com.forlks.ksrecruitservice.domain.v1.position.vo.PositionListVo
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    /**
     * Submits a job application for a member to a specified job position using a given resume.
     *
     * Prevents duplicate applications by the same member to the same job position. Throws a service exception if the member has already applied. Returns `true` if the application is successfully submitted.
     *
     * @param memberId The ID of the member applying.
     * @param jobPositionId The ID of the job position to apply for.
     * @param resumeId The ID of the resume to use for the application.
     * @return `true` if the application is successfully submitted.
     * @throws KsServiceException If the member has already applied or if an internal server error occurs.
     */
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
        ;log.info { "apply insert ::: ${apply}" }
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



