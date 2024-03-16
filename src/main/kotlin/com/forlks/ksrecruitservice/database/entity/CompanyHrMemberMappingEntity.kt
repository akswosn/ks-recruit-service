package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "company_hr_member_mapping", schema = "recruit_db", catalog = "")
@IdClass(CompanyHrMemberMappingEntityPK::class)
open class CompanyHrMemberMappingEntity(
    @Id
    @Column(name = "company_id")
    open var companyId: Int? = null,
    @Id
    @Column(name = "member_id")
    open var memberId: Int? = null,
    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    var refCompanyEntity: CompanyEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    var refMemberEntity: MemberEntity? = null


}

class CompanyHrMemberMappingEntityPK : java.io.Serializable {
    @Id

    @Column(name = "company_id")
    var companyId: Int? = null

    @Id

    @Column(name = "member_id")
    var memberId: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CompanyHrMemberMappingEntityPK

        if (companyId != other.companyId) return false
        if (memberId != other.memberId) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
