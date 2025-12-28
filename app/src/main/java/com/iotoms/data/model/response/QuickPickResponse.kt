package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuickPickResponse(

	@field:SerializedName("pages")
	val pages: List<QuickPickPagesItem?>? = null,

	@field:SerializedName("checksum")
	val checksum: String? = null,

	@field:SerializedName("configurationId")
	val configurationId: String? = null,

	@field:SerializedName("version")
	val version: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

@Serializable
data class AdditionalProp3(
	val any: String? = null
)

@Serializable
data class AdditionalProp1(
	val any: String? = null
)

@Serializable
data class QuickPickButtonsItem(

	@field:SerializedName("itemId")
	val itemId: String? = null,

	@field:SerializedName("buttonColor")
	val buttonColor: String? = null,

	@field:SerializedName("positionCol")
	val positionCol: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("uiPayload")
	val uiPayload: UiPayload? = null,

	@field:SerializedName("buttonLabel")
	val buttonLabel: String? = null,

	@field:SerializedName("positionRow")
	val positionRow: Int? = null
)

@Serializable
data class AdditionalProp2(
	val any: String? = null
)

@Serializable
data class UiPayload(

	@field:SerializedName("additionalProp1")
	val additionalProp1: AdditionalProp1? = null,

	@field:SerializedName("additionalProp3")
	val additionalProp3: AdditionalProp3? = null,

	@field:SerializedName("additionalProp2")
	val additionalProp2: AdditionalProp2? = null
)

@Serializable
data class QuickPickPagesItem(

	@field:SerializedName("backgroundColor")
	val backgroundColor: String? = null,

	@field:SerializedName("buttons")
	val buttons: List<QuickPickButtonsItem?>? = null,

	@field:SerializedName("pageIndex")
	val pageIndex: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
