package de.ma.ikarus.persistence.resources

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import io.quarkus.panache.common.Parameters
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ResourceRepository : PanacheRepositoryBase<ResourceEntity, String> {

    fun findByUser(username: String): List<ResourceEntity> {
        return find("user.name = :username", Parameters.with("username", username)).list()
    }


}