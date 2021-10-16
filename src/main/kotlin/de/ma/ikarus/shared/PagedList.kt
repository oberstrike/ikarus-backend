package de.ma.ikarus.shared

data class PagedList<T>(
    val page: Int = 0,
    val pageCount: Int = 0,
    val content: List<T> = listOf()
)



fun<T, R> PagedList<T>.pagedMap( transform: (T) -> R): PagedList<R>{
    val map = this.content.map(transform)
    return PagedList(this.page, this.pageCount, map)
}