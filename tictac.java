/*
	TIC-TAC-TOW PROJECT
	(Program under development)

	Main implementation of tic tac toe

	OS		: Ubuntu 14.04 LTS
	CODER	: Arjun Krishna Babu
	GIT		: https://github.com/arjunkbabu/tic_tac_toe


*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/*
<APPLET code = "tictac" width = 330 height = 330 >
</APPLET>
*/

public class tictac extends Applet	{
	String msg;
	public void init()	{
		msg = "Hello";
		addMouseListener(new MyMouseAdapter(this));
		addMouseMotionListener(new MyMouseMotionAdapter(this));
	}

	public void update(Graphics g)	{
		g.drawString(msg, 20, 20);
	}

	public void paint(Graphics g)	{
		update(g);
	}
}

class MyMouseAdapter extends MouseAdapter	{
	tictac T;

	MyMouseAdapter(tictac T)	{
		this.T = T;
	}

	public void mouseClicked(MouseEvent ME)	{
		T.showStatus(" CLICK!");
	}
}

class MyMouseMotionAdapter extends MouseMotionAdapter	{
	tictac T;

	MyMouseMotionAdapter(tictac T)	{
		this.T = T;
	}

	public void mouseMoved(MouseEvent ME)	{
		T.showStatus( " ( " + ME.getX() + ", " + ME.getY() + ")");
	}
}
