package com.frozenproject.sanggauborneo.callback

import com.frozenproject.sanggauborneo.model.PlaceModel

interface IPlaceLoadCallback {
    fun onPlaceLoadSucces(placeModelList: List<PlaceModel>)
    fun onPlaceLoadFailed(message:String)
}