package uz.itschool.elera.util

import uz.itschool.elera.MainActivity

class API private constructor() {
    private val shared = MainActivity().getSharedPreferences("data", 0)
    private val edit = shared.edit()
    companion object{
        private var instance : API? = null
        fun newInstance(): API {
            if (instance == null){
                instance = API()
            }
            return instance!!
        }
    }




    fun getMentors(){

    }

    fun hasData() {
        val hasData = shared.getString("hasData", "")
        if (hasData == "yes") return










        edit.putString("hasData", "yes").apply()
    }
}