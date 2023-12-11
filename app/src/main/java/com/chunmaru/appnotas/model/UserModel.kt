package com.chunmaru.appnotas.model

data class UserModel(
    val userId:String,
    val email:String,
    val username:String
){
    fun toMap() = mutableMapOf(
        "userId" to this.userId,
        "email" to this.email,
        "username" to this.username
    )
}
