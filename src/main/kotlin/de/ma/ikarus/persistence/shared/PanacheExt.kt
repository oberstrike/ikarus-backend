package de.ma.ikarus.persistence.shared

import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.shared.PagedList
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.panache.common.Page


fun<T: Any> PanacheQuery<T>.toPagedList(pagedParams: PagedParams): PagedList<T> {
    val targetPage = this.page(Page.of(pagedParams.page, pagedParams.pageSize))
    return PagedList(pagedParams.page, targetPage.pageCount(), targetPage.list())
}
