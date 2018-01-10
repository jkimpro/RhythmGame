
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
	//일종의 초기화 작업 main class 에서 가지고 옴.
	Music introMusic =new Music("introMusic.mp3", true); //true 를 넣어주었기 때문에 종료 시점까지 계속해서 반복 재생을 하게 된다.
	
	private int nowSelected = 0;
	
	public static Game game;			//프로젝트 전체에서 사용하는 변수로 만들기 위해 static 을 사용함
	//왜냐하면 game 은 한번만 시행 되어야 하는 데 , 하나가 실행되어지고 있는 와중에 다른 것이 시행되면 안되기 때문.
	
	public DynamicBeat()
	{
		trackList.add(new Track("All i wanna do Title Image.png", "All i wanna do Start Image.jpg", "All i wanna do Game Image.jpg",
				"All i wanna do Selected.mp3" ,"JPark - All i wanna do.mp3","JPark - All I Wanna Do"));									//trackList 에 넣어 주게 됨. index =0;nowSelected =0;
		trackList.add(new Track("D Title Image.png", "D Start Image.jpg", "D Game Image.jpg",
				"D Selected.mp3" ,"Dean - D.mp3","Dean - D"));																//trackList 에 넣어 주게 됨. index =1;nowSelected =1;
		trackList.add(new Track("Fix Me Title Image.png", "Fix Me Start Image.jpg", "Fix Me Game Image.jpg",
				"Fix Me Selected.mp3" ,"볼빨간사춘기 - Fix Me.mp3", "BOLBBALGAN4 - FIX ME"));												//trackList 에 넣어 주게 됨. index =2; nowSelected =2;
		
		//trackList 를 바꾸고 싶으면 더하는 순서만 바꾸면 된다.
		//오류 방지상으로 (로드가 길어지면) trackList 초기화 부분을 제일 윗부분으로 올림.
		//track list 만들고 -> 버튼 이벤트 실행.
		
		setUndecorated(true);																					//기본으로 존재하는 메뉴바가 사라지게 된다.
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);																					//사용자가 임의로 바꿀수 없음
		setLocationRelativeTo(null);									 										//정중앙에 뜨게 함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusic.start(); 				//시작 화면이 나오고 자동적으로 음악이 나오게 됨.
		
		
		//일반적인 exit 버튼
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);																		//우리가 일반적으로 생각하는 커스텀한 형태의 탬플릿이 아니기 때문에 수정을 해주어야 한다.	
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
		
		//start 버튼
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
		
		//quit 버튼
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
		
		
		//왼쪽 버튼 누르기
		leftButton.setVisible(false);							//제일 처음은 leftButton이 필요하지 않기 때문
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
				//왼쪽 버튼 이벤트
				selectLeft();
			}
		});
		add(leftButton);
		
		
		//오른쪽 버튼 누르기
		rightButton.setVisible(false);							//제일 처음은 rightButton이 필요하지 않기 때문
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
				//오른쪽 버튼 이벤트
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
				//난이도 쉬운 이벤트
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
				//메인 화면으로 돌아가는 이벤트
				backMain();
				
			}
		});
		add(backButton);
		
		
		
		//메뉴바 만들기.
		menuBar.setBounds(0,0,1280,30);
		//기본적인 마우스 이벤트 받는 과정
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
			public void mouseDragged(MouseEvent e) // 드래그 순간 순간 마다 x, y 좌표를 가져와서 자동으로 JFrame의 위치를 바꿔줌
			{
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
				
			}
		});
		add(menuBar);
	}
	
	public void paint(Graphics g)					//일종의 초기 작업임.
	{
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics(); //그래픽 객체를 얻어옴.
		screenDraw((Graphics2D)screenGraphic);		//폰트 깨짐현상 방지 및 형변환	//그림을 얻어옴
		g.drawImage(screenImage,0,0, null);			//스크린 이미지를 0,0 부터 그려짐.
	}
	public void screenDraw(Graphics2D g) //2D로 형변환
	{
		g.drawImage(background, 0, 0, null);	//단순하게 이미지를 출력해줌. //일반적인 이미지 혹은, gif 는 이것으로 출력함.

		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340,100, null);
			g.drawImage(titleImage, 340, 70,  null);
		}
		if(isGameScreen)
		{
			game.screenDraw(g);
		}
		
		paintComponents(g);							//이미지를 단순하게 그려주는 것 이외에 JFrame에 JLabel을 추가해주는 방법이다. (고정된 것) //add에 관한것
		try {
			Thread.sleep(5);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		this.repaint();								//프로그램이 종료되는 순간까지 계속 틀어주게 됨.
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
	
	//왼쪽 오른쪽을 누른 것에 대한 이벤트 처리
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
		background = new ImageIcon(Main.class.getResource("images/" +trackList.get(nowSelected).getGameImage())).getImage();	//trackList 즉, ArrayList 사용에 익숙해 질것
		// 접근할 때는 ArrayList스칼라.get(index).getter해당인자(index)
		backButton.setVisible(true);
		isGameScreen =true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);	//main frame 에 키보드 포커스가 맞춰짐..
		
	}
	public void backMain()
	{
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("images/mainBackground.jpg")).getImage();	//trackList 즉, ArrayList 사용에 익숙해 질것
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