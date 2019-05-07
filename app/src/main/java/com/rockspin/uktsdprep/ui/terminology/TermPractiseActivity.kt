package com.rockspin.uktsdprep.ui.terminology

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.behavior.SwipeDismissBehavior
import com.rockspin.uktsdprep.R
import com.rockspin.uktsdprep.data.model.Term
import com.rockspin.uktsdprep.scale
import kotlinx.android.synthetic.main.activity_term_practise.*
import java.util.*

class TermPractiseActivity : AppCompatActivity() {

    companion object {

        private const val TERMS_EXTRA = "TERMS_EXTRA"

        fun createIntent(context: Context, terms: List<Term>): Intent {
            return Intent(context, TermPractiseActivity::class.java)
                .putParcelableArrayListExtra(TERMS_EXTRA, ArrayList(terms))
        }
    }

    private var currentTermIndex = -1
    private var isShowingMeaning = false
    private lateinit var currentTerm: Term

    private lateinit var terms: List<Term>

    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_practise)

        terms = intent.getParcelableArrayListExtra(TERMS_EXTRA)

        setupSwipeDismiss()

        changePhrase()

        cardView.setOnClickListener {
            if (!isShowingMeaning) {
                revealMeaning()
            } else {
                cardView.animate()
                    .translationXBy(cardView.width.toFloat())
                    .alpha(0f)
                    .setDuration(400)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withEndAction {
                        changeCard()
                    }
            }
        }
    }

    private fun setupSwipeDismiss() {
        val swipeDismissBehavior = SwipeDismissBehavior<CardView>()
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END)
        swipeDismissBehavior.setStartAlphaSwipeDistance(0.4f)
        swipeDismissBehavior.setEndAlphaSwipeDistance(0.9f)

        val layoutParams = cardView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = swipeDismissBehavior

        swipeDismissBehavior.setListener(object: SwipeDismissBehavior.OnDismissListener {
            override fun onDismiss(view: View?) {
                changeCard()
            }

            override fun onDragStateChanged(state: Int) {

            }
        })
    }

    private fun revealMeaning() {
        val originalCardElevation = cardView.cardElevation

        cardView.cardElevation = 0f

        cardView.animate()
            .rotationXBy(360f)
            .setDuration(400)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                cardView.cardElevation = originalCardElevation
            }
            .start()

        phraseTextView.postDelayed({
            val isShowingPhrase = phraseTextView.text == currentTerm.phrase
            phraseTextView.text = if(isShowingPhrase) currentTerm.meaning else currentTerm.phrase
            isShowingMeaning = true
        }, 200)
    }

    private fun changeCard() {
        val layoutParams = cardView.layoutParams as CoordinatorLayout.LayoutParams

        changePhrase()

        cardView.postDelayed({
            layoutParams.setMargins(0, 0, 0, 0)
            cardView.requestLayout()
            cardView.translationX = 0f

            cardView.scale = 0.2f

            cardView.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()

        }, 200)
    }

    private fun changePhrase() {
        isShowingMeaning = false
        generateNextIndex()

        currentTerm = terms[currentTermIndex]

        val showPhrase = random.nextBoolean()
        phraseTextView.text = if(showPhrase) currentTerm.phrase else currentTerm.meaning
    }

    private fun generateNextIndex() {
        // Function that generates a random number guaranteed different from last one
        val rotate = 1 + random.nextInt(terms.size - 1)
        currentTermIndex = (currentTermIndex + rotate) % terms.size
    }
}