package com.ekosoftware.notas.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ekosoftware.notas.data.model.Note
import java.lang.StringBuilder

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, this.getString(resId), duration).show()
}

fun List<Note>.idsAsString(): String {
    val builder = StringBuilder()
    for (i in this.indices) {
        builder.append(this[i])
        if (i != this.size - 1) builder.append(";")
    }
    return builder.toString()
}

fun List<Note>.sort(ids: String?): List<Note> {
    return ids?.split(";")?.map {
        var correspondingNote = Note()
        loop@ for (note in this) {
            if (note.id == it.toInt()) {
                correspondingNote = note
                break@loop
            }
        }
        correspondingNote
    } ?: listOf()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showKeyboard() {
    view?.let { activity?.showKeyboard(it) }
}

fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, 1)
}

fun List<Note>.toLabelsPlainString(): String {
    val builder = StringBuilder()
    for (i in indices) {
        builder.append(this[i].label)
        if (i != size - 1) builder.append("-.,-")
    }
    return builder.toString()
}

fun MutableList<String>.toPlainString(): String {
    val builder = StringBuilder()
    for (i in indices) {
        builder.append(this[i])
        if (i != size - 1) builder.append("-.,-")
    }
    return builder.toString()
}

fun String.toLabelsList(): List<String> {
    val labels = mutableListOf<String>()
    this.split("-.,-").forEach {
        labels.add(it)
    }
    return labels.toList()
}