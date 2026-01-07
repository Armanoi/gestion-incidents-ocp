package com.example.gestiondesincidents



import android.content.Context
import android.database.Cursor

class AuthManager(private val context: Context) {
    private val dbHelper: DBHelper = DBHelper(context)

    fun login(email: String, password: String): String? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "users",
            arrayOf("role"),
            "email = ? AND password = ?",
            arrayOf(email, password),
            null, null, null
        )
        return if (cursor != null && cursor.moveToFirst()) {
            val role = cursor.getString(0)
            cursor.close()
            role
        } else {
            cursor?.close()
            null
        }
    }
    fun register(email: String, password: String, role: String): Boolean {
        return dbHelper.insertUser(email, password, role)
    }
}