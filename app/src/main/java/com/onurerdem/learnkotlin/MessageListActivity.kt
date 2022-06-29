package com.onurerdem.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.onurerdem.learnkotlin.adapter.MessageListAdapter
import com.onurerdem.learnkotlin.databinding.ActivityMessageListBinding
import com.onurerdem.learnkotlin.model.MessageItem
import com.onurerdem.learnkotlin.room.MessageDao
import com.onurerdem.learnkotlin.room.MessageDatabase

class MessageListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessageListBinding
    private var messageListAdapter = MessageListAdapter()
    private var messageList: ArrayList<MessageItem> = arrayListOf()
    private lateinit var messageDao: MessageDao
    private var messageLiveData: MutableLiveData<String> = MutableLiveData()

    var dump = LogDumpGetUnique().replace(",","\n")
    var dump2 = dump.replace(";","\n")
    var dump3 = ""
    var tempId = ""
    var tempName = ""
    var tempUserName = ""
    var tempEMail = ""
    var reDump = ""
    var dump4 = ""
    var dump6 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, MessageDatabase::class.java, "message").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        messageDao = db.messageDao()


        /*messageList.add(MessageItem("Yunus","Neredesin sen",false,"13:34"))
        messageList.add(MessageItem("Enes","Nerede kaldın",true,"12.44"))
        messageList.add(MessageItem("Adna","Hayde",true,"13:34"))
        messageList.add(MessageItem("Onur erdem","Geldin mi",true,"13:34"))
        messageList.add(MessageItem("Emre","Ekmek al",false,"13:34"))*/

        val arrSplit: List<String> = dump2.split("\n")
        for (i in arrSplit) {
            if(i.contains("id=") == false){
                dump3 += i
            }
            if(i.contains("id=")){
                tempId += i
            }
            if(i.contains("id=") == false && i.contains("username=") == false && i.contains("email=") == false){
                tempName += i
            }
            if(i.contains("username=")){
                tempUserName += i
            }
            if(i.contains("email=")){
                tempEMail += i
            }
        }
        var temp1 = tempId.trim()
        var temp2 = tempName.trim()
        var temp3 = tempUserName.trim()
        var temp4 = tempEMail.trim()
        //println(temp1)
        var tempListId = temp1.split(" ").toTypedArray()
        var tempListName = temp2.split("name=").toTypedArray()
        var tempListUserName = temp3.split(" ").toTypedArray()
        var tempListEMail = temp4.split(" ").toTypedArray()
        var dumpList = dump3.split(" name=").toTypedArray()
        for(x in 0..dumpList.size - 1){
            reDump += dumpList.get(x) + " " + tempListId.get(x) + "\nname="
        }
        //println("-\n" + reDump.substring(0,reDump.length - 5) + "\n-\n")
        val arrSplit2: List<String> = dump3.split(" name=")
        for (j in arrSplit2) {
            //if(j.contains(" name=")){
            //for(k in arrSplit2){
            //if(k == j){
            dump4 += j + "\nname="
            //}
            //}
            //}
        }
        //print(dump4.substring(0,dump4.length - 5))
        //println("-")
        var dump5 = dump4.split("\n").toTypedArray()
        for (k in dump5){
            dump6 += k + "\n"
        }
        //println(dump6.substring(0,dump6.length - 6))

        binding.apply {
            rvMessagelist.layoutManager = LinearLayoutManager(this@MessageListActivity, LinearLayoutManager.VERTICAL, false)
            rvMessagelist.adapter = messageListAdapter

            messageListAdapter.listeyiDoldur(messageList)

            messageListAdapter.messageItemClickListener = {
                messageDao.deleteMessageItem(it)
            }

            messageDao.getAllMessage().observe(this@MessageListActivity,{
                it?.let { messageItem ->
                    messageList.clear()
                    messageItem.forEach {
                        messageList.add(it)
                    }
                    messageListAdapter.listeyiDoldur(messageList)
                }
            })

            for (l in 0..tempListId.size - 1){
                messageDao.saveMessage(MessageItem(userName = tempListName.get(l + 1).trim(),userLastMessage = tempListUserName.get(l).trim() + "\n" + tempListEMail.get(l).trim(),isRead = false, messageTime = tempListId.get(l).trim()))
            }

            btnAdded.setOnClickListener {
                //insert
                messageDao.saveMessage(MessageItem(userName = edtItem.text.toString(),userLastMessage = "XXX",isRead = false, messageTime = "12:45"))
                //messageList.add(MessageItem(edtItem.text.toString(),"XXX",false,"12:45"))
                //messageListAdapter.listeyiDoldur(messageList)
            }
        }
    }
    fun LogDumpGetUnique(): String {
        var log_dump : String = "name=John Trust, username=john3, email=john3@gmail.com, id=434453; name=Hannah Smith, username=hsmith, email=hsm@test.com, id=23312; name=Hannah Smith, username=hsmith, id=3223423, email=hsm@test.com; name=Robert M, username=rm44, id=222342, email=rm@me.com; name=Robert M, username=rm4411, id=5535, email=rm@me.com; name=Susan Vee, username=sv55, id=443432, email=susanv123@me.com; name=Robert Nick, username=rnick33, id=23432, email=rnick@gmail.com; name=Robert Nick II, username=rnickTemp34, id=23432, email=rnick@gmail.com; name=Susan Vee, username=sv55, id=443432, email=susanv123@me.com;"

        //var logDump = ""
        /*val arrSplit: List<String> = log_dump.split("[, ; ]+")
        for (i in arrSplit) {
            if(i.contains("id=")){
                logDump = i
            }
        }*/
        //logDump.replace(",","\n")

        return log_dump;
    }
}