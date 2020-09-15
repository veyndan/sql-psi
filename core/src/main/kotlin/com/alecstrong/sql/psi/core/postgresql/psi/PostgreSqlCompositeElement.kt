package com.alecstrong.sql.psi.core.postgresql.psi

import com.alecstrong.sql.psi.core.postgresql.PostgreSqlFileBase
import com.alecstrong.sql.psi.core.psi.NamedElement
import com.alecstrong.sql.psi.core.psi.QueryElement.QueryResult
import com.alecstrong.sql.psi.core.psi.SqlCompositeElement

internal interface PostgreSqlCompositeElement : SqlCompositeElement {

  override fun getContainingFile(): PostgreSqlFileBase
}

class LazyQuery(
  val tableName: NamedElement,
  query: () -> QueryResult
) {
  val query by lazy(query)
}
