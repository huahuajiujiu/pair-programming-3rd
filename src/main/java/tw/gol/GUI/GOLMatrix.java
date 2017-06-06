package tw.gol.GUI;

/**
 * Created by win10 on 2017/6/3.
 */
public interface GOLMatrix {
    public void randSource(double factor);
    public void updateLife();
    public Boolean isAlive(int x, int y);
    public void loadFromFile(String path);
}
