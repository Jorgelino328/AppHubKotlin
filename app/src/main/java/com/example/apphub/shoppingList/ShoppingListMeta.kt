package com.example.apphub.shoppingList

import java.util.UUID

data class ShoppingListMeta(
    val id: String = UUID.randomUUID().toString(),
    var name: String
)