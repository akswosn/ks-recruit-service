package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "member_foreign", schema = "recruit_db", catalog = "")
open class MemberForeignEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "member_resume_id")
    open var memberResumeId: Int? = null,

    @Column(name = "name")
    open var name: String? = null,

    @Column(name = "level")
    open var level: String? = null,

    @Column(name = "description")
    open var description: String? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_resume_id", referencedColumnName = "id")
//    var refMemberResumeEntity: MemberResumeEntity? = null

}

