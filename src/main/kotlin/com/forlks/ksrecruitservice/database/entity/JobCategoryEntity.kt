package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "job_category", schema = "recruit_db", catalog = "")
open class JobCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,
    @Column(name = "name")
    open var name: String? = null,
    @Column(name = "depth")
    open var depth: Int? = null,
    @Column(name = "job_category_id")
    open var jobCategoryId: Int? = null,
    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,
    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,
    @Column(name = "is_delete")
    open var delete: String? = null,
) {


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_category_id", referencedColumnName = "id")
//    var refJobCategoryEntity: JobCategoryEntity? = null
//
//    @OneToMany(mappedBy = "refJobCategoryEntity")
//    var refJobCategoryEntities: List<JobCategoryEntity>? = null
//
//    @OneToMany(mappedBy = "refJobCategoryEntity")
//    var refJobPositionCategoryEntities: List<JobPositionCategoryEntity>? = null

}

