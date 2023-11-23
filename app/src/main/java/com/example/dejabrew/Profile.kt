package com.example.dejabrew



import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfilePage : AppCompatActivity() {

    //    var regularProperty = 1
    lateinit var recyclerView: RecyclerView
    lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        imageList = arrayOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background
        )

        titleList = arrayOf(
            "TitleOne",
            "TitleTwo",
            "TitleThree",
            "TitleFour",
            "TitleFive",
            "TitleSix",
            "TitleSeven",
            "TitleEight",
            "TitleNine",
            "TitleTen"
        )

        recyclerView = findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<DataClass>()
        getData()

//        var newInnerInstance = newInnerClass()
//        newInnerInstance.newInnerFunction(this, "Sample Toast Message")
    }

//    inner class newInnerClass(){
//        var innerProperty : String =""
//
//        fun newInnerFunction(innerContext : Context, innermessage: String){
//            regularProperty++
//            Toast.makeText(innerContext,innermessage, Toast.LENGTH_SHORT).show()
//
//        }
//    }

    private fun getData(){
        for (i in imageList.indices){
            var dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }

        recyclerView.adapter = AdapterClass(dataList)
    }


}
