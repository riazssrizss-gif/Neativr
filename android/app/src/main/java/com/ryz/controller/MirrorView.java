package com.ryz.controller;
import android.content.Context;import android.graphics.*;import android.util.AttributeSet;import android.view.View;
public class MirrorView extends View{
public interface SwipeListener{void onSwipe(float x1,float y1,float x2,float y2);}
private Bitmap frame;private final Paint p=new Paint(Paint.FILTER_BITMAP_FLAG),pp=new Paint();private int aw=1080,ah=1920;private float sx,sy;private SwipeListener sl;
public MirrorView(Context c,AttributeSet a){super(c,a);pp.setColor(Color.parseColor("#1A1A1A"));}
@Override protected void onDraw(Canvas c){super.onDraw(c);if(frame!=null){c.drawBitmap(frame,null,new RectF(0,0,getWidth(),getHeight()),p);}else{c.drawRect(0,0,getWidth(),getHeight(),pp);Paint t=new Paint();t.setColor(Color.GRAY);t.setTextSize(40f);t.setTextAlign(Paint.Align.CENTER);c.drawText("Menunggu koneksi...",getWidth()/2f,getHeight()/2f,t);}}
public void updateFrame(byte[]b){Bitmap bm=BitmapFactory.decodeByteArray(b,0,b.length);if(bm!=null){if(frame!=null)frame.recycle();frame=bm;postInvalidate();}}
public void setAgentResolution(int w,int h){aw=w;ah=h;}
public float[]scaleToAgent(float x,float y){return new float[]{x*aw/getWidth(),y*ah/getHeight()};}
public void trackMove(float x,float y){}
public void setSwipeStart(float x,float y){sx=x;sy=y;}
public void triggerSwipe(float ex,float ey){if(sl!=null)sl.onSwipe(sx,sy,ex,ey);}
public void setSwipeListener(SwipeListener l){sl=l;}}