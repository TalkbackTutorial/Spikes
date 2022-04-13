package com.talkbacktutorial.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Entity represents a table within the database
 */
@Entity(tableName = "user_table")
data class User (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val firstName: String,
        val lastName: String,
        val age: Int
        )