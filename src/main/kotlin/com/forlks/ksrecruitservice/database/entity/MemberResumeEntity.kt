package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "member_resume", schema = "recruit_db", catalog = "")
open class MemberResumeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "member_id")
    open var memberId: Int? = null,

    @Column(name = "title")
    open var title: String? = null,

    @Column(name = "about_me_text")
    open var aboutMeText: String? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null,

    @Column(name = "is_public")
    open var public: String? = null
) {


//    @OneToMany(mappedBy = "refMemberResumeEntity")
//    var refApplicantTrackingEntities: List<ApplicantTrackingEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberResumeEntity")
//    var refJobHashTagResumeEntities: List<JobHashTagResumeEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberResumeEntity")
//    var refMemberCareerEntities: List<MemberCareerEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberResumeEntity")
//    var refMemberCertificateEntities: List<MemberCertificateEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberResumeEntity")
//    var refMemberEducationEntities: List<MemberEducationEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberResumeEntity")
//    var refMemberForeignEntities: List<MemberForeignEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberResumeEntity")
//    var refMemberOtherEntities: List<MemberOtherEntity>? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    var refMemberEntity: MemberEntity? = null


}

