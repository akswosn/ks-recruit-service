package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_hash_tag", schema = "recruit_db", catalog = "")
open class JobHashTagEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "name")
    open var name: String? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null
) {


//    @OneToMany(mappedBy = "refJobHashTagEntity")
//    var refJobHashTagCompanyEntities: List<JobHashTagCompanyEntity>? = null
//
//    @OneToMany(mappedBy = "refJobHashTagEntity")
//    var refJobHashTagMemberEntities: List<JobHashTagMemberEntity>? = null
//
//    @OneToMany(mappedBy = "refJobHashTagEntity")
//    var refJobHashTagPositionEntities: List<JobHashTagPositionEntity>? = null
//
//    @OneToMany(mappedBy = "refJobHashTagEntity")
//    var refJobHashTagResumeEntities: List<JobHashTagResumeEntity>? = null


}

