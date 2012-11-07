/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lsa;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.SingularValueDecomposition;

/**
 *
 * @author adam
 */
public class SVD {
    private double[][] matrix;
    
//    SVD Objects
    
    private DenseDoubleMatrix2D mat;
    private SingularValueDecomposition svd;
    private DenseDoubleMatrix2D u;
    private DenseDoubleMatrix2D v;
    private DenseDoubleMatrix2D s;

    public SVD() {
    }

    public SVD(double[][] matrix) {
        
//        for(double[] tab : matrix)
//            {
//                for(double i : tab)
//                {
//                    System.out.print(i + " ");
//                }
//                System.out.println();
//            }
        
        double[][] tmp = new double[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
//                System.out.print(matrix[i][j] + " ");
                tmp[j][i] = matrix[i][j];
            }
//            System.out.println();
        }
        this.matrix = tmp;
        
    }
    
    public boolean setSVD()
    {
        try
        {
//            for(double[] tab : matrix)
//            {
//                for(double i : tab)
//                {
//                    System.out.print(i + " ");
//                }
//                System.out.println();
//            }
            this.mat = new DenseDoubleMatrix2D(matrix);
            
            System.out.println(mat.toString());
            
            svd = new SingularValueDecomposition(mat);            
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("Exception in setSVD: " + ex.toString());
            return false;
        }
    }
    
    public double[][] getU()
    {        
        return svd.getU().toArray();
    }
    
    public double[][] getV()
    {
        return svd.getV().toArray();
    }
    
    public double[][] getS()
    {
        
        return svd.getS().toArray();
    }
    
//    public double[][] getRank()
//    {
//        return 
//    }
    
    
}
