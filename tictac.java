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
	Dimension dim;
	int x, y;
	double h_start; //starting x co-ordinate of horizontal line
	double h_end;	//ending x co-ordinate of horizontal line
	double v_start;	//starting y co-ordinate or vertical line
	double v_end;	//ending y co-ordinate of vertical line

	public void init()	{
		msg = "Hello";
		addMouseListener(new MyMouseAdapter(this));
		addMouseMotionListener(new MyMouseMotionAdapter(this));
	}

	public void update(Graphics g)	{
//		g.drawString(msg, 20, 20);
		dim = getSize();
		
		h_start = 0.05 * dim.width;	//5% of width of applet
		v_start = 0.05 * dim.height;	//5% of height of applet

		h_end = 0.95 * dim.width;	//95% of width of applet 
		v_end = 0.95 * dim.height;	//95% of height of applet

		//draws the board. Each pair of lines below draw one line. pair for extra thickness
		g.drawLine( (int) h_start, (int) (0.35 * dim.height)	, (int) h_end, (int) (0.35 * dim.height) 	 );
		g.drawLine( (int) h_start, (int) (0.35 * dim.height + 1), (int) h_end, (int) (0.35 * dim.height + 1) );
		 
		g.drawLine( (int) h_start, (int) (0.65 * dim.height)	, (int) h_end, (int) (0.65 * dim.height) 	 ); 
		g.drawLine( (int) h_start, (int) (0.65 * dim.height + 1), (int) h_end, (int) (0.65 * dim.height + 1) ); 
		
		g.drawLine( (int) (0.35 * dim.width)	, (int) v_start, (int) (0.35 * dim.width)	 , (int) v_end ); 
		g.drawLine( (int) (0.35 * dim.width + 1), (int) v_start, (int) (0.35 * dim.width + 1), (int) v_end ); 
		
		g.drawLine( (int) (0.65 * dim.width)	, (int) v_start, (int) (0.65 * dim.width)	 , (int) v_end ); 
		g.drawLine( (int) (0.65 * dim.width + 1), (int) v_start, (int) (0.65 * dim.width + 1), (int) v_end ); 
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
