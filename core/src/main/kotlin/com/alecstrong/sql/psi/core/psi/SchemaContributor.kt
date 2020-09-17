package com.alecstrong.sql.psi.core.psi

import com.intellij.util.containers.MultiMap
import kotlin.reflect.KClass

internal interface SchemaContributor {
  fun modifySchema(schema: Schema): Schema
}

internal data class Schema(
  val types: Map<KClass<*>, MultiMap<String, Any>>
)
