package com.sirinan.todo.source.remote.network


object ServiceProperties {

    const val BASE_URL: String = "https://api-nodejs-todolist.herokuapp.com"
    const val Register = "/user/register"
    const val Login = "/user/login"
    const val Logout = "/user/logout"
    const val Task = "/task"
    const val TaskId = "/task/{id}"
}