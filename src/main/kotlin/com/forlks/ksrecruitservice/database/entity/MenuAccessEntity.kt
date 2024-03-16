package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "menu_access", schema = "recruit_db", catalog = "")
open class MenuAccessEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "member_id")
    open var memberId: Int? = null,

    @Column(name = "menu_id")
    open var menuId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null,
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    var refMemberEntity: MemberEntity? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id", referencedColumnName = "id")
//    var refMenuEntity: MenuEntity? = null


}

