import java.awt.*;

public interface PaintUpdatable {
    void paint(Graphics g);
    void update(int ms);
}