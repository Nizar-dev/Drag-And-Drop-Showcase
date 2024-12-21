package apps.nb.working.draganddroppoc.utils

private const val ELEMENTS_BY_ROW = 3
private const val ROW_HEIGHT = 100
private const val EXTRA_ROW_RATIO = 2.0

internal fun setHeight(numberOfElements: Int): Double =
    (numberOfElements / ELEMENTS_BY_ROW + EXTRA_ROW_RATIO) * ROW_HEIGHT
