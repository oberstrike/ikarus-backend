package de.ma.ikarus.persistence.shared.nanoid

import de.ma.ikarus.domain.shared.NanoId
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class NanoIdConverter : AttributeConverter<NanoId, String> {

    override fun convertToEntityAttribute(dbData: String): NanoId {
        return NanoIdEntity(dbData)
    }

    override fun convertToDatabaseColumn(attribute: NanoId?): String {
        return attribute?.nanoId ?: ""
    }

}