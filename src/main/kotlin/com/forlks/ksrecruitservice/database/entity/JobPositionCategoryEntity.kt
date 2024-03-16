package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_position_category", schema = "recruit_db", catalog = "")
@IdClass(JobPositionCategoryEntityPK::class)
open class JobPositionCategoryEntity(
    @Id
    @Column(name = "job_position_id")
    open var jobPositionId: Int? = null,

    @Id
    @Column(name = "job_category_id")
    open var jobCategoryId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_position_id", referencedColumnName = "id")
//    var refJobPositionEntity: JobPositionEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_category_id", referencedColumnName = "id")
//    var refJobCategoryEntity: JobCategoryEntity? = null


}

class JobPositionCategoryEntityPK : java.io.Serializable {
    @Id

    @Column(name = "job_position_id")
    var jobPositionId: Int? = null

    @Id

    @Column(name = "job_category_id")
    var jobCategoryId: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as JobPositionCategoryEntityPK

        if (jobPositionId != other.jobPositionId) return false
        if (jobCategoryId != other.jobCategoryId) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
