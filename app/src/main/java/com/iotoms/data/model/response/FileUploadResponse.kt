package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FileUploadResponse(

	@field:SerializedName("bucket")
	val bucket: String? = null,

	@field:SerializedName("compressionRatio")
	val compressionRatio: Double? = null,

	@field:SerializedName("compressedSizeBytes")
	val compressedSizeBytes: Double? = null,

	@field:SerializedName("publicUrl")
	val publicUrl: String? = null,

	@field:SerializedName("checksum")
	val checksum: String? = null,

	@field:SerializedName("objectName")
	val objectName: String? = null,

	@field:SerializedName("contentEncoding")
	val contentEncoding: String? = null,

	@field:SerializedName("mediaType")
	val mediaType: String? = null,

	@field:SerializedName("signedUrl")
	val signedUrl: String? = null,

	@field:SerializedName("fileId")
	val fileId: String? = null,

	@field:SerializedName("sizeBytes")
	val sizeBytes: Double? = null,

	@field:SerializedName("compressionApplied")
	val compressionApplied: Boolean? = null
)
