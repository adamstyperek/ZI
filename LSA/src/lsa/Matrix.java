/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lsa;

import java.text.DecimalFormat;

/**
 *
 * @author adam
 */
public class Matrix {
    double[][] matrix;
    double[] avg;
    double[][] concepts;
    public Matrix() {
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
//        this.avg = new double[matrix.length];
    }
    
    public double[][] scalar()
    {
        double[][] scalar = new double[matrix.length][matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = i+1; j < matrix.length; j++)
            {
                for(int ij = 0; ij < matrix[i].length; ij++)
                {
                    scalar[i][j] += matrix[i][ij] * matrix[j][ij];
                    scalar[j][i] =  scalar[i][j];
                }
            }
        }
        return scalar;
    }
    
    public double[][] cos()
    {
        double[][] cos = new double[matrix.length][matrix.length];
        double[] pow = new double[matrix.length];        
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                pow[i] += matrix[i][j] * matrix[i][j];
            }
            pow[i] = Math.sqrt(pow[i]);                
        }
        
        double[][] sim = scalar();
        for(int i = 0; i < sim.length; i++)
        {
            for(int j = 0; j < sim[i].length; j++)
            {
                double tmp = (sim[i][j]/(pow[i]*pow[j]));                
                cos[i][j] = tmp;
            }            
        }
        return cos;
    }
    
    public void trans()
    {
        double[][] tmp = new double[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                tmp[j][i] = matrix[i][j];
            }
        }
        this.matrix = tmp;
    }
    
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(5);
        String result = "";
        for(double[] row : matrix)
        {
            for(double cell : row)
            {
                result += df.format(cell) + ", ";
//                result += cell + ", ";
            }
            result += "\n";
        }
        return result;
    }
    
    public void avg()
    {
        try
        {
            this.avg = new double[matrix.length];
            for(int i = 0; i < matrix.length; i++)
            {
                double tmp = 0;
                for(int j = 0; j < matrix[i].length; j++)
                {
                    tmp += matrix[i][j];
                }
                this.avg[i] = tmp / matrix[i].length;
                System.out.println(avg[i]);
            }
        }
        catch(Exception ex)
        {
            System.out.println("AVG Exception: " + ex.toString());
        }
    }
    
    public void divideByAvg()
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                matrix[i][j] = matrix[i][j] - avg[i];
            }
        }
    }
    
    public void deleteConcepts()
    {
        try
        {
            concepts = new double[matrix.length][2];
            for(int i = 0; i < matrix.length; i++)
            {
                for(int j = 0; j < 2; j++)
                {
                    concepts[i][j] = matrix[i][j];
                    System.out.print(concepts[i][j] + ", ");
                }
                System.out.println();
            }
        }
        catch(Exception ex)
        {
            System.out.println("deleteConceptsException: " + ex.toString());
        }
        
    }
    
    public void sigmaMult(double[][] sigmaMatrix)
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                matrix[i][j] = matrix[i][j] * Math.sqrt(sigmaMatrix[j][j]);
            }
        }
    }
    
    public void result(double[][] u, double[][] s)
    {
        Matrix matU = new Matrix(u);
                matU.deleteConcepts();
                matU = new Matrix(matU.concepts);
                matU.trans();
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; i < matrix[i].length; j++)
            {
                
            }
        }
    }
    
    
}
