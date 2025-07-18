package com.forlks.ksrecruitservice.domain.v1.position.vo

import jakarta.persistence.Column

class PositionListVo(
    var positionId: Long? = null,
    var title: String? = null,
    var positionDetail: String? = null,
) {
}
