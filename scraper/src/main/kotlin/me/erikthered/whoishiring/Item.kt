package me.erikthered.whoishiring

import java.time.OffsetDateTime

data class Item(
        val id: Int,
        val by: String?,
        val time: OffsetDateTime,
        val text: String?,
        val parent: Int?,
        val kids: IntArray?,
        val title: String?
)