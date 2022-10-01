package com.mobile.moa.my

import com.google.gson.annotations.SerializedName

//{
//  "candidates":
//    [
//      {
//        "formatted_address": "140 George St, The Rocks NSW 2000, Australia",
//        "geometry":
//          {
//            "location": { "lat": -33.8599358, "lng": 151.2090295 },
//            "viewport":
//              {
//                "northeast":
//                  { "lat": -33.85824377010728, "lng": 151.2104386798927 },
//                "southwest":
//                  { "lat": -33.86094342989272, "lng": 151.2077390201073 },
//              },
//          },
//        "name": "Museum of Contemporary Art Australia",
//        "opening_hours": { "open_now": false },
//        "rating": 4.4,
//      },
//    ],
//  "status": "OK",
//}

data class SchoolResponse(
    @SerializedName("candidates") val candidates: List<Candidates>,
    @SerializedName("status") val status: String
)

data class Candidates(
    @SerializedName("formatted_address") val formatted_address: String,
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("name") val name: String,
    @SerializedName("opening_hours") val opening_hours: Opening,
    @SerializedName("rating") val rating: Number
)

data class Geometry(
    @SerializedName("location") val location: Location,
)

data class Location(
    @SerializedName("lat") val lat: Number,
    @SerializedName("lng") val lng: Number
)

data class Opening(
    @SerializedName("open_now") val open_now: Boolean
)
