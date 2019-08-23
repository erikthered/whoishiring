package me.erikthered.whoishiring

data class JobPosting (
        val id: Int,
        val company: String,
        val location: String? = null,
        val employmentType: String? = null,
        val remoteOk: Boolean = false,
        val fullText: String
)