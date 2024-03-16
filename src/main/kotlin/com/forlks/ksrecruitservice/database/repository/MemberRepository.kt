package com.forlks.ksrecruitservice.database.repository

import com.forlks.ksrecruitservice.database.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemberRepository : JpaRepository<MemberEntity, Long> {

    fun findUsersEntityByUserIdAndDelete(userId : String, isDelete: String) : Optional<MemberEntity>
}
