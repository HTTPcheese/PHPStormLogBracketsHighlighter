package com.httpcheese.bracketshighlighter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LogBracketsMatcherTest {
    @Test
    fun `matches nested bracket pair`() {ТОт
        assertEquals(0 to 14, LogBracketsMatcher.findMatching("{ one { two } }", 0))
    }

    @Test
    fun `matches bracket before caret`() {
        assertEquals(0 to 4, LogBracketsMatcher.findMatching("[log]", 1))
    }

    @Test
    fun `ignores unmatched brackets`() {
        assertNull(LogBracketsMatcher.findMatching("{ log", 0))
    }
}
