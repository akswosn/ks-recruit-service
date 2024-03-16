package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_hash_tag_position", schema = "recruit_db", catalog = "")
@IdClass(JobHashTagPositionEntityPK::class)
open class JobHashTagPositionEntity(
    @Id
    @Column(name = "job_hash_tag_id")
    open var jobHashTagId: Int? = null,

    @Id
    @Column(name = "job_position_id")
    open var jobPositionId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_hash_tag_id", referencedColumnName = "id")
//    var refJobHashTagEntity: JobHashTagEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_position_id", referencedColumnName = "id")
//    var refJobPositionEntity: JobPositionEntity? = null


}

class JobHashTagPositionEntityPK : java.io.Serializable {
    @Id
    @Column(name = "job_hash_tag_id")
    var jobHashTagId: Int? = null

    @Id
    @Column(name = "job_position_id")
    var jobPositionId: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as JobHashTagPositionEntityPK

        if (jobHashTagId != other.jobHashTagId) return false
        if (jobPositionId != other.jobPositionId) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
