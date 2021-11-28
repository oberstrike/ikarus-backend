package de.ma.ikarus.persistence.resources

import de.ma.ikarus.domain.resource.Resource
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import io.quarkus.panache.common.Parameters
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ResourceRepository : PanacheRepositoryBase<ResourceEntity, String> {

    fun findByUser(userId: String): List<ResourceEntity> {
        return find("user.userId = :userId", Parameters.with("userId", userId)).list()
    }

    fun save(entity: ResourceEntity): ResourceEntity {
        persist(entity)
        flush()
        return entity
    }




}