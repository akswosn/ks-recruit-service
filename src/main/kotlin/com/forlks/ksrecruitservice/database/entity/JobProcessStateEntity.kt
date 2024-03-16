package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_process_state", schema = "recruit_db", catalog = "")
open class JobProcessStateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "name")
    open var name: String? = null,

    @Column(name = "is_process")
    open var isProcess: String? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null
) {


    @OneToMany(mappedBy = "JobProcessStateByApplicantTracking", targetEntity=ApplicantTrackingEntity::class)
    var mappedByApplicantTracking: List<ApplicantTrackingEntity>? = null


}

