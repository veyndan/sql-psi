package com.alecstrong.sql.psi.core.postgresql.psi.mixins

import com.alecstrong.sql.psi.core.AnnotationException
import com.alecstrong.sql.psi.core.SqlAnnotationHolder
import com.alecstrong.sql.psi.core.postgresql.PostgreSqlParser
import com.alecstrong.sql.psi.core.psi.mixins.ColumnNameMixin as SqlColumnNameMixin
import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder

internal abstract class ColumnNameMixin(
  node: ASTNode
) : SqlColumnNameMixin(node) {
  override val parseRule: (PsiBuilder, Int) -> Boolean = PostgreSqlParser::column_name_real

  override fun annotate(annotationHolder: SqlAnnotationHolder) {
    try {
      val source = reference.unsafeResolve()
      if (source == null) {
        annotationHolder.createErrorAnnotation(this, "Blah blah blah $name")
      }
    } catch (e: AnnotationException) {
      annotationHolder.createErrorAnnotation(e.element ?: this, e.msg)
    }
  }
}
