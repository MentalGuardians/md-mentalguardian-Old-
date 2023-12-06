package org.guardteam.mentalguardians.common.utils

import org.guardteam.mentalguardians.domain.model.Content

object DataDummy {
    val contentItems: List<Content> by lazy {
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
}