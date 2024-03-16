package com.forlks.ksrecruitservice.database.backup

import jakarta.persistence.*
import java.time.ZonedDateTime

//@Entity
//@Table(name = "company", schema = "recruit_db", catalog = "")
class CompanyEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "name")
    var name: String? = null

    @Column(name = "company_registration_no")
    var companyRegistrationNo: String? = null

    @Column(name = "member_id")
    var memberId: Long? = null

    @Column(name = "logo_path")
    var logoPath: String? = null

    @Column(name = "employee_of")
    var employeeOf: String? = null

    @Column(name = "sales_of")
    var salesOf: String? = null

    @Column(name = "address")
    var address: String? = null

    @Column(name = "ceo_name")
    var ceoName: String? = null

    @Column(name = "business")
    var business: String? = null

    @Column(name = "state")
    var state: String = "Y"

    @Column(name = "created_at")
    var createdAt: ZonedDateTime? = null

    @Column(name = "updated_at")
    var updatedAt: ZonedDateTime? = null

    @Column(name = "is_delete")
    var isDelete: String = "N"


    //ref
//    @OneToMany(mappedBy = "jobPositionRef")
//    var mappedJobPosition: List<JobPositionEntity>? = null
}
