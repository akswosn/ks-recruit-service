package com.forlks.ksrecruitservice.position.v1.vo

import jakarta.persistence.Column

class PositionListVo(
    var positionId: Long? = null,
    var title: String? = null,
    var positionDetail: String? = null,
) {
}
