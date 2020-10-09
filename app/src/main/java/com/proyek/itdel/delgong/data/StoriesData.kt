package com.proyek.itdel.delgong.data

import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.model.local.PartStory
import com.proyek.itdel.delgong.model.local.Story

object StoriesData {
    private val titleStories = arrayOf("Mama Antelop's House")
    private val authorStories = arrayOf("Florence Marundu")
    private val yearStories = arrayOf("2019")
    private val storiesImages = intArrayOf(R.drawable.mama_antelope)

    private val mamaAntelope = listOf(
        PartStory(
            "Long ago, all the animals lived in a village. Mama Antelope had a big house. One day, Hare wanted to play a trick. He thought and thought and thought… He crept into Mama Antelope’s house. He closed the door and made a lot of noise. Mama Antelope came home. “Who is in my house?” she cried.",
            "mama_antelope/1.mp3"
        ),
        PartStory(
            "“Hyena, please help!Someone is in my house”. Knock, knock, knock! “Open the door!” howled Hyena. But the door did not open Hyena called Giraffe. “Open the door!” shouted Giraffe. But the door did not open. Giraffe called Zebra. “Open the door!” yelled Zebra. But the door did not open Zebra called Elephant. He was big and strong. “Open the door!”commanded Elephant. But the door did not open.",
            "mama_antelope/2.mp3"
        ),
        PartStory(
            "Mama Antelope was sad. All the animals were sad. Who was in Mama Antelope’s house? The birds sang. The butterflies flapped. The bees buzzed. “Open the door!” they all cried. Baby Antelope came home. “Who is in our house?”. “Please open the door,” she asked sweetly. The door opened. It was Hare! Hare was smiling. He loved tricks Hurrah! Hurrah! Mama Antelope and Baby Antelope are happy. The animals are happy. Mama Antelope’s house is open at last.",
            "mama_antelope/3.mp3"
        )
    )


    private val storiesPartList = arrayListOf(mamaAntelope)

    val listData: ArrayList<Story>
        get() {
            val list = arrayListOf<Story>()
            for (position in authorStories.indices) {
                val story = Story(
                    titleStories[position],
                    authorStories[position],
                    yearStories[position],
                    storiesImages[position],
                    storiesPartList[position]
                )
                list.add(story)
            }
            return list
        }
}