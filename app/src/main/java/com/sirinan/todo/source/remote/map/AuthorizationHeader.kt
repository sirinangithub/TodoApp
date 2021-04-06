package com.sirinan.todo.source.remote.map

class AuthorizationHeader(token:String) : HashMap<String, String>(
    hashMapOf("Authorization" to "Bearer ".plus(token))
)