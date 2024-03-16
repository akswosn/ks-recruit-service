package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "member_role", schema = "recruit_db", catalog = "")
open class MemberRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "name")
    open var name: String? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null,
) {

//    @OneToMany(mappedBy = "refMemberRoleEntity")
//    var refMemberEntities: List<MemberEntity>? = null


}

