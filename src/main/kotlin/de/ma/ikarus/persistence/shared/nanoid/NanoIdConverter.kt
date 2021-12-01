package de.ma.ikarus.persistence.shared.nanoid

import de.ma.ikarus.domain.shared.NanoId
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class NanoIdConverter : AttributeConverter<NanoId, String> {

    override fun convertToEntityAttribute(dbData: String?): NanoId {
        TODO("Not yet implemented")
    }

    override fun convertToDatabaseColumn(attribute: NanoId?): String {
        TODO("Not yet implemented")
    }

}