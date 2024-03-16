package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "message", schema = "recruit_db", catalog = "")
open class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "message")
    open var message: String? = null,

    @Column(name = "from_member_id")
    open var fromMemberId: Int? = null,

    @Column(name = "to_member_id")
    open var toMemberId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "from_member_id", referencedColumnName = "id")
//    var refFromMemberEntity: MemberEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "to_member_id", referencedColumnName = "id")
//    var retToMemberEntity: MemberEntity? = null


}

