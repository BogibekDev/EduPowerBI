package uz.ubtuit.powerbi.model

data class Question(
    val toifalash: Category,
    val test: String,
    val a: String,
    val b: String,
    val c: String,
    val d: String,
    val t_j: String
)

data class Category(
    val dars: String,
)