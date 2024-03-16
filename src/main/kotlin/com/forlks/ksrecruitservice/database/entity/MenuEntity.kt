package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "menu", schema = "recruit_db", catalog = "")
open class MenuEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "name")
    open var name: String? = null,

    @Column(name = "menu_code")
    open var menuCode: String? = null,

    @Column(name = "state")
    open var state: String? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null
) {


//    @OneToMany(mappedBy = "refMenuEntity")
//    var refMenuAccessEntities: List<MenuAccessEntity>? = null


}

