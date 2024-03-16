package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.time.ZonedDateTime

@Entity
@Table(name = "job_position", schema = "recruit_db", catalog = "")
open class JobPositionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "company_id")
    open var companyId: Long? = null,

    @Column(name = "title")
    open var title: String? = null,

    @Column(name = "position_detail")
    open var positionDetail: String? = null,

    @Column(name = "state")
    open var state: String? = null,

    @Column(name = "end")
    open var end: ZonedDateTime? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null,


) {

    @OneToMany(mappedBy = "jobPositionByApplicantTracking",
        targetEntity = ApplicantTrackingEntity::class, fetch = FetchType.LAZY, orphanRemoval = true)
    open var mappedByApplicationTracking: List<ApplicantTrackingEntity> = mutableListOf()


    override fun toString(): String = ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)


//
//    @OneToMany(mappedBy = "refJobPositionEntity")
//    var refJobHashTagPositionEntities: List<JobHashTagPositionEntity>? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    var refCompanyEntity: CompanyEntity? = null
//
//    @OneToMany(mappedBy = "refJobPositionEntity")
//    var refJobPositionCategoryEntities: List<JobPositionCategoryEntity>? = null


}

