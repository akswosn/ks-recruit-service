package com.forlks.ksrecruitservice.domain.v1.member.jobposition

import java.time.ZonedDateTime

class MyApplyJobPosition(
    val jobPositionName: String? = null,
    val process: String? = null,
    val requestedAt: ZonedDateTime? = null
) {
}
