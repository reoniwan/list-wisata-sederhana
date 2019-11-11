package com.frozenproject.sanggauborneo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frozenproject.sanggauborneo.adapter.ListPlaceAdapter
import com.frozenproject.sanggauborneo.callback.IPlaceLoadCallback
import com.frozenproject.sanggauborneo.common.Common
import com.frozenproject.sanggauborneo.model.PlaceModel
import com.google.firebase.database.*


class MainActivity : AppCompatActivity(), IPlaceLoadCallback {
    override fun onPlaceLoadSucces(placeModelList: List<PlaceModel>) {
        placeListMutableLiveData!!.value = placeModelList
    }

    override fun onPlaceLoadFailed(message: String) {
        messageError.value = message
    }


    private var placeListMutableLiveData: MutableLiveData<List<PlaceModel>>?=null
    private lateinit var messageError: MutableLiveData<String>
    private var placeLoadCallback: IPlaceLoadCallback = this


    var recyclerView: RecyclerView?=null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        placeList.observe(this, Observer {
            val listData = it
            val adapter = ListPlaceAdapter(this, listData)
            recyclerView!!.adapter = adapter
        })

        initView()

    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_wisata_alam)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }


    private val placeList: LiveData<List<PlaceModel>>
        get() {
            if (placeListMutableLiveData == null)
            {
                placeListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadPlaceList()
            }

            return placeListMutableLiveData!!
        }

    private fun loadPlaceList() {
        val tempList = ArrayList<PlaceModel>()
        val placeRef = FirebaseDatabase.getInstance().getReference(Common.PLACE_REF)
        placeRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(alertError: DatabaseError) {
                placeLoadCallback.onPlaceLoadFailed(alertError.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnapshot in snapshot!!.children)
                {
                    val model = itemSnapshot.getValue<PlaceModel>(PlaceModel::class.java)
                    tempList.add(model!!)
                }

                placeLoadCallback.onPlaceLoadSucces(tempList)
            }

        })
    }


}
