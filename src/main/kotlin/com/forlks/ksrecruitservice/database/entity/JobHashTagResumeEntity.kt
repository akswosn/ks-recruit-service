package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_hash_tag_resume", schema = "recruit_db", catalog = "")
@IdClass(JobHashTagResumeEntityPK::class)
open class JobHashTagResumeEntity(
    @Id
    @Column(name = "job_hash_tag_id")
    open var jobHashTagId: Int? = null,

    @Id
    @Column(name = "member_resume_id")
    open var memberResumeId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_hash_tag_id", referencedColumnName = "id")
//    var refJobHashTagEntity: JobHashTagEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_resume_id", referencedColumnName = "id")
//    var refMemberResumeEntity: MemberResumeEntity? = null


}

class JobHashTagResumeEntityPK : java.io.Serializable {
    @Id
    @Column(name = "job_hash_tag_id")
    var jobHashTagId: Int? = null

    @Id
    @Column(name = "member_resume_id")
    var memberResumeId: Int? = null


}
