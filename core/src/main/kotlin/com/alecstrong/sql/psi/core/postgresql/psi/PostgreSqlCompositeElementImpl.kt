package com.alecstrong.sql.psi.core.postgresql.psi

import com.alecstrong.sql.psi.core.postgresql.PostgreSqlFileBase
import com.alecstrong.sql.psi.core.psi.SqlCompositeElementImpl
import com.intellij.lang.ASTNode

open class PostgreSqlCompositeElementImpl(
  node: ASTNode
) : SqlCompositeElementImpl(node), PostgreSqlCompositeElement {

  override fun getContainingFile() = super.getContainingFile() as PostgreSqlFileBase
}
