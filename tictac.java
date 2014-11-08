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

	double h_start; //starting x co-ordinate of horizontal line
	double h_end;	//ending x co-ordinate of horizontal line
	double v_start;	//starting y co-ordinate or vertical line
	double v_end;	//ending y co-ordinate of vertical line

	int lasthit = 0; //keeps track of which square was hit last (numbered as shown below)
	/*
		1|2|3
		- - -
		4|5|6
		- - -
		7|8|9
	*/
	
	public void init()	{
//		msg = "Hello";
		addMouseListener(new MyMouseAdapter(this));
		addMouseMotionListener(new MyMouseMotionAdapter(this));
	}

	public int ptov(double perc, int targ)	{	//returns perc% of targ (in int)
		return ((int) (perc * targ));
	}

	public void drawX(Graphics g, int xx, int yy)	{	//draws "X" on a square. Takes starting co-ordinates
		if( xx == 0 && yy == 0 ) //handles a default case
			return;

		g.drawLine(xx, yy, xx + ptov(0.23, dim.width) , yy + ptov(0.23, dim.height) );
		g.drawLine(xx + ptov(0.23, dim.width), yy, xx, yy + ptov(0.23, dim.height) );

	}

	public void drawC(Graphics g, int xx, int yy)	{
		if( xx == 0 && yy == 0 )	
			return;

		g.drawOval( xx, yy, ptov(0.23, dim.width), ptov(0.23, dim.height) );

	}

	public void hit(int xx, int yy)	{	//if a mouse is clicked from a particular square...
		//xx - x co-ordinate of clicked point
		//yy - y co-ordinate of clicked point

		if( xx > ptov(0.06, dim.width) && xx < ptov(0.34, dim.width) )	{	//left coloumn
			if( yy > ptov(0.06,  dim.height) && yy < ptov(0.34, dim.height) )	{
				lasthit = 1;
			}
			else if( yy > ptov(0.37,  dim.height) && yy < ptov(0.64, dim.height) )	{
				lasthit = 4;
			}
			else if( yy > ptov(0.67,  dim.height) && yy < ptov(0.94, dim.height) )	{
				lasthit = 7;
			}
		}
		else if( xx > ptov(0.37, dim.width) && xx < ptov(0.64, dim.width) )	{ //middle coloumn
			if( yy > ptov(0.06,  dim.height) && yy < ptov(0.34, dim.height) )	{
				lasthit = 2;
			}
			else if( yy > ptov(0.37,  dim.height) && yy < ptov(0.64, dim.height) )	{
				lasthit = 5;
			}
			else if( yy > ptov(0.67,  dim.height) && yy < ptov(0.94, dim.height) )	{
				lasthit = 8;
			}
		}
		else if( xx > ptov(0.67, dim.width) && xx < ptov(0.92, dim.width) )	{ //right coloumn
			if( yy > ptov(0.06,  dim.height) && yy < ptov(0.34, dim.height) )	{
				lasthit = 3;
			}
			else if( yy > ptov(0.37,  dim.height) && yy < ptov(0.64, dim.height) )	{
				lasthit = 6;
			}
			else if( yy > ptov(0.67,  dim.height) && yy < ptov(0.94, dim.height) )	{
				lasthit = 9;
			}
		}			
	}

	public void update(Graphics g)	{
		/*
			All measurements are relative to the dimensions of screen 
			so that everything resizes itself when the screen is resized
		*/
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

		int dx = 0, dy = 0;
		switch(lasthit)	{ //resolve x and y co-ordinates of a particular square
			case 1: dx = ptov(0.09, dim.width);
					dy = ptov(0.09, dim.height);
					break;
			case 2: dx = ptov(0.38, dim.width);
					dy = ptov(0.09, dim.height);
					break;
			case 3: dx = ptov(0.68, dim.width);
					dy = ptov(0.09, dim.height);
					break;
			case 4: dx = ptov(0.09, dim.width);
					dy = ptov(0.38, dim.height);
					break;
			case 5: dx = ptov(0.38, dim.width);
					dy = ptov(0.38, dim.height);
					break;
			case 6: dx = ptov(0.68, dim.width);
					dy = ptov(0.38, dim.height);
					break;
			case 7: dx = ptov(0.09, dim.width);
					dy = ptov(0.68, dim.height);
					break;
			case 8: dx = ptov(0.38, dim.width);
					dy = ptov(0.68, dim.height);
					break;
			case 9: dx = ptov(0.68, dim.width);
					dy = ptov(0.68, dim.height);
					break;
		}
//		drawX(g, dx, dy);
		drawC(g, dx, dy);
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
		T.hit(ME.getX(), ME.getY());
		T.repaint();
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
