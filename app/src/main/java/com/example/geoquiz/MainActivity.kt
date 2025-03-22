package com.example.geoquiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val questionBank = listOf(
        Question("Canberra is the capital of Australia.", true),
        Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        Question("The source of the Nile River is in Egypt.", false),
        Question("The Amazon River is the longest river in the Americas.", true),
        Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Use rememberSaveable instead of remember
                var currentIndex by rememberSaveable { mutableStateOf(0) }

                // Get the current question
                val currentQuestion = questionBank[currentIndex]

                QuizScreen(
                    questionText = currentQuestion.text,
                    onTrueClicked = {
                        checkAnswer(true, currentQuestion.answer)
                    },
                    onFalseClicked = {
                        checkAnswer(false, currentQuestion.answer)
                    },
                    onPreviousClicked = {
                        currentIndex = if (currentIndex == 0) {
                            questionBank.lastIndex // Jump to the last question
                        } else {
                            currentIndex - 1
                        }
                    },
                    onNextClicked = {
                        currentIndex = (currentIndex + 1) % questionBank.size
                    }
                )
            }
        }
    }

    private fun checkAnswer(userAnswer: Boolean, correctAnswer: Boolean) {
        val message = if (userAnswer == correctAnswer) "Correct!" else "Incorrect!"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Displays:
 *   - A question (as text).
 *   - True/False buttons.
 *   - A row of Previous/Next buttons at the bottom.
 */
@Composable
fun QuizScreen(
    questionText: String,
    onTrueClicked: () -> Unit,
    onFalseClicked: () -> Unit,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    // Optional: for advanced usage, you could also call Toast here, but
    // typically you'd do that in the Activity or a ViewModel for best practice.

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Main content (question + True/False)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp), // Extra bottom padding so we can have buttons at the bottom
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = questionText,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = onTrueClicked) {
                Text(text = "True")
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = onFalseClicked) {
                Text(text = "False")
            }
        }

        // Bottom navigation row: Previous/Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onPreviousClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Previous"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }

            Button(onClick = onNextClicked) {
                Text("Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Next"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    MaterialTheme {
        QuizScreen(
            questionText = "Canberra is the capital of Australia.",
            onTrueClicked = {},
            onFalseClicked = {},
            onPreviousClicked = {},
            onNextClicked = {}
        )
    }
}


/*package com.example.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
//import com.example.geoquiz.databinding.ActivityMainBinding //test

class MainActivity : ComponentActivity() {

    //private lateinit var binding: ActivityMainBinding   //test
    // private val quizViewModel: QuizViewModel by viewModels()
    //private lateinit var trueButton: Button //test
   //private lateinit var falseButton: Button //test

    /*private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)   //test
        //setContentView(binding.root)    //test
        // Log.d(TAG, "Got a QuizViewModel: $quizViewModel")
        //setContentView(R.layout.activity_main)  //test
        setContent {
            QuizScreen(
                onTrueClicked = { /* handle True */ },
                onFalseClicked = { /* handle False */ },
                onPreviousClicked = { /* handle Previous */ },
                onNextClicked = { /* handle Next */ }
            )
        }

        //trueButton = findViewById(R.id.true_button) //test
        //falseButton = findViewById(R.id.false_button) //test
        //trueButton.setOnClickListener { view: View ->
        //binding.trueButton.setOnClickListener { view: View ->
            //Toast.makeText(
            //    this,
            //    R.string.correct_toast,
            //    Toast.LENGTH_SHORT
            //).show()
            //checkAnswer(true)
        //}   //test
        //falseButton.setOnClickListener { view: View ->
        //binding.falseButton.setOnClickListener { view: View ->
            //Toast.makeText(
            //    this,
            //    R.string.incorrect_toast,
            //    Toast.LENGTH_SHORT
            //).show()
            //checkAnswer(false)
        //}   //test

        //binding.nextButton.setOnClickListener {
            //currentIndex = (currentIndex + 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            //updateQuestion()

        //}

        //val questionTextResId = questionBank[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)
        //updateQuestion()
    //}

    //private fun updateQuestion() {
        //val questionTextResId = questionBank[currentIndex].textResId
       // binding.questionTextView.setText(questionTextResId)
    //}

    //private fun checkAnswer(userAnswer: Boolean) {
        //val correctAnswer = questionBank[currentIndex].answer
        //val correct = quizViewModel.currentQuestionText

       // val messageResId = if (userAnswer == correctAnswer) {
        //    R.string.correct_toast
        //} else {
            //R.string.incorrect_toast
        //}

       // Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        //    .show()
    }
}

@Composable
fun QuizScreen(
    onTrueClicked: () -> Unit,
    onFalseClicked: () -> Unit,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp), // Add padding to prevent overlap with buttons
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Is this awesome?",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = onTrueClicked) {
                Text(text = "True")
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = onFalseClicked) {
                Text(text = "False")
            }
        }

        // Bottom Navigation Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Aligns at the bottom
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onPreviousClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }

            Button(onClick = onNextClicked) {
                Text("Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    MaterialTheme {
        QuizScreen(
            onTrueClicked = {},
            onFalseClicked = {},
            onPreviousClicked = {},
            onNextClicked = {}
        )
    }
}*/