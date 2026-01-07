package com.example.gestiondesincidents

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "IncidentManagement.db"
        private const val DATABASE_VERSION = 1

        // Tables Utilisateur
        private const val TABLE_UTILISATEUR = "Utilisateur"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOM = "nom"
        private const val COLUMN_PRENOM = "prenom"
        private const val COLUMN_LOGIN = "login"
        private const val COLUMN_PASSWORD = "password"

        // Tables Users (Admins et IT et user)
        private const val TABLE_USERS = "users"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_ROLE = "role"

        // Tables Incidents
        private const val TABLE_INCIDENTS = "incidents"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_STATUS = "status"

        // Tables IT Users
        private const val TABLE_IT_USERS = "ITUsers"
        private const val COLUMN_PSW = "psw"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Création des tables
        val createTableUtilisateur = """
            CREATE TABLE IF NOT EXISTS $TABLE_UTILISATEUR (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOM TEXT NOT NULL,
                $COLUMN_PRENOM TEXT NOT NULL,
                $COLUMN_LOGIN TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableUtilisateur)

        val createUsersTable = """
            CREATE TABLE IF NOT EXISTS $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_ROLE TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createUsersTable)

        val createIncidentTable = """
    CREATE TABLE Incidents (
        titre TEXT PRIMARY KEY,
        description TEXT
       
    );
""".trimIndent()

        db.execSQL(createIncidentTable)

        val createITUsersTable = """
            CREATE TABLE IF NOT EXISTS $TABLE_IT_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOM TEXT,
                $COLUMN_PRENOM TEXT,
                $COLUMN_LOGIN TEXT,
                $COLUMN_PSW TEXT,
                $COLUMN_ROLE TEXT
            )
        """.trimIndent()
        db.execSQL(createITUsersTable)

        // Insertion d'un admin par défaut
        val insertAdmin = "INSERT INTO $TABLE_USERS ($COLUMN_EMAIL, $COLUMN_PASSWORD, $COLUMN_ROLE) VALUES ('admin@ocp.com', 'admin123', 'admin')"
        db.execSQL(insertAdmin)
        val insertUser = "INSERT INTO $TABLE_USERS ($COLUMN_EMAIL, $COLUMN_PASSWORD, $COLUMN_ROLE) VALUES ('user@ocp.com','user123', 'user')"
        db.execSQL(insertUser)
        val insertIT = "INSERT INTO $TABLE_USERS ($COLUMN_EMAIL, $COLUMN_PASSWORD, $COLUMN_ROLE) VALUES ('IT@ocp.com', 'IT123', 'IT')"
        db.execSQL(insertIT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_UTILISATEUR")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_INCIDENTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_IT_USERS")
        onCreate(db)
    }

    // 🔹 Ajouter un utilisateur
    fun ajouterUtilisateur(nom: String, prenom: String, login: String, password: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOM, nom)
            put(COLUMN_PRENOM, prenom)
            put(COLUMN_LOGIN, login)
            put(COLUMN_PASSWORD, password)
        }

        return try {
            val result = db.insert(TABLE_UTILISATEUR, null, values)
            db.close()
            if (result == -1L) {
                Log.e("DBHelper", "Échec de l'insertion de l'utilisateur")
                false
            } else {
                Log.d("DBHelper", "Utilisateur ajouté avec succès ID: $result")
                true
            }
        } catch (e: Exception) {
            Log.e("DBHelper", "Erreur lors de l'insertion de l'utilisateur", e)
            false
        }
    }

    // 🔹 Modifier un utilisateur
    fun modifierUtilisateur(id: Int, nom: String, prenom: String, login: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOM, nom)
            put(COLUMN_PRENOM, prenom)
            put(COLUMN_LOGIN, login)
            put(COLUMN_PASSWORD, password)
        }

        val result = db.update(TABLE_UTILISATEUR, values, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    // 🔹 Récupérer tous les utilisateurs
    fun getAllUtilisateurs(): List<Utilisateur> {
        val utilisateurs = mutableListOf<Utilisateur>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_UTILISATEUR", null)

        if (cursor.moveToFirst()) {
            do {
                val utilisateur = Utilisateur(
                    id = cursor.getInt(0),
                    nom = cursor.getString(1),
                    prenom = cursor.getString(2),
                    login = cursor.getString(3),
                    password = cursor.getString(4)
                )
                utilisateurs.add(utilisateur)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        Log.d("DBHelper", "Nombre d'utilisateurs récupérés: ${utilisateurs.size}")

        return utilisateurs
    }

    // 🔹 Supprimer un utilisateur
    fun supprimerUtilisateur(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_UTILISATEUR, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun ajouterIncident(titre: String, description: String ): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("titre", titre)
            put("description", description)
        }
        val result = db.insert("incidents", null, values)
        db.close()
        return result != -1L
    }



    fun insertUser(email: String, password: String, role: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_PASSWORD, password)
        values.put(COLUMN_ROLE, role)
        val result = db.insert(TABLE_USERS, null, values)
        return result != -1L
    }


    fun insertIncident(userId: Int, description: String, status: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_ID, userId)
        values.put(COLUMN_DESCRIPTION, description)
        values.put(COLUMN_STATUS, status)
        val result = db.insert(TABLE_INCIDENTS, null, values)
        return result != -1L
    }

    fun modifierIncident(id: Int, description: String, status: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("description", description)
            put("status", status)
        }

        val result = db.update("incidents", values, "id=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun supprimerIncident(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete("incidents", "id=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun getAllIncidents(): Cursor? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM incidents", null)

        Log.d("DBHelper", "Nombre d'incidents: ${cursor.count}")

        return cursor
    }




    fun ajouterAdmin() {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("email", "admin@ocp.com")
            put("password", "admin123")
            put("role", "admin")
        }
        db.insert("users", null, values)
        db.close()
    }
    fun ajouterUser() {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("email", "user@ocp.com")
            put("password", "user123")
            put("role", "user")
        }
        db.insert("users", null, values)
        db.close()
    }

    fun ajouterIT() {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("email", "IT@ocp.com")
            put("password", "IT123")
            put("role", "IT")
        }
        db.insert("users", null, values)
        db.close()
    }


    // ✅ Ajouter un IT User
    fun addITUser(user: ITUser): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nom", user.nom)
        values.put("prenom", user.prenom)
        values.put("login", user.login)
        values.put("psw", user.password) // Assurez-vous que le nom de colonne "psw" correspond à votre base

        val result = db.insert("ITUsers", null, values)
        return result != -1L // Retourne vrai si l'insertion réussit

        Log.d("DBHelper", "Ajout de l'utilisateur : $user")
        Log.d("DBHelper", "Résultat de l'opération : $result")
    }

    // ✅ Modifier un IT User
    fun updateITUser(user: ITUser): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nom", user.nom)
            put("prenom", user.prenom)
            put("login", user.login)
            put("psw", user.password)
        }
        val result = db.update("ITUsers", values, "id = ?", arrayOf(user.id.toString()))
        db.close()
        return result > 0
    }

    // ✅ Supprimer un IT User
    fun deleteITUser(userId: Int): Boolean {
        val db = writableDatabase
        val result = db.delete("ITUsers", "id = ?", arrayOf(userId.toString()))
        db.close()
        return result > 0
    }
    fun getAllIncidentes(): List<Incident> {
        val incidentList = mutableListOf<Incident>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM incidents WHERE statut = 'Non traité'", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
                val statut = cursor.getString(cursor.getColumnIndexOrThrow("statut"))
                val userId= cursor.getInt(cursor.getColumnIndexOrThrow("userId"))
                incidentList.add(Incident(id, userId ,description, statut))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return incidentList
    }

    fun marquerIncidentCommeTraité(id: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("statut", "Traité")
        db.update("incidents", values, "id = ?", arrayOf(id.toString()))
        db.close()
    }


    fun getAllITUsers(): List<ITUser> {
        val userList = mutableListOf<ITUser>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ITUsers", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"))
                val prenom = cursor.getString(cursor.getColumnIndexOrThrow("prenom"))
                val login = cursor.getString(cursor.getColumnIndexOrThrow("login"))
                val password = cursor.getString(cursor.getColumnIndexOrThrow("psw")) // Change "password" à "psw"

                userList.add(ITUser(id, nom, prenom, login, password))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return userList
    }
}