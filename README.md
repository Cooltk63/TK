import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.MouseInfo;
import java.util.Random;

public class MouseMover {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            Random random = new Random();
            System.out.println("Mouse Mover started. Press Ctrl+C to stop.");
            
            while (true) {
                // Get the current mouse location
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                int x = (int) mouseLocation.getX();
                int y = (int) mouseLocation.getY();
                
                // Move the mouse slightly
                int offsetX = random.nextInt(10) - 5; // Random offset between -5 and 5
                int offsetY = random.nextInt(10) - 5;
                robot.mouseMove(x + offsetX, y + offsetY);
                
                // Wait for 4-5 seconds
                Thread.sleep(4000 + random.nextInt(1000)); // Random delay between 4000ms and 5000ms
            }
        } catch (AWTException e) {
            System.err.println("Error: Unable to control the mouse. " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Error: Program interrupted. " + e.getMessage());
        }
    }
}