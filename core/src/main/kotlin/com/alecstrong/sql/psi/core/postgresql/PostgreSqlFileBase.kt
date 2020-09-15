package com.alecstrong.sql.psi.core.postgresql

import com.alecstrong.sql.psi.core.SqlFileBase
import com.intellij.lang.Language
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement

abstract class PostgreSqlFileBase(
  viewProvider: FileViewProvider,
  language: Language
) : SqlFileBase(viewProvider, language) {

  fun domains(sqlStmtElement: PsiElement): Collection<PostgreSqlCreateDomainStmt> = TODO()
}

// TODO
typealias PostgreSqlCreateDomainStmt = Any
