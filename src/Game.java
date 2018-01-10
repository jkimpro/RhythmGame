
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{

	private Image noteRouteLineImage= new ImageIcon(Main.class.getResource("images/noteRouteLine.png")).getImage();
	private Image judgementLineImage= new ImageIcon(Main.class.getResource("images/judgementLine.png")).getImage();
	
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteDImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteFImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();	//영역이 넓어 인식 속도가 오래 걸리기 때문에 Space의 경우 두개를 만듦
	private Image noteRouteSpace2Image= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteJImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteKImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	private Image noteRouteLImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	
	public Game(String titleName, String difficulty, String musicTitle)
	{
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic  = new Music(this.musicTitle, false);
	}
	
	
	public void screenDraw(Graphics2D g)
	{
		g.drawImage(noteRouteSImage,228 ,30, null);
		g.drawImage(noteRouteDImage,332 ,30, null);
		g.drawImage(noteRouteFImage,436 ,30, null);
		g.drawImage(noteRouteSpace1Image,540 ,30, null);
		g.drawImage(noteRouteSpace2Image,640 ,30, null);
		g.drawImage(noteRouteJImage,744 ,30, null);
		g.drawImage(noteRouteKImage,848 ,30, null);
		g.drawImage(noteRouteLImage,952 ,30, null);
		
		g.drawImage(noteRouteLineImage,224,30,null);
		g.drawImage(noteRouteLineImage,328,30,null);
		g.drawImage(noteRouteLineImage,432,30,null);
		g.drawImage(noteRouteLineImage,536,30,null);
		g.drawImage(noteRouteLineImage,740,30,null);
		g.drawImage(noteRouteLineImage,844,30,null);
		g.drawImage(noteRouteLineImage,948,30,null);
		g.drawImage(noteRouteLineImage,1052,30,null);
		
		g.drawImage(gameInfoImage,0 ,660, null);
		g.drawImage(judgementLineImage,0 ,580, null);//note 판정
		
		//판정 라인 다음에 note를 그려줌 (더 위에 출력해야 하기 때문에)
		
		for(int i =0; i< noteList.size(); i++)
		{
			Note note = noteList.get(i);
			note.screenDraw(g);
		}
		
		
		
		
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.PLAIN, 26));
		g.drawString("00000", 565, 702);
		
	}
	
	public void pressS()
	{
		noteRouteSImage= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();				//반복을 안하기 위해서 false로 초기화 함. 반복일 경우에는 true;
	}
	public void releaseS()
	{
		noteRouteSImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	}
	
	public void pressD()
	{
		noteRouteDImage= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		new Music("drumSmall2.mp3",false).start();
	}
	public void releaseD()
	{
		noteRouteDImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	}
	
	public void pressF()
	{
		noteRouteFImage= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		new Music("drumSmall3.mp3",false).start();
	}
	public void releaseF()
	{
		noteRouteFImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	}
	
	public void pressSpace()
	{
		noteRouteSpace1Image= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		new Music("drumBig1.mp3",false).start();
		
	}
	public void releaseSpace()
	{
		noteRouteSpace1Image= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
		noteRouteSpace2Image= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	}
	
	public void pressJ()
	{
		noteRouteJImage= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		new Music("drumBig2.mp3",false).start();
	}
	public void releaseJ()
	{
		noteRouteJImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	}
	
	public void pressK()
	{
		noteRouteKImage= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		new Music("drumBig3.mp3",false).start();
	}
	public void releaseK()
	{
		noteRouteKImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	}
	
	public void pressL()
	{
		noteRouteLImage= new ImageIcon(Main.class.getResource("images/noteRoutePressed.png")).getImage();
		new Music("drumBig4.mp3",false).start();
	}
	public void releaseL()
	{
		noteRouteLImage= new ImageIcon(Main.class.getResource("images/noteRoute.png")).getImage();
	}
	
	@Override
	public void run()
	{
		dropNotes();
	}
	public void close()
	{
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes()
	{
		//beats 변수 안에 새로운 것만 추가하면 됨.
		//악보를 보고 비트에 따라서 찍는 것이 좋음 (악보 필요)
		// 예를 들어 4/4 60 박자라면  한박자는 1초가 된다., 첫번째 박자가 시작하는 때 
		Beat [] beats = null;
		if(titleName.equals("JPark - All I Wanna Do"))
		{
			int startTime =5000 - Main.REACH_TIME*1000;
			int gap =275 ;
			int mainGap =10;
			beats = new Beat[]
					{
							new Beat(startTime , "S"),
							new Beat(startTime + gap*2 , "D"),
							new Beat(startTime + gap*4 , "F"),
							new Beat(startTime + gap*6 , "Space"),
							new Beat(startTime + gap*7 , "Space"),
							

							new Beat(startTime + gap*10 , "D"),
							new Beat(startTime + gap*12 , "F"),
							new Beat(startTime + gap*14 , "Space"),
							new Beat(startTime + gap*16 , "Space"),
							new Beat(startTime + gap*18, "S"),
							//------------------------------------경계
							
							new Beat(startTime + gap*21, "S"),
							new Beat(startTime + gap*23 , "F"),
							new Beat(startTime + gap*25 , "Space"),
							new Beat(startTime + gap*27, "S"),	
							new Beat(startTime + gap*28 , "F"),
							
							new Beat(startTime + gap*31, "S"),
							new Beat(startTime + gap*33 , "D"),
							new Beat(startTime + gap*35 , "F"),
							new Beat(startTime + gap*37, "Space"),
							new Beat(startTime + gap*38, "S"),
							
							new Beat(startTime + gap*42 , "D"),
							new Beat(startTime + gap*44, "F"),
							new Beat(startTime + gap*46 , "L"),
							new Beat(startTime + gap*48 , "K"),
							new Beat(startTime + gap*49 , "K"),
							//--------------------------------------
							

							new Beat(startTime + gap*51 , "D"),
							new Beat(startTime + gap*53, "F"),
							new Beat(startTime + gap*55 , "L"),
							new Beat(startTime + gap*57 , "K"),
							new Beat(startTime + gap*58 , "K"),
							
							
							new Beat(startTime + gap*61 , "D"),
							new Beat(startTime + gap*63, "F"),
							new Beat(startTime + gap*65 , "L"),
							new Beat(startTime + gap*67 , "K"),
							new Beat(startTime + gap*68 , "K"),
							
							new Beat(startTime + gap*71 , "D"),
							new Beat(startTime + gap*73, "F"),
							new Beat(startTime + gap*75 , "L"),
							new Beat(startTime + gap*77 , "K"),
							new Beat(startTime + gap*78 , "K"),
							
								
					};
		
		}
		else if(titleName.equals("Dean - D"))
		{
			int startTime =1000;
			beats = new Beat[]
					{
							new Beat(startTime, "Space"),
					};
		}
		else if(titleName.equals("BOLBBALGAN4 - FIX ME"))
		{
			int startTime =1000;
			beats = new Beat[]
					{
							new Beat(startTime, "Space"),
					};
		}
		
		int i =0;
		gameMusic.start();

		while(i<beats.length && !isInterrupted())	//텀을 두면서 노트를 떨어트려 주기 위한것.
		{
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime())
			{
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped)
			{
				try {
					Thread.sleep(5);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
