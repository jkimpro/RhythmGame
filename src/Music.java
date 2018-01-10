
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread { //작은 프로그램이라고 보면 되는 Thread
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop)
	{
		try
		{
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime()		//어떤 위치에서 실행되고 있는지 알려줌.
	{
		if(player == null)
		{
			return 0;
		}
		return player.getPosition();		//0.001 초 단위 까지 return 해줌
	}
	public void close()
	{
		isLoop = false;
		player.close();
		this.interrupt();	//해당 쓰래드를 중지 상태로 만들어줌 (게임과 별도로 따로 재생되는 것)
	}
	
	@Override
	public void run()		//run() 함수는 쓰래드를 상속 받으면 반드시 사용해야 하는 메소드이다.
	{
		try
		{
			do
			{
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}
			while(isLoop);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
}
