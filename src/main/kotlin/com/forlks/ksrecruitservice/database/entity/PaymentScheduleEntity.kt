package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "payment_schedule", schema = "recruit_db", catalog = "")
open class PaymentScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "company_id")
    open var companyId: Int? = null,

    @Column(name = "product_package_id")
    open var productPackageId: Int? = null,

    @Column(name = "periodic")
    open var periodic: Int? = null,

    @Column(name = "state")
    open var state: String? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    var refCompanyEntity: CompanyEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_package_id", referencedColumnName = "id")
//    var refProductPackageEntity: ProductPackageEntity? = null


}

