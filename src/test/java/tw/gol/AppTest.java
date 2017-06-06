package tw.gol;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tw.gol.GUI.Imp.GOLMatrixImp;

/**
 * Unit test for simple GOLMatrixImp.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testGOLMatrixImp()
    {
        assertTrue( true );
    }


    public void testInit_normal(){
        GOLMatrixImp GOLMatrixImp = new GOLMatrixImp(3,3);

        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            arr[1][i] = 1;
        }

        assertEquals(GOLMatrixImp.initView(arr),true);

    }

    public void testUpdateLife_normal(){
        GOLMatrixImp GOLMatrixImp = new GOLMatrixImp(3, 3);
        int[][] graph = new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        String grapnew = "010\n010\n010\n";
        GOLMatrixImp.initView(graph);
        GOLMatrixImp.updateLife();
        assertEquals(GOLMatrixImp.toString(), grapnew);
    }

    public void testUpdateLife_In2Times_normal(){
        GOLMatrixImp GOLMatrixImp = new GOLMatrixImp(3, 3);
        int[][] graph = new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        String grapnew = "000\n111\n000\n";
        GOLMatrixImp.initView(graph);
        GOLMatrixImp.updateLife();
        GOLMatrixImp.updateLife();
        assertEquals(GOLMatrixImp.toString(), grapnew);
    }

    public void testUpdateLife_unnormal(){
        GOLMatrixImp GOLMatrixImp = new GOLMatrixImp(2, 4);
        int[][] graph = new int[][]{
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        String grapnew = "0110\n0110\n";
        GOLMatrixImp.initView(graph);
        GOLMatrixImp.updateLife();
        assertEquals(GOLMatrixImp.toString(), grapnew);
    }


    public void testUpdateLife_testmodel2(){
        GOLMatrixImp GOLMatrixImp = new GOLMatrixImp(3, 3);
        int[][] graph = new int[][]{
                {0, 0, 0},
                {1, 0, 1},
                {1, 0, 0}
        };
        String grapnew = "000\n010\n010\n";
        GOLMatrixImp.initView(graph);
        GOLMatrixImp.updateLife();
        assertEquals(GOLMatrixImp.toString(), grapnew);
    }

}
