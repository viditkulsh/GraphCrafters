package DAT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pieChart extends JFrame {
		
	DefaultPieDataset dataset;
	private JFreeChart chart;
	private JTextArea dataTextArea;	
	
	public void generatePiePlot(double zValue, String graphTitle) {
	    if (zValue > 0) 
	    {
	        dataset = new DefaultPieDataset();
	        chart = ChartFactory.createPieChart3D(
	                graphTitle,
	                dataset,
	                true,
	                true,
	                false
	        );
	        PiePlot3D plot = (PiePlot3D) chart.getPlot();

	        plot.setDepthFactor(zValue / 100);
	        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2}%)"));
	    } 
	    
	    else 
	    {
	        dataset = new DefaultPieDataset();
	        chart = ChartFactory.createPieChart(
	                graphTitle,
	                dataset,
	                true,
	                true,
	                false
	        );

	        PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2}%)"));
	    }
	}
	
	public pieChart(String graphTitle, String zAxis, String formatComboValue) {
		double zValue = Double.parseDouble(zAxis);
		
		String title = graphTitle;
		String axis = zAxis;
		String formatCombo = formatComboValue;
		
		generatePiePlot(zValue, graphTitle);
		
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.setBackground(Color.WHITE);
        JPanel controlsPanel = new JPanel(new GridLayout(1, 1));
        controlsPanel.setBackground(Color.WHITE);
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
        buttonsPanel.setBackground(Color.WHITE);
        JPanel subButtonsPanel = new JPanel(new GridLayout(1, 2));
        subButtonsPanel.setBackground(Color.WHITE);
        JPanel paddingPanel = new JPanel(new GridLayout(1, 2));
        paddingPanel.setBackground(Color.WHITE);
        paddingPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        dataTextArea = new JTextArea(10, 15);
        dataTextArea.setText("Delete me and add the Name-Value pairs (Strictly separated by a comma (',') or a tab space ('	')) in every new line.");
        JScrollPane dataScrollPane = new JScrollPane(dataTextArea);
        JButton plotButton = new JButton("Plot Data");
        JButton clearButton = new JButton("Clear Data");
        JButton downloadButton = new JButton("Download Graph");
        
        plotButton.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	            	try {
		                if (formatComboValue.equals("INTEGER")) {
		                    try {
		                    	String text = dataTextArea.getText();
		                        String[] lines = text.split("\n");
		                        for (String line:lines) {
		                        	String[] part = line.split(",");
		                        	String Category = part[0];
		                        	int value = Integer.parseInt(part[1]);
		                        	dataset.setValue(Category, value);
		                        }
		                    } 
		                    catch (NumberFormatException ex)
		                    {
		                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter Integers.");
		                    }
		                }
		                else if (formatComboValue.equals("FLOAT")) 
		                {
		                	try {
		                    	String text = dataTextArea.getText();
		                        String[] lines = text.split("\n");
		                        for (String line:lines) {
		                        	String[] part = line.split(",");
		                        	String Category = part[0];
		                        	double value = Double.parseDouble(part[1]);
		                        	dataset.setValue(Category, value);
		                        }
		                    } 
		                    catch (NumberFormatException ex) 
		                    {
		                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter Float.");
		                    }
		                }
	            	}
		            catch (ArrayIndexOutOfBoundsException ex)
	            	{
		            	JOptionPane.showMessageDialog(null, "Invalid input. Please enter values as specified.");
		            }
	            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new pieChart(title, axis, formatCombo);
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new savePanel(chart);
            }
        });
        ChartPanel chartPanel1 = new ChartPanel(chart);
        chartPanel1.setBackground(Color.WHITE);

        chartPanel1.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {
            }
        });

        chartPanel1.setDomainZoomable(false);
        chartPanel1.setRangeZoomable(false);
        controlsPanel.add(dataScrollPane, BorderLayout.CENTER);
        subButtonsPanel.add(plotButton);
        subButtonsPanel.add(clearButton);
        buttonsPanel.add(subButtonsPanel);
        buttonsPanel.add(downloadButton);

        inputPanel.add(controlsPanel, BorderLayout.NORTH);
        inputPanel.add(buttonsPanel, BorderLayout.SOUTH);
        paddingPanel.add(chartPanel1, BorderLayout.CENTER);
        paddingPanel.add(inputPanel, BorderLayout.SOUTH);
        add (paddingPanel);
        setSize(1420, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
