package DAT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class statsPanel extends JFrame {
		
	private JTextField minField;
	private JTextField qOneField;
	private JTextField medianField;
	private JTextField qrThreeField;
	private JTextField maxValField;
	private JTextField rangeField;
	private JTextField interQuartileField;
	private JTextField upperBoundField;
	private JTextField lowerBoundField;
	private JTextArea resultTextArea;
	private JTextField rValField;
	private JTextField rSqrValField;
	private JTextField linearRegressionField;
	
    public statsPanel(String lines2, String lines3) {
        setTitle("Statistics");
        setSize(600, 600);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2, 0, 10));
        
        JLabel minVal = new JLabel("Minimum Value:");
        minField = new JTextField(20);
        minField.setEditable(false);
        minVal.setForeground(Color.BLACK);
        minField.setForeground(Color.BLACK);
        minField.setBackground(Color.WHITE);
        minVal.setFont(new Font("Century Gothic", Font.BOLD, 20));
        minField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel qrOne = new JLabel("First Quartile:");
        qOneField = new JTextField(20);
        qOneField.setEditable(false);
        qrOne.setForeground(Color.BLACK);
        qOneField.setForeground(Color.BLACK);
        qOneField.setBackground(Color.WHITE);
        qrOne.setFont(new Font("Century Gothic", Font.BOLD, 20));
        qOneField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel med = new JLabel("Median:");
        medianField = new JTextField(20);
        medianField.setEditable(false);
        med.setForeground(Color.BLACK);
        medianField.setForeground(Color.BLACK);
        medianField.setBackground(Color.WHITE);
        med.setFont(new Font("Century Gothic", Font.BOLD, 20));
        medianField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel qrThree = new JLabel("Third Quartile:");
        qrThreeField = new JTextField(20);
        qrThreeField.setEditable(false);
        qrThree.setForeground(Color.BLACK);
        qrThreeField.setForeground(Color.BLACK);
        qrThreeField.setBackground(Color.WHITE);
        qrThree.setFont(new Font("Century Gothic", Font.BOLD, 20));
        qrThreeField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel maxVal = new JLabel("Maximum Value:");
        maxValField = new JTextField(20);
        maxValField.setEditable(false);
        maxVal.setForeground(Color.BLACK);
        maxValField.setForeground(Color.BLACK);
        maxValField.setBackground(Color.WHITE);
        maxVal.setFont(new Font("Century Gothic", Font.BOLD, 20));
        maxValField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel Range = new JLabel("Range:");
        rangeField = new JTextField(20);
        rangeField.setEditable(false);
        Range.setForeground(Color.BLACK);
        rangeField.setForeground(Color.BLACK);
        rangeField.setBackground(Color.WHITE);
        Range.setFont(new Font("Century Gothic", Font.BOLD, 20));
        rangeField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel interQuarterRange = new JLabel("Inter Quartile Range:");
        interQuartileField = new JTextField(20);
        interQuartileField.setEditable(false);
        interQuarterRange.setForeground(Color.BLACK);
        interQuartileField.setForeground(Color.BLACK);
        interQuartileField.setBackground(Color.WHITE);
        interQuarterRange.setFont(new Font("Century Gothic", Font.BOLD, 20));
        interQuartileField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel upperBounds = new JLabel("Upper Boundary:");
        upperBoundField = new JTextField(20);
        upperBoundField.setEditable(false);
        upperBounds.setForeground(Color.BLACK);
        upperBoundField.setForeground(Color.BLACK);
        upperBoundField.setBackground(Color.WHITE);
        upperBounds.setFont(new Font("Century Gothic", Font.BOLD, 20));
        upperBoundField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel lowerBounds = new JLabel("Lower Boundary:");
        lowerBoundField = new JTextField(20);
        lowerBoundField.setEditable(false);
        lowerBounds.setForeground(Color.BLACK);
        lowerBoundField.setForeground(Color.BLACK);
        lowerBoundField.setBackground(Color.WHITE);
        lowerBounds.setFont(new Font("Century Gothic", Font.BOLD, 20));
        lowerBoundField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel rValue = new JLabel("R:");
        rValField = new JTextField(20);
        rValField.setEditable(false);
        rValue.setForeground(Color.BLACK);
        rValField.setForeground(Color.BLACK);
        rValField.setBackground(Color.WHITE);
        rValue.setFont(new Font("Century Gothic", Font.BOLD, 20));
        rValField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel rSqrVal = new JLabel("R^2:");
        rSqrValField = new JTextField(20);
        rSqrValField.setEditable(false);
        rSqrVal.setForeground(Color.BLACK);
        rSqrValField.setForeground(Color.BLACK);
        rSqrValField.setBackground(Color.WHITE);
        rSqrVal.setFont(new Font("Century Gothic", Font.BOLD, 20));
        rSqrValField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel linearRegression = new JLabel("Linear Regression:");
        linearRegressionField = new JTextField(20);
        linearRegressionField.setEditable(false);
        linearRegression.setForeground(Color.BLACK);
        linearRegressionField.setForeground(Color.BLACK);
        linearRegressionField.setBackground(Color.WHITE);
        linearRegression.setFont(new Font("Century Gothic", Font.BOLD, 20));
        linearRegressionField.setFont(new Font("Century Gothic", Font.PLAIN, 20));


        String[] d = lines2.split(",");
        double[] data = new double[d.length];

        for (int i = 0; i < d.length; i++) {
            try {
                data[i] = Double.parseDouble(d[i]);
            } catch (NumberFormatException e) {
                resultTextArea.setText("Invalid input. Please enter comma-separated numeric values.");
                return;
            }
        }
        
        String[] d2 = lines3.split(",");
        double[] data2 = new double[d2.length];

        for (int i = 0; i < d2.length; i++) {
            try {
                data2[i] = Double.parseDouble(d2[i]);
            } catch (NumberFormatException e) {
                resultTextArea.setText("Invalid input. Please enter comma-separated numeric values.");
                return;
            }
        }

        Arrays.sort(data);
        Arrays.sort(data2);

        minField.setText(Double.toString(data[0]));
        maxValField.setText(Double.toString(data[data.length - 1]));
        rangeField.setText(Double.toString(data[data.length - 1] - data[0]));
        medianField.setText(Double.toString(calculateMedian(data)));
        qOneField.setText(Double.toString(calculateQuartile(data, 0.25)));
        qrThreeField.setText(Double.toString(calculateQuartile(data, 0.75)));
        interQuartileField.setText(Double.toString(calculateQuartile(data, 0.75) - calculateQuartile(data, 0.25)));
        upperBoundField.setText(Double.toString((calculateQuartile(data, 0.75) + 1.5 * (calculateQuartile(data, 0.75) - calculateQuartile(data, 0.25)))));
        lowerBoundField.setText(Double.toString((calculateQuartile(data, 0.25) - 1.5 * (calculateQuartile(data, 0.75) - calculateQuartile(data, 0.25)))));
        rValField.setText(Double.toString(r(data2, data)));
        rSqrValField.setText(Double.toString(rSqrt(data2, data)));
        linearRegressionField.setText((Double.toString(slope(data2, data)) + "x + " + (Double.toString(intercept(data2, data)))));
                
        panel.add(minVal);
        panel.add(minField);
        panel.add(qrOne);
        panel.add(qOneField);
        panel.add(med);
        panel.add(medianField);
        panel.add(qrThree);
        panel.add(qrThreeField);
        panel.add(maxVal);
        panel.add(maxValField);
        panel.add(Range);
        panel.add(rangeField);
        panel.add(interQuarterRange);
        panel.add(interQuartileField);
        panel.add(upperBounds);
        panel.add(upperBoundField);
        panel.add(lowerBounds);
        panel.add(lowerBoundField);
        panel.add(rValue);
        panel.add(rValField);
        panel.add(rSqrVal);
        panel.add(rSqrValField);
        panel.add(linearRegression);
        panel.add(linearRegressionField);
        
        panel.setBorder(new EmptyBorder(10, 30, 10, 30));
        panel.setBackground(Color.WHITE);
        
        add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private double calculateMedian(double[] data) {
        int n = data.length;
        int middleIndex1 = (n - 1) / 2;
        int middleIndex2 = n / 2;

        if (n % 2 == 0) {
            return (data[middleIndex1] + data[middleIndex2]) / 2.0;
        } else {
            return data[middleIndex2];
        }
    }

    private double calculateQuartile(double[] data, double percentile) {
        int n = data.length;
        double position = (n - 1) * percentile + 1;
        int k = (int) Math.floor(position);
        double d = position - k;

        if (k < 1) {
            return data[0];
        } else if (k >= n) {
            return data[n - 1];
        } else {
            return data[k - 1] + d * (data[k] - data[k - 1]);
        }
    }
    
    public double slope(double[] xValues, double[] yValues) {
        int n = xValues.length;

        double sumX = 0.0;
        double sumY = 0.0;
        double sumX2 = 0.0;
        double sumXY = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += xValues[i];
            sumY += yValues[i];
            sumX2 += xValues[i] * xValues[i];
            sumXY += xValues[i] * yValues[i];
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        return slope;
    }
    
    public double intercept(double[] xValues, double[] yValues) {
        int n = xValues.length;

        double sumX = 0.0;
        double sumY = 0.0;
        double sumX2 = 0.0;
        double sumXY = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += xValues[i];
            sumY += yValues[i];
            sumX2 += xValues[i] * xValues[i];
            sumXY += xValues[i] * yValues[i];
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);

        double intercept = (sumY - slope * sumX) / n;

        return intercept;
    }
    
    public double r(double[] xValues, double[] yValues) {
        int n = xValues.length;

        double sumX = 0.0;
        double sumY = 0.0;
        double sumX2 = 0.0;
        double sumY2 = 0.0;
        double sumXY = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += xValues[i];
            sumY += yValues[i];
            sumX2 += xValues[i] * xValues[i];
            sumY2 += yValues[i] * yValues[i];
            sumXY += xValues[i] * yValues[i];
        }

        double r = (n * sumXY - sumX * sumY) / Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));
        return r;
    }
    
    public double rSqrt(double[] xValues, double[] yValues) {
        int n = xValues.length;

        double sumX = 0.0;
        double sumY = 0.0;
        double sumX2 = 0.0;
        double sumY2 = 0.0;
        double sumXY = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += xValues[i];
            sumY += yValues[i];
            sumX2 += xValues[i] * xValues[i];
            sumY2 += yValues[i] * yValues[i];
            sumXY += xValues[i] * yValues[i];
        }

        double r = (n * sumXY - sumX * sumY) / Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));

        double rSquared = r * r;
        
        return rSquared;
    }
}