package com.example.clonepaging.network

data class RickAndMortyList(val info: Info, val results:  List<CharacterData>){
    //B3 Tạo CLass
    data class CharacterData(val name: String? , val species: String?, val image: String)
    data class Info(val count: Int?, val  pages: String?, val next: String?, val prev: String?)

}