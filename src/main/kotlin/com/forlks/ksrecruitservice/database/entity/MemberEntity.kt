package com.forlks.ksrecruitservice.database.entity

import jakarta.persistence.*
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.time.ZonedDateTime

@Entity
@Table(name = "member", schema = "recruit_db", catalog = "")
open class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "user_id")
    open var userId: String? = null,

    @Column(name = "password")
    open var password: String? = null,

    @Column(name = "oauth_type")
    open var oauthType: String? = null,

    @Column(name = "oauth_social_id")
    open var oauthSocialId: String? = null,

    @Column(name = "member_role_id")
    open var memberRoleId: Int? = null,

    @Column(name = "created_at")
    open var createdAt: ZonedDateTime? = null,

    @Column(name = "updated_at")
    open var updatedAt: ZonedDateTime? = null,

    @Column(name = "is_delete")
    open var delete: String? = null,
) {


//    @OneToMany(mappedBy = "refMemberEntity")
//    var refApplicantTrackingEntities: List<ApplicantTrackingEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberEntity")
//    var refCompanyEntities: List<CompanyEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberEntity")
//    var refCompanyHrMemberMappingEntities: List<CompanyHrMemberMappingEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberEntity")
//    var refJobHashTagMemberEntities: List<JobHashTagMemberEntity>? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_role_id", referencedColumnName = "id")
//    var refMemberRoleEntity: MemberRoleEntity? = null
//
//    @OneToMany(mappedBy = "refMemberEntity")
//    var refMemberResumeEntities: List<MemberResumeEntity>? = null

//    @OneToMany(mappedBy = "refMemberEntity")
//    var refMenuAccessEntities: List<MenuAccessEntity>? = null
//
//    @OneToMany(mappedBy = "refFromMemberEntity")
//    var refFromMessageEntities: List<MessageEntity>? = null
//
//    @OneToMany(mappedBy = "retToMemberEntity")
//    var refToMessageEntities: List<MessageEntity>? = null
//
//    @OneToMany(mappedBy = "refMemberEntity")
//    var refNotificationMemberEntities: List<NotificationMemberEntity>? = null

    override fun toString(): String = ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
}

