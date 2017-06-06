package tw.gol.GUI.Imp;

import tw.gol.GUI.GOLMatrix;

import java.io.*;

/**
 * Created by win10 on 2017/6/3.
 */
public class GOLMatrixImp implements GOLMatrix {

    //    视图中默认矩形的长度
    private int xLength= 3;
    private int yLength = 3;


    private int[][] view1 = null;
    private int[][] view2 = null;
    private boolean isView1InUse = false;
    public GOLMatrixImp(int xLength, int yLength) {
        this.xLength = xLength;
        this.yLength = yLength;
        view1 = new int[xLength][yLength];
        view2 = new int[xLength][yLength];
    }

    //初始化视图
    public boolean initView(int[][] arr){
        if(arr.length!=xLength || arr[0].length!=yLength){
            return false;
        }

        for(int i = 0; i< view1.length; i++){
            for (int j = 0; j < view1[0].length; j++) {
                view1[i][j] = arr[i][j];
            }
        }
        isView1InUse = true;
        return true;
    }

//    更新像素值
    public  boolean updataPixel(int x ,int y){
        if(x<0 || y<0 || x>=xLength || y>=yLength){
            return false;
        }

        int[][] oldView = view1, newView = view2;
        if(!isView1InUse){
            oldView = view2;
            newView = view1;
        }
//        int temp = oldView[x-1][y-1]+oldView[x-1][y]+oldView[x-1][y+1]
//                +oldView[x][y-1]+oldView[x][y+1]+oldView[x+1][y-1]+oldView[x+1][y]+oldView[x+1][y+1];
        int temp = 0;
        for(int i=x-1; i<= x+1; i++){
            for(int j=y-1; j<= y+1; j++){
                if(i<0 || j<0 || i>=xLength || j>=yLength || (i == x && j == y)){
                    continue;
                }
                temp += oldView[i][j];
            }
        }
        if (temp== 3){
            newView[x][y] = 1;
        }else if(temp ==2){
            newView[x][y] = oldView[x][y];
        }else{
            newView[x][y] = 0;
        }
        return true;
    }
    public void loadFromFile(String path){
        File file = new File(path);
        BufferedReader reader = null;

        int[][] nowView = view1;
        if(!isView1InUse) {
            nowView = view2;
        }
        for (int i = 0; i < nowView.length; i++) {
            for (int j = 0; j < nowView[i].length; j++) {
                nowView[i][j] = 0;
            }
        }
        int index = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line  = reader.readLine();
            while(line != null && index<nowView.length){
                for(int i=0; i<line.length()&&i<nowView[index].length; i++){
                    if(line.charAt(i) != ' '){
                        nowView[index][i] = 1;
                    }else {
                        nowView[index][i] = 0;
                    }
                }
                line = reader.readLine();
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void randSource(double factor){
        int[][] nowView = view1;
        if(!isView1InUse) {
            nowView = view2;
        }
        for (int i = 0; i < nowView.length; i++) {
            for (int j = 0; j < nowView[i].length; j++) {
                nowView[i][j] = Math.random() < factor?1:0;
            }
        }
    }

    public void updateLife(){
        int[][] nowView = view1;
        if(!isView1InUse) {
            nowView = view2;
        }
        for (int i = 0; i < nowView.length; i++) {
            for (int j = 0; j < nowView[i].length; j++) {
                updataPixel(i, j);
            }
        }
        //取反
        isView1InUse = !isView1InUse;
    }

    public Boolean isAlive(int x, int y) {

        int[][] nowView = view1;
        if(!isView1InUse) {
            nowView = view2;
        }
        return nowView[x][y]>0?true:false;
    }

    @Override
    public String toString(){

        int[][] nowView = view1;
        StringBuffer sb = new StringBuffer();
        if(!isView1InUse) {
            nowView = view2;
        }
        for (int i = 0; i < nowView.length; i++) {
            for (int j = 0; j < nowView[i].length; j++) {
                sb.append(nowView[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void printGame(){
        int[][] nowView = view1;
        if(!isView1InUse) {
            nowView = view2;
        }
        for (int i = 0; i < nowView.length; i++) {
            for (int j = 0; j < nowView[i].length; j++) {
                if(nowView[i][j] == 1){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
