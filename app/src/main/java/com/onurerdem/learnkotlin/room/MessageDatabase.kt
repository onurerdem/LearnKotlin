package com.onurerdem.learnkotlin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onurerdem.learnkotlin.model.MessageItem

@Database(entities = [MessageItem::class], version = 4)
abstract class MessageDatabase: RoomDatabase() {
    abstract fun messageDao() : MessageDao
}