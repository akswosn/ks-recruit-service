package com.forlks.ksrecruitservice.database.repository

import com.forlks.ksrecruitservice.database.entity.JobPositionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime


@Repository
interface JobPositionRepository: JpaRepository<JobPositionEntity, Long> {

    fun findAllByEndAfterAndDelete(end: ZonedDateTime, isDelete: String): List<JobPositionEntity>
}
