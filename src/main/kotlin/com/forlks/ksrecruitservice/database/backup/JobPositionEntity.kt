package com.forlks.ksrecruitservice.database.backup

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.ZonedDateTime


//@Entity
//@Table(name = "job_position", schema = "recruit_db", catalog = "")
class JobPositionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "company_id")
    var companyId: Long? = null

    @Column(name = "title")
    var title: String? = null

    @JdbcTypeCode(SqlTypes.BLOB)
    @Column(name = "position_detail")
    var positionDetail: String? = null

    @Column(name = "state")
    var state: String = "Y"

    @Column(name = "end")
    var end: ZonedDateTime? = null

    @Column(name = "created_at")
    var createdAt: ZonedDateTime? = null

    @Column(name = "updated_at")
    var updatedAt: ZonedDateTime? = null

    @Column(name = "is_delete")
    var isDelete: String = "N"


    //    @Column(name = "companyId")
//    var company_id: Long = 0
//    @JdbcTypeCode(SqlTypes.JSON)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    var jobPositionRef: CompanyEntity? = null

}
