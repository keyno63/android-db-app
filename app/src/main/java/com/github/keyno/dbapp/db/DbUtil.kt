package com.github.keyno.dbapp.db

import java.sql.DriverManager

class DbUtil {
    fun get(): String? {
        try {
            val c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sandbox",
                "root",
                "root"
            )
            val stmt = c.createStatement()
            val result = stmt.executeQuery("SELECT * FROM person");
            val sb = StringBuffer()
            while (result.next()) {
                sb.append(result.getInt(1).toString() + result.getString(4))
            }
            return sb.toString()
        }catch (e: Exception){
            return e.stackTraceToString()
        }
    }
}