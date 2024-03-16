package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_hash_tag_member", schema = "recruit_db", catalog = "")
@IdClass(JobHashTagMemberEntityPK::class)
open class JobHashTagMemberEntity(
    @Id
    @Column(name = "job_hash_tag_id")
    open var jobHashTagId: Int? = null,

    @Id
    @Column(name = "member_id")
    open var memberId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_hash_tag_id", referencedColumnName = "id")
//    var refJobHashTagEntity: JobHashTagEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    var refMemberEntity: MemberEntity? = null


}

class JobHashTagMemberEntityPK : java.io.Serializable {
    @Id
    @Column(name = "job_hash_tag_id")
    var jobHashTagId: Int? = null

    @Id
    @Column(name = "member_id")
    var memberId: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as JobHashTagMemberEntityPK

        if (jobHashTagId != other.jobHashTagId) return false
        if (memberId != other.memberId) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
