package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import java.util.*

class DrawQuestion1(var color1: Paint, var color2: Paint) : Drawable() {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    private var coeff_rand:Array<IntArray> = Array(3) { IntArray(3) {0} }
    private val num_rand: List<Int> = (0..3).shuffled().take(3)

    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)

        paintText.setTextAlign(Paint.Align.CENTER)
        paintText.setTextSize(50f)

        for(i in 0 until 3) {
            coeff_rand[0][i] = num_rand[i] % 2
            coeff_rand[1][i] = (num_rand[i] / 2) % 2
            coeff_rand[2][i] = num_rand[i]
        }
    }

    override fun draw(canvas: Canvas) {
        val SIZE: Float = bounds.width().toFloat() / 3f
        val radius: Float = (SIZE-20) / 2f

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                canvas.drawRect(i * SIZE, j * SIZE, (i + 1) * SIZE, (j + 1) * SIZE, paintStroke)
                if(i!=2){
                    canvas.drawCircle((i*SIZE + SIZE/2).toFloat(), (j*SIZE + SIZE/2).toFloat(), radius, pcolor(coeff_rand[i][j]))
                    canvas.drawCircle((i*SIZE + SIZE/2).toFloat(), (j*SIZE + SIZE/2).toFloat(), radius, paintStroke)
                }
                else{
                    if(j==2){
                        canvas.drawText("?", (i * SIZE + SIZE / 2).toFloat(), (j * SIZE + SIZE / 2 + 20f).toFloat(), paintText)
                    }
                    else{
                        color1 = pcolor(coeff_rand[i-2][j])
                        color2 = pcolor(coeff_rand[i-1][j])

                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat(), (j*SIZE + SIZE/2).toFloat(), radius, color1)
                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat(), (j*SIZE + SIZE/2).toFloat(), radius, paintStroke)
                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat(), (j*SIZE + SIZE/2).toFloat(), radius/2, color2)
                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat(), (j*SIZE + SIZE/2).toFloat(), radius/2, paintStroke)
                    }

                }
            }
        }
    }

    public fun getCoeff():Int{
        return  num_rand[2]
    }

    protected fun pcolor(coeff:Int): Paint {
        if(coeff == 1)
            return Paint().apply { setARGB(255, 255, 255, 255)}
        else
            return Paint().apply { setARGB(255, 0, 0, 0)}
    }

    override fun setAlpha(alpha: Int) {
        // This method is required
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // This method is required
    }

    override fun getOpacity(): Int =
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        PixelFormat.OPAQUE
}