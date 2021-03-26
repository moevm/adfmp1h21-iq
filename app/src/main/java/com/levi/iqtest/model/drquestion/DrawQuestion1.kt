package com.levi.iqtest.model.drquestion

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import java.util.*

class DrawQuestion1() : Drawable() {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    private var coeff_rand:Array<IntArray> = Array(3) { IntArray(3) {0} }
    private val num_rand: List<Int> = (0..3).shuffled().take(3)

    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)

        paintText.setTextAlign(Paint.Align.CENTER)

        for(i in 0 until 3) {
            coeff_rand[0][i] = num_rand[i] % 2
            coeff_rand[1][i] = (num_rand[i] / 2) % 2
            coeff_rand[2][i] = num_rand[i]
        }
    }

    override fun draw(canvas: Canvas) {
        val SIZE: Float = Math.min(bounds.width(), bounds.height()).toFloat() / 3f
        val offsetLeft = if(bounds.width()<bounds.height()) 0 else (bounds.width()-bounds.height())/2
        val radius: Float = (SIZE-20) / 2f
//        Log.i("Debug", toString())

        paintText.setTextSize(SIZE/3)

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                canvas.drawRect(i * SIZE +offsetLeft, j * SIZE, (i + 1) * SIZE+offsetLeft, (j + 1) * SIZE, paintStroke)
                if(i!=2){
                    canvas.drawCircle((i*SIZE + SIZE/2).toFloat()+offsetLeft, (j*SIZE + SIZE/2).toFloat(), radius, pcolor(coeff_rand[i][j]))
                    canvas.drawCircle((i*SIZE + SIZE/2).toFloat()+offsetLeft, (j*SIZE + SIZE/2).toFloat(), radius, paintStroke)
                }
                else{
                    val c1 = pcolor(coeff_rand[i-2][j])
                    val c2 = pcolor(coeff_rand[i-1][j])
                    if(j==2){
//                        color1 = pcolor(coeff_rand[i-2][j])
//                        color2 = pcolor(coeff_rand[i-1][j])
                        canvas.drawText("?", (i * SIZE + SIZE/2).toFloat()+offsetLeft, (j * SIZE + 2*SIZE/3).toFloat(), paintText)
                    }
                    else{
                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat()+offsetLeft, (j*SIZE + SIZE/2).toFloat(), radius, c1)
                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat()+offsetLeft, (j*SIZE + SIZE/2).toFloat(), radius, paintStroke)
                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat()+offsetLeft, (j*SIZE + SIZE/2).toFloat(), radius/2, c2)
                        canvas.drawCircle((i*SIZE + SIZE/2).toFloat()+offsetLeft, (j*SIZE + SIZE/2).toFloat(), radius/2, paintStroke)
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

    override fun toString(): String {
        return "DrawQuestion1( paintStroke=$paintStroke, paintText=$paintText, coeff_rand=${coeff_rand.contentToString()}, num_rand=$num_rand), ${bounds.width()}, ${bounds.height()})"
    }
}