package com.stepasha.endangeredhaven.model

data class ResponseBody(val access_token: String,
                        val token_type: String,
                        val experis_in: Int,
                        val scope: String)