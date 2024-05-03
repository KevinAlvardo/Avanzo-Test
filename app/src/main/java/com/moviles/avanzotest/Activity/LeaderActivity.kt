package com.moviles.avanzotest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.moviles.avanzotest.Adapter.LeaderAdapter
import com.moviles.avanzotest.Domain.UserModel
import com.moviles.avanzotest.R
import com.moviles.avanzotest.databinding.ActivityLeaderBinding
import com.ismaeldivita.chipnavigation.R

class LeaderActivity : AppCompatActivity() {
    lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy {LeaderAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window:Window= this@LeaderActivity.window
        window.statusBarColor=ContextCompat.getColor(this@LeaderActivity, com.moviles.avanzotest.R.color.grey)


        binding.apply {
            scoreTop1Txt.text=loadData().get(0).score.toString()
            scoreTop2Txt.text=loadData().get(1).score.toString()
            scoreTop3Txt.text=loadData().get(2).score.toString()
            titleTop1Txt.text=loadData().get(0).name
            titleTop2Txt.text=loadData().get(1).name
            titleTop3Txt.text=loadData().get(2).name

        val drawableResourceId1: Int=binding.root.resources.getIdentifier(
            loadData().get(0).pic,"drawable",binding.root.context.packageName
        )
            Glide.with(root.context)
                .load(drawableResourceId1)
                .into(pic1)

            val drawableResourceId2: Int=binding.root.resources.getIdentifier(
                loadData().get(1).pic,"drawable",binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId2)
                .into(pic1)

            val drawableResourceId3: Int=binding.root.resources.getIdentifier(
                loadData().get(2).pic,"drawable",binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId3)
                .into(pic1)


            bottomMenu.setItemSelected(R.id.Board)
            bottomMenu.setOnItemSelectedListener{
                if(it==R.id.home){
                    startActivity(intent(this@LeaderActivity,MainActivity::class.java))
                }
            }

        var list:MutableList<UserModel> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)
            leaderAdapter.differ.submitList(list)

            leaderView.apply {
                layoutManager=LinearLayoutManager(this@LeaderActivity)
                adapter=leaderAdapter
            }
        }
    }
    // lista de ejemplo aca se puede usar una lista de alguna api
    private fun loadData(): MutableList<UserModel>{
        val users: MutableList<UserModel> = mutableListOf()
        users.add(UserModel(1,"Sophia","person1",4850))
        users.add(UserModel(2,"Daniel","person2",4560))
        users.add(UserModel(3,"James","person3",3873))
        users.add(UserModel(4,"John","person4",3250))
        users.add(UserModel(5,"Emily","person5",3015))
        users.add(UserModel(6,"David","person6",2970))
        users.add(UserModel(7,"Sarah","person7",2870))
        users.add(UserModel(8,"Michael","person8",2670))
        users.add(UserModel(9,"Valentina","person9",2380))
        users.add(UserModel(10,"Sarah","person10",2380))
        return users
    }
}