package com.frozenproject.sanggauborneo.model

class PlaceModel {

    var name : String? = null
    var location_id : String? = null
    var description : String? = null
    var address : String? = null
    var image : String? = null

    constructor()
    constructor(
        name: String?,
        id: String?,
        description: String?,
        address: String?,
        image: String?
    ) {
        this.name = name
        this.location_id = id
        this.description = description
        this.address = address
        this.image = image
    }


}