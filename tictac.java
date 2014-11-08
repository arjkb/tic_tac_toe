/*
	Main implementation of tic tac toe

*/

import java.awt.*;
import java.applet.*;

/*
<APPLET code = "tictac" width = 330 height = 330 >
</APPLET>
*/

public class tictac extends Applet	{
	String msg;
	public void init()	{
		msg = "Hello";
	}

	public void update(Graphics g)	{
		g.drawString(msg, 20, 20);
	}

	public void paint(Graphics g)	{
		update(g);
	}
}
