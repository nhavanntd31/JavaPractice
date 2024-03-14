public class ThreadStart {

    public static void main(String[] args){
        PingPongGame pingPongGame = new PingPongGame();
        new Thread(pingPongGame).start();
        SinDraw sinDraw = new SinDraw();
        new Thread(sinDraw).start();
        DrawingWin drawingWin = new DrawingWin();
        new Thread(drawingWin).start();
    }
}