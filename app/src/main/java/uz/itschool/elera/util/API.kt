package uz.itschool.elera.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.elera.MainActivity

class API private constructor(context: Context) {
    private val shared = context.getSharedPreferences("data", 0)
    private val edit = shared.edit()
    private val gson = Gson()

    private val mentorsString = "mentors"
    private val usersString = "users"
    private val coursesString = "courses"
    private val reviewsString = "reviews"

    companion object {
        private var instance: API? = null
        fun newInstance(context: Context): API {
            if (instance == null) {
                instance = API(context)
            }
            return instance!!
        }
    }
    fun hasData() {
        val hasData = shared.getString("hasData", "")
        if (hasData == "yes") return

        val user1 = User(
            "Ali1202",
            "ali@email.com",
            "11111111",
            "Ali",
            "Aliyev",
            true,
            "https://m.timesofindia.com/photo/83890830/83890830.jpg",
            arrayListOf()
        )
        val user2 = User(
            "Benny",
            "benny@email.com",
            "11111111",
            "Benny",
            "Spanbauer",
            true,
            "https://img.freepik.com/free-photo/portrait-smiling-charming-young-man-grey-t-shirt-standing-against-plain-background_23-2148213406.jpg?w=360",
            arrayListOf()
        )
        val user3 = User(
            "Freida1",
            "freida@email.com",
            "11111111",
            "Freida",
            "Varnes",
            false,
            "https://media.istockphoto.com/id/1348830922/photo/shot-of-an-attractive-young-businesswomen-standing-alone-outside-with-her-arms-folded.jpg?b=1&s=170667a&w=0&k=20&c=sIsWIHDysN_AI0QYEqfxkySmTVspbtDP-OJgfdBk1pM=",
            arrayListOf()
        )
        val user4 = User(
            "ClintonBest",
            "clioton@email.com",
            "11111111",
            "Clinton",
            "Mcclure",
            true,
            "data:image/jpeg;base64,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",
            arrayListOf()
        )
        val user5 = User(
            "Chieko",
            "chieko@email.com",
            "11111111",
            "Chieko",
            "Chute",
            false,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_PdJ0Nw5ADvr9NzVeuogCLnxzu_XJGYn20tShrMXDZvVqQgPUx3z6b_Ubz-DCWsZc4ts&usqp=CAU",
            arrayListOf()
        )
        val user6 = User(
            "Doniyor",
            "doniyor@email.com",
            "11111111",
            "Doniyor",
            "Ziyodov",
            true,
            "https://media.istockphoto.com/id/1048695348/photo/cheerful-young-man-pointing-at-himself.jpg?s=612x612&w=0&k=20&c=36UH2zZDheSZM4FykeDBVYCpi9MtibK6HYptC2B0aNs=",
            arrayListOf()
        )
        val user7 = User(
            "Shamsiddin",
            "shamsiddin@email.com",
            "11111111",
            "Shamsiddin",
            "Tohirov",
            true,
            "https://media.istockphoto.com/id/1200677760/photo/portrait-of-handsome-smiling-young-man-with-crossed-arms.jpg?s=612x612&w=0&k=20&c=g_ZmKDpK9VEEzWw4vJ6O577ENGLTOcrvYeiLxi8mVuo=",
            arrayListOf()
        )
        val user8 = User(
            "Hasan007",
            "hasan@email.com",
            "11111111",
            "Hasan",
            "Bo'ronov",
            true,
            "https://image.cnbcfm.com/api/v1/image/106930629-1629399630371-gettyimages-494691340-87856868.jpeg?v=1629399760",
            arrayListOf()
        )
        val user9 = User(
            "Lucy$$$",
            "lucy@email.com",
            "11111111",
            "Lucy",
            "Brown",
            false,
            "https://www.shutterstock.com/image-photo/confident-smiling-female-employee-260nw-339668693.jpg",
            arrayListOf()
        )
        val user10 = User(
            "Linda23",
            "linda@email.com",
            "11111111",
            "Linda",
            "Bauer",
            false,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtVQmNf7ZXkCNZu9-seEv2_dsQtvBgdDyx0Db-U1zaoOibxjbTZYzXRW8vgwUfAWOrrtw&usqp=CAU",
            arrayListOf()
        )

        val mentor1 = Mentor(
            "Teador",
            "Snowden",
            "3D Designer",
            "https://img.freepik.com/premium-photo/smiling-man-with-crossed-arms_23-2147574143.jpg"
        )
        val mentor2 = Mentor(
            "Jacob",
            "Snowden",
            "Business manager",
            "https://img.freepik.com/free-photo/close-up-confident-male-employee-white-collar-shirt-smiling-camera-standing-self-assured-against-studio-background_1258-26761.jpg"
        )
        val mentor3 = Mentor(
            "Claire",
            "Snowden",
            "Flutter developer",
            "https://media.istockphoto.com/id/1324786380/photo/young-handsome-smiling-man-in-brown-shirt-and-glasses-feeling-confident-isolated-on-gray.jpg?s=612x612&w=0&k=20&c=EWqUQzPW-4jH8rri6eQAeomVfeizC2ead7YCl28KhXU=    "
        )
        val mentor4 = Mentor(
            "Wade",
            "Snowden",
            "UI freelancer",
            "https://bcbstwelltuned.com/wp-content/uploads/2016/12/employeementalhealth.jpg"
        )
        val mentor5 = Mentor(
            "Katy",
            "Snowden",
            "AI developer in Google",
            "https://unboxed-web-production.s3.amazonaws.com/media/images/female-employee-taking-part-in-a-virtual-ba.max-1028x464_cWtEF3e.jpg"
        )

        val course1 = Course(
            "3D design illustration",
            Category.ThreeDDESIGN,
            "",
            arrayListOf(48),
            2,
            30,
            true,
            mentor1,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course2 = Course(
            "3D design",
            Category.ThreeDDESIGN,
            "",
            arrayListOf(80, 48),
            2,
            15,
            false,
            mentor1,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course3 = Course(
            "CRM management",
            Category.BUSINESS,
            "",
            arrayListOf(20),
            2,
            30,
            true,
            mentor2,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course4 = Course(
            "Flutter mobile",
            Category.MOBILE,
            "",
            arrayListOf(48),
            2,
            24,
            false,
            mentor3,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course5 = Course(
            "Management",
            Category.BUSINESS,
            "",
            arrayListOf(45, 15),
            3,
            30,
            true,
            mentor2,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course6 = Course(
            "Flutter desktop",
            Category.MOBILE,
            "",
            arrayListOf(48),
            2,
            30,
            true,
            mentor3,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course7 = Course(
            "Website UI/UX",
            Category.UIUX,
            "",
            arrayListOf(85, 50),
            2,
            0,
            false,
            mentor4,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course8 = Course(
            "AI for complete beginner",
            Category.AI,
            "",
            arrayListOf(48),
            2,
            15,
            true,
            mentor5,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course9 = Course(
            "AIa (advanced)",
            Category.AI,
            "",
            arrayListOf(60, 30),
            2,
            30,
            true,
            mentor5,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course10 = Course(
            "UI/UX",
            Category.UIUX,
            "",
            arrayListOf(75),
            2,
            45,
            false,
            mentor4,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )

        val reviews = arrayListOf(
            Review("Zo'r, malades", 3, user1, course1),
            Review("Zo'r, malades", 4, user2, course1),
            Review("Zo'r, malades", 5, user3, course1),
            Review("Zo'r, malades", 4, user4, course1),
            Review("Zo'r, malades", 3, user5, course2),
            Review("Zo'r, malades", 4, user6, course2),
            Review("Zo'r, malades", 2, user7, course2),
            Review("Zo'r, malades", 4, user8, course3),
            Review("Zo'r, malades", 1, user9, course3),
            Review("Zo'r, malades", 4, user10, course3),
            Review("Zo'r, malades", 5, user1, course3),
            Review("Zo'r, malades", 4, user2, course4),
            Review("Zo'r, malades", 3, user3, course4),
            Review("Zo'r, malades", 4, user4, course5),
            Review("Zo'r, malades", 2, user5, course5),
            Review("Zo'r, malades", 4, user6, course6),
            Review("Zo'r, malades", 4, user7, course6),
            Review("Zo'r, malades", 5, user8, course6),
            Review("Zo'r, malades", 4, user9, course6),
            Review("Zo'r, malades", 5, user10, course7),
            Review("Zo'r, malades", 4, user1, course7),
            Review("Zo'r, malades", 3, user2, course7),
            Review("Zo'r, malades", 4, user3, course7),
            Review("Zo'r, malades", 2, user4, course7),
            Review("Zo'r, malades", 4, user5, course8),
            Review("Zo'r, malades", 1, user6, course8),
            Review("Zo'r, malades", 5, user7, course9),
            Review("Zo'r, malades", 4, user8, course9),
            Review("Zo'r, malades", 3, user9, course9),
            Review("Zo'r, malades", 4, user10, course9),
            Review("Zo'r, malades", 4, user1, course9),
            Review("Zo'r, malades", 4, user2, course9),
            Review("Zo'r, malades", 5, user3, course10),
            Review("Zo'r, malades", 2, user4, course10),
            Review("Zo'r, malades", 5, user5, course10),
        )
        val users =
            arrayListOf(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10)
        val mentors = arrayListOf(mentor1, mentor2, mentor3, mentor4, mentor5)
        val courses = arrayListOf(
            course1,
            course2,
            course3,
            course4,
            course5,
            course6,
            course7,
            course8,
            course9,
            course10
        )

        edit.putString(reviewsString, gson.toJson(reviews))
        edit.putString(usersString, gson.toJson(users))
        edit.putString(mentorsString, gson.toJson(mentors))
        edit.putString(coursesString, gson.toJson(courses))

        edit.putString("hasData", "yes").apply()
    }
    fun getMentors(): ArrayList<Mentor> {
        val data: String = shared.getString(mentorsString, "")!!
        val typeToken = object : TypeToken<ArrayList<Mentor>>() {}.type
        if (data == "") return ArrayList()
        return gson.fromJson(data, typeToken)
    }
    fun getUsers(): ArrayList<User> {
        val data: String = shared.getString(usersString, "")!!
        val typeToken = object : TypeToken<ArrayList<User>>() {}.type
        if (data == "") return ArrayList()
        return gson.fromJson(data, typeToken)
    }
    fun getCourses(): ArrayList<Course> {
        val data: String = shared.getString(coursesString, "")!!
        val typeToken = object : TypeToken<ArrayList<Course>>() {}.type
        if (data == "") return ArrayList()
        return gson.fromJson(data, typeToken)
    }
    fun getReviews(): ArrayList<Review> {
        val data: String = shared.getString(reviewsString, "")!!
        val typeToken = object : TypeToken<ArrayList<Review>>() {}.type
        if (data == "") return ArrayList()
        return gson.fromJson(data, typeToken)
    }


}