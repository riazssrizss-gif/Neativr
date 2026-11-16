package com.ryz.controller;
import org.java_websocket.client.WebSocketClient;import org.java_websocket.handshake.ServerHandshake;import java.net.URI;import java.nio.ByteBuffer;
public class ControllerClient{
public interface FrameListener{void onFrame(byte[]data);}public interface MessageListener{void onMessage(String msg);}public interface StatusListener{void onEvent();}
private WebSocketClient ws;private final String url;private final StatusListener onC,onD;private final FrameListener onF;private final MessageListener onM;
public ControllerClient(String url,StatusListener onC,StatusListener onD,FrameListener onF,MessageListener onM){this.url=url;this.onC=onC;this.onD=onD;this.onF=onF;this.onM=onM;}
public void connect(){try{ws=new WebSocketClient(new URI(url)){public void onOpen(ServerHandshake h){onC.onEvent();}public void onMessage(String m){onM.onMessage(m);}public void onMessage(ByteBuffer b){byte[]d=new byte[b.remaining()];b.get(d);onF.onFrame(d);}public void onClose(int c,String r,boolean re){onD.onEvent();}public void onError(Exception e){onD.onEvent();}};ws.connect();}catch(Exception e){onD.onEvent();}}
public void sendCommand(String c){if(ws!=null&&ws.isOpen())ws.send(c);}
public boolean isConnected(){return ws!=null&&ws.isOpen();}
public void disconnect(){if(ws!=null)ws.close();}}