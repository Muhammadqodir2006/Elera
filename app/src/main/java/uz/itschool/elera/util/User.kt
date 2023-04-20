package uz.itschool.elera.util

class User(var username:String, var email:String, var password:String, var firstName:String, var lastName:String, var gender:Boolean, var image:String, val courses:ArrayList<Course>) : java.io.Serializable{
    var bookMarks = arrayListOf<Course>()
}