package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "company_payment", schema = "recruit_db", catalog = "")
open class CompanyPaymentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,
    @Column(name = "company_id")
    open var companyId: Int? = null,
    @Column(name = "pay_type")
    open var payType: String? = null,
    @Column(name = "card_no")
    open var cardNo: String? = null,
    @Column(name = "card_name")
    open var cardName: String? = null,
    @Column(name = "bank_no")
    open var bankNo: String? = null,
    @Column(name = "bank_name")
    open var bankName: String? = null,
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
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    var refCompanyEntity: CompanyEntity? = null


}

