package com.alecstrong.sql.psi.core.psi.mixins

import com.alecstrong.sql.psi.core.SqlAnnotationHolder
import com.alecstrong.sql.psi.core.psi.QueryElement.QueryResult
import com.alecstrong.sql.psi.core.psi.Schema
import com.alecstrong.sql.psi.core.psi.SchemaContributor
import com.alecstrong.sql.psi.core.psi.SqlCompositeElementImpl
import com.alecstrong.sql.psi.core.psi.SqlCreateIndexStmt
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.util.containers.MultiMap

internal abstract class CreateIndexMixin(
  node: ASTNode
) : SqlCompositeElementImpl(node),
    SqlCreateIndexStmt,
    SchemaContributor {
  override fun modifySchema(schema: Schema): Schema {
    val types = schema.types.toMutableMap()
    val indexes = types[CreateIndexMixin::class] ?: MultiMap()
    indexes.putValue(indexName.text, this)
    types[CreateIndexMixin::class] = indexes
    return schema.copy(
        types = types
    )
  }

  override fun queryAvailable(child: PsiElement): Collection<QueryResult> {
    if (child in indexedColumnList || child == expr) {
      return listOfNotNull(
          tablesAvailable(child).firstOrNull { it.tableName.name == tableName?.name }?.query
      )
    }
    return super.queryAvailable(child)
  }

  override fun annotate(annotationHolder: SqlAnnotationHolder) {
    if (containingFile.schema<CreateIndexMixin>(this).any { it != this && it.indexName.text == indexName.text }) {
      annotationHolder.createErrorAnnotation(indexName, "Duplicate index name ${indexName.text}")
    }
    super.annotate(annotationHolder)
  }
}
