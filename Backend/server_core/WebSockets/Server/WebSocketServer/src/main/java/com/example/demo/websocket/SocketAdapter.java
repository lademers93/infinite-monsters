package com.example.demo.websocket;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;



public class SocketAdapter {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public SocketAdapter(Socket socket) {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public InetAddress getInetAddress() {
		return socket.getInetAddress();
	}
	
	public String getHost() {
		return (socket.getInetAddress() != null) ? socket.getInetAddress().toString() : null;
	}
	
	public String readString() throws IOException, EOFException {
//		synchronized (socket) {
			String read = null;
			try {
				read = (String) in.readObject();
				System.out.println(read);
			} catch (ClassNotFoundException e) {
				//malformed input
				return null;
			} catch (IOException e) {
				//TODO check to see if it's because the connection was dropped
				throw e;
			}
			return read;
//		}
	}
	
	public void writeString(String toWrite) {
//		synchronized (socket) {
			try {
				System.out.println(toWrite);
				out.writeObject((Object)toWrite);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}
	
	public boolean isClosed() {
		return socket.isClosed();
	}
	
	public void close() {
		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}