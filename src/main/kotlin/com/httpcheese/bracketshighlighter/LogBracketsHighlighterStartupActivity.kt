package com.httpcheese.bracketshighlighter

import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.event.CaretEvent
import com.intellij.openapi.editor.event.CaretListener
import com.intellij.openapi.editor.markup.HighlighterLayer
import com.intellij.openapi.editor.markup.HighlighterTargetArea
import com.intellij.openapi.editor.markup.RangeHighlighter
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class LogBracketsHighlighterStartupActivity : StartupActivity.DumbAware {
    override fun runActivity(project: Project) {
        EditorFactory.getInstance().eventMulticaster.addCaretListener(LogBracketsCaretListener(), project)
    }
}

private class LogBracketsCaretListener : CaretListener {
    private var highlighters = emptyList<RangeHighlighter>()

    override fun caretPositionChanged(event: CaretEvent) {
        clearHighlighting()

        val editor = event.editor
        val file = FileDocumentManager.getInstance().getFile(editor.document) ?: return
        if (!file.extension.equals("log", ignoreCase = true)) return

        val pair = LogBracketsMatcher.findMatching(editor.document.charsSequence, event.caret.offset) ?: return
        highlighters = listOf(pair.first, pair.second).map { offset ->
            editor.markupModel.addRangeHighlighter(
                offset,
                offset + 1,
                HighlighterLayer.SELECTION - 1,
                editor.colorsScheme.getAttributes(EditorColors.SEARCH_RESULT_ATTRIBUTES),
                HighlighterTargetArea.EXACT_RANGE,
            )
        }
    }

    private fun clearHighlighting() {
        highlighters.forEach { it.dispose() }
        highlighters = emptyList()
    }
}
