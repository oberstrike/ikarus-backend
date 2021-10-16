package de.ma.ikarus.api.resources

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
class ResourceName(
    @ProjectedFieldName("name") val name: String = "",
    @ProjectedFieldName("id") val id: Long
)