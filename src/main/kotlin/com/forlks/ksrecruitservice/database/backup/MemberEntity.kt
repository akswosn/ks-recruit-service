package com.forlks.ksrecruitservice.database.backup

import jakarta.persistence.*
import java.time.ZonedDateTime


//@Entity
//@Table(name = "member", schema = "recruit_db", catalog = "")
class MemberEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "user_id")
    var userId : String? = null

    @Column(name = "password")
    var password: String?= null

    @Column(name = "oauth_type")
    var oauthType: String?= null

    @Column(name = "oauth_social_id")
    var oauthSocialId: String?= null

    @Column(name = "member_role_id")
    var memberRoleId: String?= null

    @Column(name = "created_at")
    var createdAt:ZonedDateTime? = null

    @Column(name = "updated_at")
    var updatedAt:ZonedDateTime? = null

    @Column(name = "is_delete")
    var isDelete: String = "N"
}
