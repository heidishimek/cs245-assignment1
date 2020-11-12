import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NBody extends JPanel implements ActionListener
{
							/* VARIABLES TO STORE ...*/
	private String name; 		//name of body
	private double yVelocity; 	//y velocity
	private double xVelocity; 	//x velocity
	private int xCoordinate; 	//x coordinate
	private int yCoordinate; 	//y coordinate
	private double mass; 		//mass
	private String size;		//size
	private Color color;		//color
	private double forceX;		//the x force
	private double forceY;		//the y force
	private double scale;		//scale
	private  Lists<NBody> list; //list

	public NBody(String name, String size, String mass, String xCoordinate, String yCoordinate, String xVelocity, String yVelocity)
	{
		/* SETS ALL ABOVE VARIABLES TO NAME */
		this.name = name;
		this.size = size.substring(1);
		this.mass = Double.parseDouble(mass);
		this.xCoordinate = Integer.parseInt(xCoordinate);
		this.yCoordinate = Integer.parseInt(yCoordinate);
		this.xVelocity = Double.parseDouble(xVelocity);
		this.yVelocity = Double.parseDouble(yVelocity);

		Random random = new Random();
		color = new Color(random.nextInt(), random.nextInt(), random.nextInt());
	}

	/* RETURNS Y VELOCITY */
	public double getYVelocity()
	{
		return this.yVelocity;
	}

	/* RETURNS X VELOCITY */
	public double getXVelocity()
	{
		return this.xVelocity;
	}

	/* RETURNS Y COORDINATE */
	public int getYCoordinate()
	{
		return yCoordinate;
	}

	/* RETURNS X COORDINATE */
	public int getXCoordinate()
	{
		return xCoordinate;
	}

	/* RETURNS MASS */
	public double getMass()
	{
		return mass;
	}

	/* RETURNS COLOR */
	public Color getColor()
	{
		return color;
	}

	/* SETS PREVIOUS LIST AND SCALE TO NEW LIST AND SCALE */
	public NBody(Lists<NBody> list2, double scale2)
	{
		list = list2;
		scale = scale2;
	}

	/* PAINTS CELESTIAL BODIES */
	public void paint(Graphics paint)
	{	
		Timer time = new Timer(500, this);
		super.paint(paint);
		for (int i = 0; i < list.size(); i++)
		{
			paint.setColor(list.get(i).getColor());
			paint.fillOval(list.get(i).getXCoordinate(), list.get(i).getYCoordinate(), list.size(), list.size());
		}
		time.start();
	}

	/* CALCULATES FORCE */
	public void force(NBody x, double y)
	{
		NBody x2 = this;
		double x1 = x.xCoordinate - x2.xCoordinate;
		double y1 = x.yCoordinate - x2.yCoordinate;
		double distance = Math.sqrt(x1 * x1 + y1 * y1);
		double finalForce = (6.673e-11 * x.mass * x2.mass / ((distance * distance) / y));
		x.forceX += finalForce * x1 / distance;
		x.forceY += finalForce * y1 /distance;
	}

	/* UPDATES POSITION OF CELESTIAL BODIES */
	public void updatePosition()
	{
		xVelocity += forceX / mass;
		yVelocity += forceY / mass;
		xCoordinate += xVelocity;
		yCoordinate += yVelocity;
	}

	/* UPDATES SHAPES OF CELESTIAL BODIES */
	public void shapes()
	{
		int i;
		for (i = 0; i < list.size(); i++)
		{
			list.get(i).force((list.get(i + 1)), scale);
			list.get(i).updatePosition(); 
		}
		if (list.size() > 1)
		{
			list.get(i).force(list.get(i - 1), scale);
			list.get(i).updatePosition();
		}
	}

	/* RESETS FORCE TO 0.0 */
	public void resetForce()
	{
		xVelocity = 0;
		yVelocity = 0;
	}

	/* CALLED WHEN AN ACTION IS PERFORMED */
	public void actionPerformed(ActionEvent e)
	{
		shapes();
		repaint();
	}


	/* MAIN FUNCTION - READS FILE */
	public static void main(String[] args)
	{
		Lists<NBody> newList = null;
		double temp = 0;

		File file = new File("nbody_input.txt");

		try
		{
			File input = new File(args[0]);
			Scanner scan = new Scanner(input);
			String listName = scan.nextLine();

			if (listName.equals("LinkedList"))
			{
				newList = new LinkedList<>();
			}
			else if (listName.equals("ArrayList"))
			{
				newList = new ArrayList<>();
			}
			else
			{
				System.out.println("Invalid.");
			}

			temp = Double.parseDouble(scan.nextLine());
			scan.useDelimiter(",");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error.");
		}

		NBody nBody = new NBody(newList, temp);
		JFrame jFrame = new JFrame();
		jFrame.setTitle("NBody");
		jFrame.setSize(new Dimension(768, 768));
		jFrame.add(nBody);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}