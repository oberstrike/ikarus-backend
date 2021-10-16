package de.ma.ikarus.web.shared

import de.ma.ikarus.api.shared.PagedParams
import javax.ws.rs.DefaultValue
import javax.ws.rs.QueryParam

class PagedRequest {
    @DefaultValue("0")
    @QueryParam("page")
    var page: Int = 0

    @DefaultValue("10")
    @QueryParam("pageSize")
    var pageSize: Int = 10
}

fun PagedRequest.toPagedParams(): PagedParams {
    return PagedParams(page, pageSize)
}