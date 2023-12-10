package org.guardteam.mentalguardians.common.utils

import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.presentation.history.data.History
import org.guardteam.mentalguardians.presentation.transaction.data.Transaction

object DataDummy {
    val contentData: List<Content> by lazy {
        (1..10).map { index ->
            Content(
                id = index,
                title = "Title $index",
                author = "Author $index",
                duration = "2-3 min",
                rating = 4.8,
                views = "24k",
                desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac lobortis ante. Morbi vulputate nunc a nibh mattis euismod. Proin sed faucibus magna. Sed ut sollicitudin massa. Nulla velit tellus, sodales vel cursus ut, sagittis vel nunc. Proin iaculis massa et interdum vestibulum. Vivamus vitae nibh metus. Sed in dictum augue. Curabitur venenatis purus tellus, et efficitur felis elementum quis. Proin varius, neque ut elementum accumsan, ex magna cursus felis, vel tincidunt nisl diam vel nisi. Sed luctus velit at metus porta laoreet. Suspendisse quis sapien non lacus eleifend aliquam quis a metus. Aliquam placerat aliquam massa, a euismod nunc mattis eget. Integer congue lacinia mauris, nec laoreet ipsum tincidunt vitae.\n" +
                        "\n" +
                        "Morbi ut nulla hendrerit, lobortis quam a, tempor urna. Nam pulvinar tellus non tempus ultricies. Praesent pellentesque augue vel massa ultricies laoreet. Cras luctus magna non risus gravida viverra rhoncus at libero. Maecenas dictum, turpis sed malesuada aliquet, diam ex feugiat mi, in ornare risus ligula quis lorem. Curabitur in elit eget ipsum placerat eleifend id at tellus. Aenean eget felis a est iaculis lacinia. In auctor odio dolor, a rutrum arcu pharetra nec. Praesent dapibus justo sit amet tortor laoreet, sed tempus purus cursus. Fusce aliquet eros sit amet sem egestas aliquet quis non nunc. Suspendisse lectus ligula, posuere vel lectus id, placerat accumsan tortor. Donec sodales sem massa, nec egestas urna fringilla vitae. Duis iaculis ex sit amet ipsum porttitor iaculis."
            )
        }
    }

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
}