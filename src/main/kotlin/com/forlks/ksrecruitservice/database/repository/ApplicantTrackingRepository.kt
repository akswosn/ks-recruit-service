package com.forlks.ksrecruitservice.database.repository

import com.forlks.ksrecruitservice.database.entity.ApplicantTrackingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicantTrackingRepository : JpaRepository<ApplicantTrackingEntity, Long>{

    fun countAllByMemberIdAndJobPositionIdAndDelete(memberId:Long, jobPositionId: Long, delete: String): Long


    fun findAllByMemberIdAndDelete(memberId: Long, delete: String): List<ApplicantTrackingEntity>
}
