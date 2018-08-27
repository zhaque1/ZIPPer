
import gui.MainWindow;

import javax.swing.SwingUtilities;


public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			SwingUtilities.invokeLater(new Runnable(){
				
				@Override
				public void run(){
					MainWindow ex = new MainWindow();
					ex.setVisible(true);
				}
				
				
			});
	}

}
