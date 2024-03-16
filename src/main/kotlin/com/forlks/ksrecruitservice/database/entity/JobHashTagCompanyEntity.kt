package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_hash_tag_company", schema = "recruit_db", catalog = "")
@IdClass(JobHashTagCompanyEntityPK::class)
open class JobHashTagCompanyEntity(
    @Id
    @Column(name = "job_hash_tag_id")
    open var jobHashTagId: Int? = null,

    @Id
    @Column(name = "company_id")
    open var companyId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_hash_tag_id", referencedColumnName = "id")
//    var refJobHashTagEntity: JobHashTagEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    var refCompanyEntity: CompanyEntity? = null

}

class JobHashTagCompanyEntityPK : java.io.Serializable {
    @Id

    @Column(name = "job_hash_tag_id")
    var jobHashTagId: Int? = null

    @Id

    @Column(name = "company_id")
    var companyId: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as JobHashTagCompanyEntityPK

        if (jobHashTagId != other.jobHashTagId) return false
        if (companyId != other.companyId) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
