package com.onurerdem.learnkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.onurerdem.learnkotlin.model.MessageItem

@Dao
interface MessageDao {

    @Insert
    fun saveMessage(vararg messageaItem: MessageItem)

    @Query("SELECT * FROM messageitem")
    fun getAllMessage() : LiveData<List<MessageItem>>

    @Query("DELETE FROM messageitem WHERE uid = :uId")
    fun deleteMessageItem(uId: Int) : Int

}