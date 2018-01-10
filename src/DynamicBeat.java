
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("images/quitButtonBasic.png"));
	
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("images/rightButtonBasic.png"));
	
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("images/hardButtonBasic.png"));
	
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("images/backButtonBasic.png"));
	
	private Image background = new ImageIcon(Main.class.getResource("images/introBackground(Title).jpg")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	//������ �ʱ�ȭ �۾� main class ���� ������ ��.
	Music introMusic =new Music("introMusic.mp3", true); //true �� �־��־��� ������ ���� �������� ����ؼ� �ݺ� ����� �ϰ� �ȴ�.
	
	private int nowSelected = 0;
	
	public static Game game;			//������Ʈ ��ü���� ����ϴ� ������ ����� ���� static �� �����
	//�ֳ��ϸ� game �� �ѹ��� ���� �Ǿ�� �ϴ� �� , �ϳ��� ����Ǿ����� �ִ� ���߿� �ٸ� ���� ����Ǹ� �ȵǱ� ����.
	
	public DynamicBeat()
	{
		trackList.add(new Track("All i wanna do Title Image.png", "All i wanna do Start Image.jpg", "All i wanna do Game Image.jpg",
				"All i wanna do Selected.mp3" ,"JPark - All i wanna do.mp3","JPark - All I Wanna Do"));									//trackList �� �־� �ְ� ��. index =0;nowSelected =0;
		trackList.add(new Track("D Title Image.png", "D Start Image.jpg", "D Game Image.jpg",
				"D Selected.mp3" ,"Dean - D.mp3","Dean - D"));																//trackList �� �־� �ְ� ��. index =1;nowSelected =1;
		trackList.add(new Track("Fix Me Title Image.png", "Fix Me Start Image.jpg", "Fix Me Game Image.jpg",
				"Fix Me Selected.mp3" ,"����������� - Fix Me.mp3", "BOLBBALGAN4 - FIX ME"));												//trackList �� �־� �ְ� ��. index =2; nowSelected =2;
		
		//trackList �� �ٲٰ� ������ ���ϴ� ������ �ٲٸ� �ȴ�.
		//���� ���������� (�ε尡 �������) trackList �ʱ�ȭ �κ��� ���� ���κ����� �ø�.
		//track list ����� -> ��ư �̺�Ʈ ����.
		
		setUndecorated(true);																					//�⺻���� �����ϴ� �޴��ٰ� ������� �ȴ�.
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);																					//����ڰ� ���Ƿ� �ٲܼ� ����
		setLocationRelativeTo(null);									 										//���߾ӿ� �߰� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusic.start(); 				//���� ȭ���� ������ �ڵ������� ������ ������ ��.
		
		
		//�Ϲ����� exit ��ư
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try
				{
					Thread.sleep(1000);
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		//start ��ư
		startButton.setBounds(40, 450, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				introMusic.close();
				enterMain();
			}
		});
		add(startButton);
		
		//quit ��ư
		quitButton.setBounds(40, 580, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try
				{
					Thread.sleep(1000);
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		
		
		//���� ��ư ������
		leftButton.setVisible(false);							//���� ó���� leftButton�� �ʿ����� �ʱ� ����
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//���� ��ư �̺�Ʈ
				selectLeft();
			}
		});
		add(leftButton);
		
		
		//������ ��ư ������
		rightButton.setVisible(false);							//���� ó���� rightButton�� �ʿ����� �ʱ� ����
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//������ ��ư �̺�Ʈ
				selectRight();
			}
		});
		add(rightButton);
		
		
		easyButton.setVisible(false);							
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//���̵� ���� �̺�Ʈ
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
		
		hardButton.setVisible(false);						
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
		
		backButton.setVisible(false);						
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//���� ȭ������ ���ư��� �̺�Ʈ
				backMain();
				
			}
		});
		add(backButton);
		
		
		
		//�޴��� �����.
		menuBar.setBounds(0,0,1280,30);
		//�⺻���� ���콺 �̺�Ʈ �޴� ����
		menuBar.addMouseListener(new MouseAdapter()
		{	@Override
				public void mousePressed(MouseEvent e)
				{
					mouseX = e.getX();
					mouseY = e.getY();
				}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e) // �巡�� ���� ���� ���� x, y ��ǥ�� �����ͼ� �ڵ����� JFrame�� ��ġ�� �ٲ���
			{
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
				
			}
		});
		add(menuBar);
	}
	
	public void paint(Graphics g)					//������ �ʱ� �۾���.
	{
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics(); //�׷��� ��ü�� ����.
		screenDraw((Graphics2D)screenGraphic);		//��Ʈ �������� ���� �� ����ȯ	//�׸��� ����
		g.drawImage(screenImage,0,0, null);			//��ũ�� �̹����� 0,0 ���� �׷���.
	}
	public void screenDraw(Graphics2D g) //2D�� ����ȯ
	{
		g.drawImage(background, 0, 0, null);	//�ܼ��ϰ� �̹����� �������. //�Ϲ����� �̹��� Ȥ��, gif �� �̰����� �����.

		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340,100, null);
			g.drawImage(titleImage, 340, 70,  null);
		}
		if(isGameScreen)
		{
			game.screenDraw(g);
		}
		
		paintComponents(g);							//�̹����� �ܼ��ϰ� �׷��ִ� �� �̿ܿ� JFrame�� JLabel�� �߰����ִ� ����̴�. (������ ��) //add�� ���Ѱ�
		try {
			Thread.sleep(5);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		this.repaint();								//���α׷��� ����Ǵ� �������� ��� Ʋ���ְ� ��.
	}
	
	public void selectTrack(int nowSelected)
	{
		if(selectedMusic !=null)
		{
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("images/" +trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("images/" +trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	//���� �������� ���� �Ϳ� ���� �̺�Ʈ ó��
	public void selectLeft()
	{
		if(nowSelected ==0)
		{
			nowSelected = trackList.size() -1;		
		}
		else
		{
			nowSelected--;
		}
		selectTrack(nowSelected);
	}
	
	public void selectRight()
	{
		if(nowSelected == trackList.size() -1)
		{
			nowSelected =0;
			
		}
		else
		{
			nowSelected++;
		}
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty)
	{
		if(selectedMusic != null)
		{
			selectedMusic.close();
		}
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("images/" +trackList.get(nowSelected).getGameImage())).getImage();	//trackList ��, ArrayList ��뿡 �ͼ��� ����
		// ������ ���� ArrayList��Į��.get(index).getter�ش�����(index)
		backButton.setVisible(true);
		isGameScreen =true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);	//main frame �� Ű���� ��Ŀ���� ������..
		
	}
	public void backMain()
	{
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("images/mainBackground.jpg")).getImage();	//trackList ��, ArrayList ��뿡 �ͼ��� ����
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen =false;
		game.close();
		
	}
	public void enterMain()
	{
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("images/mainBackground.jpg")).getImage();

		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
}