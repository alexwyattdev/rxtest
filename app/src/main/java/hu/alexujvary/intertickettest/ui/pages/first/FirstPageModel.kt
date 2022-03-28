package hu.alexujvary.intertickettest.ui.pages.first

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FirstPageModel @Inject constructor() : FirstPageContract.Model {

    private val digitsRegex = Regex("[^\\d-, ]")
    private val digits = "0123456789"
    private val stringToRepeatForPositiveNumbers = "Rx"
    private val stringForNegativeNumbers = "NEGATÍV SZÁM"
    private val lineSeparator = "\n"

    override fun processInput(input: String): Single<String> = Single.create { emitter ->
        try {
            val cleanInput = input.replace(digitsRegex, "")
            if (cleanInput.isEmpty() || !input.any { it in digits }) {
                emitter.onSuccess("")
            } else {
                val digits = cleanInput.split("[\\s,]+".toRegex()).map { it.trim().toInt() }
                val processedInput = digits.joinToString(lineSeparator) {
                    if (it >= 0) stringToRepeatForPositiveNumbers.repeat(it) else stringForNegativeNumbers
                }
                emitter.onSuccess(processedInput)
            }
        } catch (e: Exception) {
            emitter.tryOnError(e)
        }
    }
}