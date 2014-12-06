/*
	TIC-TAC-TOE PROJECT
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

class MainMenu	extends Frame	{
	String msg;
	Dimension D;
	boolean flag;

	int option_ch;
	public int choice;
	int selected_option;
	int dim[][] = new int[3][2];

	
	String menu_option[] = {"New Game", "Settings", "EXIT"};
	
	public MainMenu()	{
		flag = false;
//		msg = "Hello";
		option_ch = 0;
		choice = -1;
		
		addKeyListener( new MyKeyAdapter(this) );
		addWindowListener( new MyWindowAdapter() );
		requestFocus();
	}
	
	int getOption()	{
		return option_ch;
	}
	void setOption(int op)	{
		option_ch = op;
	}
	
	public int getChoice()	{
		return choice;
	}
	
	void setDim()	{
		D = getSize();
		dim[0][0] = D.width/2 - 30;
		dim[0][1] = D.height/2 - 10;
		
		dim[1][0] = D.width/2 - 30;
		dim[1][1] = D.height/2 + 5;
		
		dim[2][0] = D.width/2 - 30;
		dim[2][1] = D.height/2 + 20;
	}
	
	public void update(Graphics g)	{
		setDim();
		g.setColor(Color.black);
		
		g.drawString(" A " + choice, 10, 20);
		
		g.drawString(menu_option[0], dim[0][0], dim[0][1]);
		g.drawString(menu_option[1], dim[1][0], dim[1][1]);
		g.drawString(menu_option[2], dim[2][0], dim[2][1]);

//		g.drawString(msg, 10, 20);
		g.setColor(Color.red);
		g.drawString(menu_option[option_ch], dim[option_ch][0], dim[option_ch][1]);
	}
	
	public void paint(Graphics g)	{			
		update(g);
	}	
}


class Game extends Frame	{
	//class that implements actual Game logic

	String msg;
	Dimension dim;

	double h_start; //starting x co-ordinate of horizontal line
	double h_end;	//ending x co-ordinate of horizontal line
	double v_start;	//starting y co-ordinate or vertical line
	double v_end;	//ending y co-ordinate of vertical line
	
	boolean win = false; int ws = 0, we = 0;

	int lasthit = 0; //keeps track of which square was hit last (numbered as shown below)
	int [][]A = new int[3][3];
	
	/*
		00|01|02
		-- -- --
		10|11|12
		-- -- --
		21|21|22
	*/
	
	public Game()	{
//		msg = "Hello";
		setBackground(Color.black);
		
		addMouseListener(new MyMouseAdapter(this));
		addMouseMotionListener(new MyMouseMotionAdapter(this));
		addWindowListener(new MyWindowAdapter());

		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				A[i][j] = -1;
	}

	public int ptov(double perc, int targ)	{	//returns perc% of targ (in int)
		return ((int) (perc * targ));
	}

	public void drawX(Graphics g, int xx, int yy)	{	//draws "X" on a square. Takes starting co-ordinates
		if( xx == 0 && yy == 0 ) //handles a default case
			return;

		g.drawLine(xx, yy, xx + ptov(0.23, dim.width) , yy + ptov(0.23, dim.height) );
		g.drawLine(xx+1, yy, xx + ptov(0.23, dim.width) + 1, yy + ptov(0.23, dim.height));
		g.drawLine(xx+2, yy, xx + ptov(0.23, dim.width) + 2, yy + ptov(0.23, dim.height));
		
		try {
			Thread.sleep(150);			
		}
		catch (InterruptedException E)	{ }	
		
		g.drawLine(xx + ptov(0.23, dim.width), yy, xx, yy + ptov(0.23, dim.height) );
		g.drawLine(xx + ptov(0.23, dim.width)+1, yy, xx+1, yy + ptov(0.23, dim.height) );
		g.drawLine(xx + ptov(0.23, dim.width)+2, yy, xx+2, yy + ptov(0.23, dim.height) );

	}

	public void drawC(Graphics g, int xx, int yy)	{
		
		if( xx == 0 && yy == 0 )	
			return;

		g.drawOval( xx, yy, ptov(0.23, dim.width), ptov(0.23, dim.height) );
		g.drawOval( xx+1, yy+1, ptov(0.23, dim.width) - 1, ptov(0.23, dim.height) - 2 );
	}

	public void hit(int xx, int yy)	{	//if a mouse is clicked from a particular square...
		//xx - x co-ordinate of clicked point
		//yy - y co-ordinate of clicked point

		
		if( xx > ptov(0.06, dim.width) && xx < ptov(0.34, dim.width) )	{	//left coloumn
			if( yy > ptov(0.06,  dim.height) && yy < ptov(0.34, dim.height) )	{
				lasthit = 1;
				if(A[0][0] == -1)	
					A[0][0] = 1;
				////showStatus("SQUARE ONE");
			}
			else if( yy > ptov(0.37,  dim.height) && yy < ptov(0.64, dim.height) )	{
				lasthit = 4;
				if(A[1][0] == -1)	
					A[1][0] = 1;
				//showStatus("SQUARE FOUR");
			}
			else if( yy > ptov(0.67,  dim.height) && yy < ptov(0.94, dim.height) )	{
				lasthit = 7;
				if(A[2][0] == -1)
					A[2][0] = 1;
				//showStatus("SQUARE SEVEN");
			}
		}
		else if( xx > ptov(0.37, dim.width) && xx < ptov(0.64, dim.width) )	{ //middle coloumn
			if( yy > ptov(0.06,  dim.height) && yy < ptov(0.34, dim.height) )	{
				lasthit = 2;
				if(A[0][1] == -1)
					A[0][1] = 1;
				//showStatus("SQUARE TWO");
			}
			else if( yy > ptov(0.37,  dim.height) && yy < ptov(0.64, dim.height) )	{
				lasthit = 5;
				if(A[1][1] == -1)
					A[1][1] = 1;
				//showStatus("SQUARE FIVE");
			}
			else if( yy > ptov(0.67,  dim.height) && yy < ptov(0.94, dim.height) )	{
				lasthit = 8;
				if(A[2][1] == -1)
					A[2][1] = 1;
				//showStatus("SQUARE EIGHT");
			}

		}
		else if( xx > ptov(0.67, dim.width) && xx < ptov(0.92, dim.width) )	{ //right coloumn
			if( yy > ptov(0.06,  dim.height) && yy < ptov(0.34, dim.height) )	{
				lasthit = 3;
				if(A[0][2] == -1)
					A[0][2] = 1;
				//showStatus("SQUARE THREE");
			}
			else if( yy > ptov(0.37,  dim.height) && yy < ptov(0.64, dim.height) )	{
				lasthit = 6;
				if(A[1][2] == -1)
					A[1][2] = 1;
				//showStatus("SQUARE SIX");
			}
			else if( yy > ptov(0.67,  dim.height) && yy < ptov(0.94, dim.height) )	{
				lasthit = 9;
				if(A[2][2] == -1)
					A[2][2] = 1;
				//showStatus("SQUARE NINE");
			}
		}			
	}
	
	public void playOpp()	{
//		A[1][1] = 2; //this is a trial move

		//BLOCK:
		if(A[0][0] == 1)	{	//top-left
			if(A[0][1] == 1)	{
				if(A[0][2] == -1)	{
					A[0][2] = 2;	
					return;
				}
			}
			else if(A[0][2] == 1)	{
				if(A[0][1] == -1)	{
					A[0][1] = 2;
					return;
				}
			}
			else if(A[1][0] == 1)	{
				if(A[2][0] == -1)	{
					A[2][0] = 2;
					return;
				}
			}
			else if(A[2][0] == 1)	{
				if(A[1][0] == -1)	{
					A[1][0] = 2;
					return;
				}
			}
			else if(A[1][1] == 1)	{
				if(A[2][2] == -1)	{
					A[2][2] = 2;
					return;
				}
			}
			else if(A[2][2] == 1)	{
				if(A[1][1] == -1)	{
					A[1][1] = 2;
					return;
				}
			}
		}
		
		if(A[2][0] == 1)	{	//bottom-left
			if(A[2][1] == 1)	{
				if(A[2][2] == -1)	{
					A[2][2] = 2;
					return;
				}
			}
			else if(A[2][2] == 1)	{
				if(A[2][1] == -1)	{
					A[2][1] = 2;
					return;
				}
			}
			else if(A[1][1] == 1)	{
				if(A[0][2] == -1)	{
					A[0][2] = 2;
					return;
				}
			}
			else if(A[0][2] == 1)	{
				if(A[1][1] == -1)	{
					A[1][1] = 2;
					return;
				}
			}
		}
		
		if(A[2][2] == 1)	{	//bottom-right
			if(A[2][1] == 1)	{	//bottom-centre
				if(A[2][0] == -1)	{	//bottom-left;
					A[2][0] = 2;
					return;
				}
			}
			else if(A[2][0] == 1)	{	//bottom-left
				if(A[2][1] == -1)	{	//bottom-centre
					A[2][1] = 2;
					return;
				}
			}
			else if(A[1][1] == 1)	{	//middle-centre
				if(A[0][0] == -1)	{	//top-left
					A[0][0] = 2;
					return;
				}
			}
			
			else if(A[1][2] == 1)	{	//middle-right
				if(A[0][2] == -1)	{	//top-right
					A[0][2] = 2;
					return;
				}
			}
			else if(A[0][2] == 1)	{	//top-right
				if(A[1][2] == -1)	{	//middle-right
					A[1][2] = 2;
					return;
				}
			}
		}
		
		if(A[0][2] == 1)	{	//top-right
			if(A[0][1] == 1)	{	//top-centre
				if(A[0][0] == -1)	{	//top-left
					A[0][0] = 2;
					return;
				}
			}
			else if(A[1][2] == 1)	{	//middle-right
				if(A[2][2] == -1)	{	//bottom-right
					A[2][2] = 2;
					return;
				}
			}
			else if(A[1][1] == 1)	{	//middle-centre
				if(A[2][0] == -1)	{	//bottom-left
					A[2][0] = 2;
					return;
				}
			}
		}
		
		if(A[1][1] == 1)	{	//middle-centre
			if(A[0][1] == 1)	{	//top-centre
				if(A[2][1] == -1)	{	//bottom-centre
					A[2][1] = 2;
					return;
				}
			}
			else if(A[2][1] == 1)	{	//bottom-centre
				if(A[0][1] == -1)	{	//top-centre
					A[0][1] = 2;
					return;
				}
			}
			
			else if(A[1][0] == 1)	{	//middle-left
				if(A[1][2] == -1)	{	//middle-right
					A[1][2] = 2;
					return;
				}
			}
			else if(A[1][2] == 1)	{	//middle-right
				if(A[1][0] == -1)	{	//middle-left
					A[1][0] = 2;
					return;
				}
			}
		}
		//---END OF BLOCK---|
		
		//CENTRE (if centre is free, play center)
		if(A[1][1] ==  -1)	{
			A[1][1]  = 2;
			return;
		}
		//---END OF CENTRE---|
		
		//OPP CORNER
		if(A[0][0] == 1)	{	//top-left		
			if(A[2][2] == -1)	{	//bottom-right
				A[2][2] = 2;
				return;
			}
		}
		if(A[2][2] == 1)	{	//bottom-right
			if(A[0][0] == -1)	{	//top-left
				A[0][0] = 2;
				return;
			}
		}
		
		 if(A[0][2] == 1)	{	//top-right
			if(A[2][0] == -1)	{	//bottom-left
				A[2][0] = 2;
				return;
			}
		}
		if(A[2][0] == 1)	{	//bottom-left
			if(A[0][2] == -1)	{	//top-right
				A[0][2] = 2;
				return;
			}
		}
		//---END OF OPPOSITE CORNER--|
		
		//EMPTY CORNER	
		if(A[0][0] == -1)	{	//top-left
			A[0][0] = 2;
			return;
		}
		if(A[0][2] == -1)	{	//top-right
			A[0][2] = 2;
			return;
		}
		if(A[2][0] == -1)	{	//bottom-left
			A[2][0] = 2;
			return;
		}
		if(A[2][2] == -1)	{	//bottom-right
			A[2][2] = 2;
			return;
		}		
		//---END OF EMPTY CORNER--|
		
		//EMPTY SIDE
		if(A[0][1] == -1)	{
			A[0][1] = 2;
			return;
		}
		if(A[1][0] == -1)	{
			A[1][0] = 2;
			return;
		}
		if(A[2][1] == -1)	{
			A[2][1] = 2;
			return;
		}
		if(A[1][2] == -1)	{
			A[1][2] = 2;
			return;
		}
		//---END OF EMPTY SIDE---|
		
	} //end of playOpp()
	
	public void winCheck()	{		
		if(A[0][0] == 1 && A[0][1] == 1 && A[0][2] == 1)	{ // ^ --- 
			win = true;
			ws = 1;
			we = 3;
			repaint();			
		}
		else if(A[1][0] == 1 && A[1][1] == 1 && A[1][2] == 1)	{ // ~ --- 
			win = true;
			ws = 4;
			we = 6;
			repaint();			
		}
		else if(A[2][0] == 1 && A[2][1] == 1 && A[2][2] == 1)	{ // _ --- 
			win = true;
			ws = 7;
			we = 9;
			repaint();			
		}
		
		else if(A[0][0] == 1 && A[1][0] == 1 && A[2][0] == 1)	{	// < |
			win = true;
			ws = 1;
			we = 7;
			//showStatus("X WIN");
			repaint();			
		}
		else if(A[0][1] == 1 && A[1][1] == 1 && A[2][1] == 1)	{	// < | >
			win = true;
			ws = 2;
			we = 8;
			//showStatus("X WIN");
			repaint();			
		}
		else if(A[0][2] == 1 && A[1][2] == 1 && A[2][2] == 1)	{	//  | >
			win = true;
			ws = 3;
			we = 9;
			//showStatus("X WIN");
			repaint();			
		}
		
		else if(A[0][0] == 1 && A[1][1] == 1 && A[2][2] == 1)	{	// \
			win = true;
			ws = 1;
			we = 9;
			//showStatus("X WIN");
			repaint();			
		}
		
		else if(A[0][2] == 1 && A[1][1] == 1 && A[2][0] == 1)	{	// /
			win = true;
			ws = 3;
			we = 7;
			//showStatus("X WIN");
			repaint();			
		}
		//////
		if(A[0][0] == 2 && A[0][1] == 2 && A[0][2] == 2)	{ // ^ --- 
			win = true;
			ws = 1;
			we = 3;
			repaint();			
		}
		else if(A[1][0] == 2 && A[1][1] == 2 && A[1][2] == 2)	{ // ~ --- 
			win = true;
			ws = 4;
			we = 6;
			repaint();			
		}
		else if(A[2][0] == 2 && A[2][1] == 2 && A[2][2] == 2)	{ // _ --- 
			win = true;
			ws = 7;
			we = 9;
			repaint();			
		}
		
		else if(A[0][0] == 2 && A[1][0] == 2 && A[2][0] == 2)	{	// < |
			win = true;
			ws = 1;
			we = 7;
			//showStatus("X WIN");
			repaint();			
		}
		else if(A[0][1] == 2 && A[1][1] == 2 && A[2][1] == 2)	{	// < | >
			win = true;
			ws = 2;
			we = 8;
			//showStatus("X WIN");
			repaint();			
		}
		else if(A[0][2] == 2 && A[1][2] == 2 && A[2][2] == 2)	{	//  | >
			win = true;
			ws = 3;
			we = 9;
			//showStatus("X WIN");
			repaint();			
		}
		
		else if(A[0][0] == 2 && A[1][1] == 2 && A[2][2] == 2)	{	// 
			win = true;
			ws = 1;
			we = 9;
			//showStatus("X WIN");
			repaint();			
		}
		
		else if(A[0][2] == 2 && A[1][1] == 2 && A[2][0] == 2)	{	// /
			win = true;
			ws = 3;
			we = 7;
			//showStatus("X WIN");
			repaint();			
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
		
		
		g.setColor(Color.yellow);
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
		
		g.setColor(Color.blue);
		/* DRAW X */
		if(A[0][0] == 1)	{
			dx = ptov(0.09, dim.width);
			dy = ptov(0.09, dim.height);
			drawX(g, dx, dy);
		}
		if(A[0][1] == 1)	{
			dx = ptov(0.38, dim.width);
			dy = ptov(0.09, dim.height);
			drawX(g, dx, dy);
		}
		if(A[0][2] == 1)	{
			dx = ptov(0.68, dim.width);
			dy = ptov(0.09, dim.height);
			drawX(g, dx, dy);
		}
		if(A[1][0] == 1)	{
			dx = ptov(0.09, dim.width);
			dy = ptov(0.38, dim.height);
			drawX(g, dx, dy);
		}
		if(A[1][1] == 1)	{
			dx = ptov(0.38, dim.width);
			dy = ptov(0.38, dim.height);
			drawX(g, dx, dy);
		}
		if(A[1][2] == 1)	{
			dx = ptov(0.68, dim.width);
			dy = ptov(0.38, dim.height);
			drawX(g, dx, dy);
		}
		if(A[2][0] == 1)	{
			dx = ptov(0.09, dim.width);
			dy = ptov(0.68, dim.height);
			drawX(g, dx, dy);
		}
		if(A[2][1] == 1)	{
			dx = ptov(0.38, dim.width);
			dy = ptov(0.68, dim.height);
			drawX(g, dx, dy);
		}
		if(A[2][2] == 1)	{
			dx = ptov(0.68, dim.width);
			dy = ptov(0.68, dim.height);
			drawX(g, dx, dy);
		}
		
		g.setColor(Color.red);
		/* DRAW CIRCLES */
		if(A[0][0] == 2)	{
			dx = ptov(0.09, dim.width);
			dy = ptov(0.09, dim.height);
			drawC(g, dx, dy);
		}
		if(A[0][1] == 2)	{
			dx = ptov(0.38, dim.width);
			dy = ptov(0.09, dim.height);
			drawC(g, dx, dy);
		}
		if(A[0][2] == 2)	{
			dx = ptov(0.68, dim.width);
			dy = ptov(0.09, dim.height);
			drawC(g, dx, dy);
		}
		if(A[1][0] == 2)	{
			dx = ptov(0.09, dim.width);
			dy = ptov(0.38, dim.height);
			drawC(g, dx, dy);
		}
		if(A[1][1] == 2)	{
			dx = ptov(0.38, dim.width);
			dy = ptov(0.38, dim.height);
			drawC(g, dx, dy);
		}
		if(A[1][2] == 2)	{
			dx = ptov(0.68, dim.width);
			dy = ptov(0.38, dim.height);
			drawC(g, dx, dy);
		}
		if(A[2][0] == 2)	{
			dx = ptov(0.09, dim.width);
			dy = ptov(0.68, dim.height);
			drawC(g, dx, dy);
		}
		if(A[2][1] == 2)	{
			dx = ptov(0.38, dim.width);
			dy = ptov(0.68, dim.height);
			drawC(g, dx, dy);
		}
		if(A[2][2] == 2)	{
			dx = ptov(0.68, dim.width);
			dy = ptov(0.68, dim.height);
			drawC(g, dx, dy);
		}
			
	
		if(win == true)	{	//if somebody has WON!..
			
			int x1 = 0, x2 = 0;
			int y1 = 0, y2 = 0;
			
			if(ws == 1 && we == 3)	{
				x1 = ptov(0.1, dim.width);
				x2 = ptov(0.9, dim.width);
				y1 = ptov(0.2, dim.height);
				y2 = y1;
			}
			
			else if(ws == 4 && we == 6)	{
				x1 = ptov(0.1, dim.width);
				x2 = ptov(0.9, dim.width);
				y1 = ptov(0.5, dim.height);
				y2 = y1;
			}
			
			else if(ws == 7 && we == 9)	{
				x1 = ptov(0.1, dim.width);
				x2 = ptov(0.9, dim.width);
				y1 = ptov(0.8, dim.height);
				y2 = y1;
			}
			
			else if(ws == 1 && we == 7)	{
				x1 = ptov(0.2, dim.width);
				x2 = x1;
				y1 = ptov(0.1, dim.height);
				y2 = ptov(0.9, dim.height);
			}
			else if(ws == 2 && we == 8)	{
				x1 = ptov(0.5, dim.width);
				x2 = x1;
				y1 = ptov(0.1, dim.height);
				y2 = ptov(0.9, dim.height);
			}
			else if(ws == 3 && we == 9)	{
				x1 = ptov(0.8, dim.width);
				x2 = x1;
				y1 = ptov(0.1, dim.height);
				y2 = ptov(0.9, dim.height);
			}
			
			else if(ws == 1 && we == 9)	{
				x1 = ptov(0.1, dim.width);
				x2 = ptov(0.9, dim.width);
				y1 = ptov(0.1, dim.height);
				y2 = ptov(0.9, dim.height);
			}
			else if(ws == 3 && we == 7)	{
				x1 = ptov(0.9, dim.width);
				x2 = ptov(0.1, dim.width);
				y1 = ptov(0.1, dim.height);
				y2 = ptov(0.9, dim.height);
			}
			
			g.setColor(Color.green);

			g.drawLine(x1, y1, x2, y2);
			g.drawLine(x1-1, y1, x2-1, y2);
		}	
//		drawX(g, dx, dy);
//		drawC(g, dx, dy);
//		//showStatus(" Best if you do not resize ");
	}

	public void paint(Graphics g)	{
		update(g);
	}
}

class MyMouseAdapter extends MouseAdapter	{
	Game T;

	MyMouseAdapter(Game T)	{
		this.T = T;
	}

	public void mouseClicked(MouseEvent ME)	{
		//T.showStatus(" CLICK!");
		T.hit(ME.getX(), ME.getY());
		
		try {
			Thread.sleep(250);			
		}
		catch (InterruptedException E)	{ }
		
		T.winCheck();
		
		T.playOpp();
		T.winCheck();
		T.repaint();
	}
}

class MyMouseMotionAdapter extends MouseMotionAdapter	{
	Game T;

	MyMouseMotionAdapter(Game T)	{
		this.T = T;
	}

	public void mouseMoved(MouseEvent ME)	{
		//T.showStatus( " ( " + ME.getX() + ", " + ME.getY() + ")");
	}	
}

class MyWindowAdapter extends WindowAdapter	{
	//for closing the window
	public void windowClosing(WindowEvent w)	{
		System.exit(0);
	}
}

class MyKeyAdapter extends KeyAdapter	{
	MainMenu m;

	MyKeyAdapter(MainMenu m)	{
		this.m = m;
	}
	
	public void keyPressed(KeyEvent K)	{
		int code = K.getKeyCode();
		switch(code)	{
			case KeyEvent.VK_DOWN: m.option_ch++;	   break;
			case KeyEvent.VK_UP	 : m.option_ch--;	   break;
			case KeyEvent.VK_ENTER: m.choice = m.option_ch; 
									break;
		}
		m.repaint();
	}
}

public class tictac {		//MAIN CLASS
	
	public static void main(String args[])	{
		MainMenu MM = new MainMenu();
		Game T = new Game();
	
		int m_choice = 0;
		
		MM.setSize(new Dimension(300, 300));
		MM.setTitle(" Main Menu ");
		
		MM.setVisible(true);
		
		while(MM.getChoice() != 0) { 
			System.out.print("");
		}	//busy wait
		
		switch( MM.getChoice() )	{
			case 0:	MM.setVisible(false);
					T.setSize(new Dimension(330, 330));
					T.setTitle(" TIC TAC TOE ");
					T.setVisible(true);
					break;
		}
	}	
}
