/*
 * Adam Styperek | Templates
 * and open the template in the editor.
 */
package lsa;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.SingularValueDecomposition;

/**
 *
 * @author adam
 */
public class Test {
    static double[][] matrix = 
    {
        {1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0},
        {0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0},
        {1.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0},
        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0},
        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0},
        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0}    
    };    
    public static void main(String[] args)    
    {
        try
        {            
//            Matrix m = new Matrix(matrix);
//            System.out.println("Before divide: " + m.toString());
//            m.avg();
//            m.divideByAvg();
//            System.out.println("After  divide: " + m.toString());
//            double[][] scalar = m.scalar();
//            double[][] cos = m.cos();
//            Matrix scal = new Matrix(scalar);
//            Matrix co = new Matrix(cos);
//            System.out.println("Scalar");
//            System.out.println(scal.toString());
//            System.out.println("Cos");
//            System.out.println(co.toString());
            SVD svd = new SVD(matrix);
            
            
            if(svd.setSVD())
            {
                DenseDoubleMatrix2D u = new DenseDoubleMatrix2D(svd.getU());
                DenseDoubleMatrix2D v = new DenseDoubleMatrix2D(svd.getV());
                DenseDoubleMatrix2D s = new DenseDoubleMatrix2D(svd.getS());
                
                System.out.println("U: \n" + u.toString());
                System.out.println("S: \n" + s.toString());
                System.out.println("V: \n" + v.toString());
                v = new DenseDoubleMatrix2D(v.viewDice().toArray());
                System.out.println("V: \n" + v.toString());
                
                u = new DenseDoubleMatrix2D(u.viewPart(0, 0, 12, 2).toArray());
//                u = new DenseDoubleMatrix2D(u.viewPart(0, 0, 2, 9).toArray());
//                v = new DenseDoubleMatrix2D(v.viewDice().toArray());
                v = new DenseDoubleMatrix2D(v.viewPart(0, 0, 2, 9).toArray());
//                v = new DenseDoubleMatrix2D(v.viewDice().toArray());
//                v = new DenseDoubleMatrix2D(v.viewPart(0, 0, 9, 2).toArray());                
                s = new DenseDoubleMatrix2D(s.viewPart(0, 0, 2, 2).toArray());
                
                System.out.println("U: \n" + u.toString());
                System.out.println("S: \n" + s.toString());
                System.out.println("V: \n" + v.toString());                                
                                
                double[][] u1 = u.toArray();
                double[][] s1 = s.toArray();
                double[][] v1 = v.toArray();
                double[][] res = new double[u1.length][s1.length];
                for(int i = 0; i < u1.length; i++)
                {
                    for(int j = 0; j < u1[i].length; j++)
                    {
                        for(int k = 0; k < s1[j].length; k++)
                        {
                            res[i][j] += u1[i][k] * s1[j][k];
                        }
                    }
                }
                
                System.out.println("Res Length: " + res.length);
                
                System.out.println("V1 Length: " + v1.length);
                
                System.out.println("");
                
                double[][] res2 = new double[res.length][v1[0].length];
//                for(int i = 0; i < res.length; i++)
//                {
//                    for(int j = 0; j < res[i].length; j++)
//                    {
//                        for(int k = 0; k < v1[j].length; k++)
//                        {
//                            res2[i][k] += res[i][j] * v1[j][k];
//                        }
//                    }
//                }
                
                for(int i = 0; i < 12; i++)
                {
                    for(int j = 0; j < 9; j++)
                    {
                        for(int k = 0; k < 2; k++)
                        {
                            res2[i][j] += res[i][k] * v1[k][j]; 
                        }
                    }
                }
                
                Matrix res3 = new Matrix(res2);                  
                System.out.println("Final res: \n" +res3.toString());
                res3.trans();                
                Matrix res4 = new Matrix(res3.cos());
                System.out.println("Final res2: \n" +res4.toString());
                                
            }
            else
            {
                System.out.println("False");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception in main: " + ex.toString());
        }
    }
    
}
