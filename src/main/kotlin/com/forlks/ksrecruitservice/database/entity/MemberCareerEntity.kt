package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "member_career", schema = "recruit_db", catalog = "")
open class MemberCareerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "member_resume_id")
    open var memberResumeId: Int? = null,

    @Column(name = "name")
    open var name: String? = null,

    @Column(name = "is_joined")
    open var isJoined: String? = null,

    @Column(name = "team_position")
    open var teamPosition: String? = null,

    @Column(name = "description")
    open var description: String? = null,

    @Column(name = "started_ym")
    open var startedYm: String? = null,

    @Column(name = "end_ym")
    open var endYm: String? = null,

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

