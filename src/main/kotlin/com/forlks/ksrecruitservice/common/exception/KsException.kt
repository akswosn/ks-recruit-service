package com.forlks.ksrecruitservice.common.exception

import com.forlks.ksrecruitservice.common.response.KsResponse
import java.lang.Exception

abstract class KsException(
        ksResponse: KsResponse,
        e: Exception
) : RuntimeException(ksResponse.resultMsg(), e) {

    abstract fun ksResponse(): KsResponse

    abstract fun e(): Exception
}
