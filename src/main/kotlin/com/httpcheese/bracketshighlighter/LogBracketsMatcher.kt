package com.httpcheese.bracketshighlighter

internal object LogBracketsMatcher {
    fun findMatching(text: CharSequence, caretOffset: Int): Pair<Int, Int>? {
        val index = sequenceOf(caretOffset, caretOffset - 1)
            .firstOrNull { it in text.indices && text[it] in "()[]{}" }
            ?: return null

        val (opening, closing, direction) = when (text[index]) {
            '(' -> Triple('(', ')', 1)
            '[' -> Triple('[', ']', 1)
            '{' -> Triple('{', '}', 1)
            ')' -> Triple('(', ')', -1)
            ']' -> Triple('[', ']', -1)
            '}' -> Triple('{', '}', -1)
            else -> return null
        }
        var depth = 0
        var position = index
        while (position in text.indices) {
            when (text[position]) {
                opening -> depth += direction
                closing -> depth -= direction
            }
            if (depth == 0) return index to position
            position += direction
        }
        return null
    }
}
