package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "applicant_tracking", schema = "recruit_db", catalog = "")
open class ApplicantTrackingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,
    @Column(name = "job_position_id", insertable=false, updatable=false)
    open var jobPositionId: Long? = null,
    @Column(name = "member_id")
    open var memberId: Long? = null,
    @Column(name = "member_resume_id")
    open var memberResumeId: Long? = null,
    @Column(name = "process_state_id", insertable=false, updatable=false)
    open var processStateId: Long? = null,
    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,
    @Column(name = "is_delete")
    open var delete: String? = null
) {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_position_id", referencedColumnName = "id")
    open var jobPositionByApplicantTracking: JobPositionEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    var refMemberEntity: MemberEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_resume_id", referencedColumnName = "id")
//    var refMemberResumeEntity: MemberResumeEntity? = null
//
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "process_state_id", referencedColumnName = "id")
    open var JobProcessStateByApplicantTracking: JobProcessStateEntity? = null


}

