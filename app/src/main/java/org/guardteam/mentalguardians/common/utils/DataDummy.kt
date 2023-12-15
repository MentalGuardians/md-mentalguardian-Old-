package org.guardteam.mentalguardians.common.utils

import org.guardteam.mentalguardians.domain.model.ContentData
import org.guardteam.mentalguardians.domain.model.Day
import org.guardteam.mentalguardians.domain.model.History
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.presentation.profile.data.Profile
import org.guardteam.mentalguardians.presentation.transaction.data.Transaction
import java.time.DayOfWeek
import java.time.LocalDate


object DataDummy {
//    val contentData: List<ContentData> by lazy {
//        (1..10).map { index ->
//            ContentData(
//                id = index,
//                title = "Title $index",
//                author = "Author $index",
//                duration = "2-3 min",
//                rating = 4.8,
//                views = "24k",
//                desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac lobortis ante. Morbi vulputate nunc a nibh mattis euismod. Proin sed faucibus magna. Sed ut sollicitudin massa. Nulla velit tellus, sodales vel cursus ut, sagittis vel nunc. Proin iaculis massa et interdum vestibulum. Vivamus vitae nibh metus. Sed in dictum augue. Curabitur venenatis purus tellus, et efficitur felis elementum quis. Proin varius, neque ut elementum accumsan, ex magna cursus felis, vel tincidunt nisl diam vel nisi. Sed luctus velit at metus porta laoreet. Suspendisse quis sapien non lacus eleifend aliquam quis a metus. Aliquam placerat aliquam massa, a euismod nunc mattis eget. Integer congue lacinia mauris, nec laoreet ipsum tincidunt vitae.\n" +
//                        "\n" +
//                        "Morbi ut nulla hendrerit, lobortis quam a, tempor urna. Nam pulvinar tellus non tempus ultricies. Praesent pellentesque augue vel massa ultricies laoreet. Cras luctus magna non risus gravida viverra rhoncus at libero. Maecenas dictum, turpis sed malesuada aliquet, diam ex feugiat mi, in ornare risus ligula quis lorem. Curabitur in elit eget ipsum placerat eleifend id at tellus. Aenean eget felis a est iaculis lacinia. In auctor odio dolor, a rutrum arcu pharetra nec. Praesent dapibus justo sit amet tortor laoreet, sed tempus purus cursus. Fusce aliquet eros sit amet sem egestas aliquet quis non nunc. Suspendisse lectus ligula, posuere vel lectus id, placerat accumsan tortor. Donec sodales sem massa, nec egestas urna fringilla vitae. Duis iaculis ex sit amet ipsum porttitor iaculis."
//            )
//        }
//    }

    val therapistData: List<Therapist> by lazy {
        (1..10).map { index ->
            Therapist(
                id = index,
                name = "Name $index",
                primaryFocus = "Family therapist",
                rating = 4.8,
                cost = "200k",
                experience = 7,
                client = 32,
                session = 556,
                secondaryFocus = listOf(
                    "Anxiety",
                    "Bulimia",
                    "Depression",
                    "Relationship",
                    "Anger"
                ),
                about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac lobortis ante. Morbi vulputate nunc a nibh mattis euismod. Proin sed faucibus magna. Sed ut sollicitudin massa. Nulla velit tellus, sodales vel cursus ut, sagittis vel nunc. Proin iaculis massa et interdum vestibulum. Vivamus vitae nibh metus. Sed in dictum augue. Curabitur venenatis purus tellus, et efficitur felis elementum quis. Proin varius, neque ut elementum accumsan, ex magna cursus felis, vel tincidunt nisl diam vel nisi. Sed luctus velit at metus porta laoreet. Suspendisse quis sapien non lacus eleifend aliquam quis a metus. Aliquam placerat aliquam massa, a euismod nunc mattis eget. Integer congue lacinia mauris, nec laoreet ipsum tincidunt vitae.\n" +
                        "\n" +
                        "Morbi ut nulla hendrerit, lobortis quam a, tempor urna. Nam pulvinar tellus non tempus ultricies. Praesent pellentesque augue vel massa ultricies laoreet. Cras luctus magna non risus gravida viverra rhoncus at libero. Maecenas dictum, turpis sed malesuada aliquet, diam ex feugiat mi, in ornare risus ligula quis lorem. Curabitur in elit eget ipsum placerat eleifend id at tellus. Aenean eget felis a est iaculis lacinia. In auctor odio dolor, a rutrum arcu pharetra nec. Praesent dapibus justo sit amet tortor laoreet, sed tempus purus cursus. Fusce aliquet eros sit amet sem egestas aliquet quis non nunc. Suspendisse lectus ligula, posuere vel lectus id, placerat accumsan tortor. Donec sodales sem massa, nec egestas urna fringilla vitae. Duis iaculis ex sit amet ipsum porttitor iaculis."
            )
        }
    }

    val historyData: List<History> by lazy {
        (1..20).map { index ->
            History(
                id = index,
                date = "30 November 2023",
                time = "21.38",
                diagnose = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            )
        }
    }
    val dataTransaction: List<Transaction> by lazy {
        (1..12).map { index ->
            Transaction(
                id = index,
                name = "Name $index",
                date = "22 November 2023",
                time = "20.30",
                status = "Scheduled"
            )
        }
    }


    val dataProfile = listOf(
        Profile(
            1,
            "Ihfansyahpedo9@gmail.com",
            "Ihfansyah Pedo",
            "Jl. Laksda Adisucipto, Papringan, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281",
            "123456789"
        )
    )


    val listDate: List<Day> by lazy {
        val currentDate = LocalDate.now()
        val startOfSecondWeek = currentDate.plusDays(1)
        (0..6).map {
            val currentDateInLoop = startOfSecondWeek.plusDays(it.toLong())
            if (currentDateInLoop.dayOfWeek != DayOfWeek.SATURDAY && currentDateInLoop.dayOfWeek != DayOfWeek.SUNDAY) {
                Day(
                    date = currentDateInLoop.toString(),
                    day = currentDateInLoop.dayOfMonth,
                    dayName = currentDateInLoop.dayOfWeek.toString().substring(0, 3)
                )
            } else {
                null
            }
        }.filterNotNull()
    }

    val listTime: List<Pair<String, String>> = listOf(
        Pair("09:00", "am"),
        Pair("11:00", "am"),
        Pair("01:00", "pm"),
        Pair("03:00", "pm"),
        Pair("05:00", "pm"),
        Pair("07:00", "pm"),
    )

}