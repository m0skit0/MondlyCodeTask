package org.m0skit0.android.mondlycodetask.data


import com.squareup.moshi.Json

data class ItemsDTO(
    @Json(name = "dataCollection")
    val dataCollection: List<DataCollectionDTO> = emptyList()
) {
    data class DataCollectionDTO(
        @Json(name = "item")
        val item: ItemDTO = ItemDTO()
    ) {
        data class ItemDTO(
            @Json(name = "attributes")
            val attributes: AttributesDTO = AttributesDTO(),
            @Json(name = "id")
            val id: String = ""
        ) {
            data class AttributesDTO(
                @Json(name = "description")
                val description: String = "",
                @Json(name = "imageInfo")
                val imageInfo: ImageInfoDTO = ImageInfoDTO(),
                @Json(name = "name")
                val name: String = ""
            ) {
                data class ImageInfoDTO(
                    @Json(name = "imageUrl")
                    val imageUrl: String = ""
                )
            }
        }
    }
}
