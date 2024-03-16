package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "company", schema = "recruit_db", catalog = "")
open class CompanyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,
    @Column(name = "name")
    open var name: String? = null,
    @Column(name = "company_registration_no")
    open var companyRegistrationNo: String? = null,
    @Column(name = "member_id")
    open var memberId: Int? = null,
    @Column(name = "logo_path")
    open var logoPath: String? = null,
    @Column(name = "employee_of")
    open var employeeOf: String? = null,
    @Column(name = "sales_of")
    open var salesOf: String? = null,
    @Column(name = "address")
    open var address: String? = null,
    @Column(name = "ceo_name")
    open var ceoName: String? = null,
    @Column(name = "business")
    open var business: String? = null,
    @Column(name = "state")
    open var state: String? = null,
    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,
    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,
    @Column(name = "is_delete")
    open var delete: String? = null,
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    var refMemberEntity: MemberEntity? = null
//
//    @OneToMany(mappedBy = "refCompanyEntity")
//    var refCompanyHrMemberMappingEntities: List<CompanyHrMemberMappingEntity>? = null
//
//    @OneToMany(mappedBy = "refCompanyEntity")
//    var refCompanyPaymentEntities: List<CompanyPaymentEntity>? = null
//
//    @OneToMany(mappedBy = "refCompanyEntity")
//    var refJobHashTagCompanyEntities: List<JobHashTagCompanyEntity>? = null
//
//    @OneToMany(mappedBy = "refCompanyEntity")
//    var refJobPositionEntities: List<JobPositionEntity>? = null
//
//    @OneToMany(mappedBy = "refCompanyEntity")
//    var refPaymentHistoryEntities: List<PaymentHistoryEntity>? = null
//
//    @OneToMany(mappedBy = "refCompanyEntity")
//    var refPaymentScheduleEntities: List<PaymentScheduleEntity>? = null


}

