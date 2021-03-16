package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class DrawQuestion3Ans (val res: IntArray) : Drawable() {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)

        paintText.setTextAlign(Paint.Align.CENTER)
        paintText.setTextSize(50f)
    }

    override fun draw(canvas: Canvas) {
        val SIZE: Float = bounds.width().toFloat() / 5

        val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)

        val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintText.setTextAlign(Paint.Align.CENTER)
        paintText.setTextSize(30f)

        for (i in 0 until res.size) {
            for (j in 0 until res.size) {
                if(i==j) {
                    canvas.drawRect(i * SIZE, j * SIZE, (i + 1) * SIZE, (j + 1) * SIZE, paintStroke)
                    canvas.drawText(res[i].toString(), (i * SIZE + SIZE / 2).toFloat(), (j * SIZE + SIZE / 2).toFloat(), paintText)
                }
            }
        }
    }

    override fun setAlpha(alpha: Int) {
        TODO("Not yet implemented")
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("Not yet implemented")
    }

    override fun getOpacity(): Int =
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        PixelFormat.OPAQUE

}