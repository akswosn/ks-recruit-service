package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "payment_history", schema = "recruit_db", catalog = "")
open class PaymentHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "company_id")
    open var companyId: Int? = null,

    @Column(name = "column_pay_typename3")
    open var columnPayTypename3: String? = null,

    @Column(name = "card_no")
    open var cardNo: String? = null,

    @Column(name = "card_name")
    open var cardName: String? = null,

    @Column(name = "bank_no")
    open var bankNo: String? = null,

    @Column(name = "bank_name")
    open var bankName: String? = null,

    @Column(name = "result")
    open var result: String? = null,

    @Column(name = "request_at")
    open var requestAt: ZonedDateTime? = null,

    @Column(name = "payment_at")
    open var paymentAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    var refCompanyEntity: CompanyEntity? = null


}

