package DATPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class analyticalBG extends JFrame{
	
	private static final long serialVersionUID = 523310274347479926L;
	
	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	private JTextArea dataTextArea;
    private String lines2;
    CategoryPlot plot;
	
	public analyticalBG(String xAxis, String yAxis, String graphTitle, String maxY, String minY, String formatComboValue, String zAxis) {
		super("Bar Graph for " + graphTitle);
		
		int zValue = Integer.parseInt(zAxis);
		if (zValue > 0)
		{
			dataset = new DefaultCategoryDataset();
			chart = ChartFactory.createBarChart3D(
					graphTitle,
					xAxis,
					yAxis,
					dataset,
					PlotOrientation.VERTICAL,
            		true,
            		true,
            		false
	        );

	        plot = chart.getCategoryPlot();
	        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
	        renderer.setMinimumBarLength(zValue);
		}
		else
		{	
			dataset = new DefaultCategoryDataset();
			chart = ChartFactory.createBarChart(
						graphTitle,
						xAxis,
						yAxis,
						dataset,
						PlotOrientation.VERTICAL,
                		true,
                		true,
                		false
					);
		}
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.setBackground(Color.WHITE);
        JPanel controlsPanel = new JPanel(new GridLayout(1, 1));
        controlsPanel.setBackground(Color.WHITE);
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));
        buttonsPanel.setBackground(Color.WHITE);
        JPanel subButtonsPanel = new JPanel(new GridLayout(1, 2));
        subButtonsPanel.setBackground(Color.WHITE);
        JPanel paddingPanel = new JPanel(new GridLayout(1, 2));
        paddingPanel.setBackground(Color.WHITE);
        paddingPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        dataTextArea = new JTextArea(10, 15);
        dataTextArea.setText("Delete me and add the category name HERE\nThen add the Name-Value pairs (Strictly separated by a comma (',') or a tab space ('	')) in every new line.");
        JScrollPane dataScrollPane = new JScrollPane(dataTextArea);
        JButton plotButton = new JButton("Plot Data");
        JButton clearButton = new JButton("Clear Data");
        JButton downloadButton = new JButton("Download Graph");

        if (formatComboValue.equals("INTEGER")) {
            int maxYValue = Integer.parseInt(maxY);
            int minYValue = Integer.parseInt(minY);
            plot = (CategoryPlot) chart.getPlot();
            ValueAxis Y = plot.getRangeAxis();
            Y.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            Y.setRange(minYValue, maxYValue); 
        } 
        else if (formatComboValue.equals("FLOAT"))
        {
            double maxYValue = Double.parseDouble(maxY);
            double minYValue = Double.parseDouble(minY);
            plot = (CategoryPlot) chart.getPlot();
            ValueAxis Y = plot.getRangeAxis();
            Y.setRange(minYValue, maxYValue);  
        }

        plotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dataset.clear();
            	
                if (formatComboValue.equals("INTEGER")) {
                    try {
                    	String text = dataTextArea.getText();
                        String[] lines = text.split("\n");
                        for (int i = 0; i < lines.length; i++) {
                            String[] parts = lines[i].split("[,\\t]");
                            if (parts.length == 2) {
                                String category = parts[0];
                                int value = Integer.parseInt(parts[1]);
                                if (value < 0) 
                                {
                                	JOptionPane.showMessageDialog(null, "Invalid input. Number should be positive.");
                                }
                                else
                                {
                                dataset.addValue(value, lines[0], category);
                                }
                            }
                        }
                    } 
                    catch (NumberFormatException ex) 
                    {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                    }
                }
                else if (formatComboValue.equals("FLOAT")) 
                {
                    try {
                    	String text = dataTextArea.getText();
                        String[] lines = text.split("\n");
                        for (int i = 0; i < lines.length; i++) {
                            String[] parts = lines[i].split("[,\\t]");
                            if (parts.length == 2) {
                                String category = parts[0];
                                double value = Double.parseDouble(parts[1]);
                                if (value < 0) 
                                {
                                	JOptionPane.showMessageDialog(null, "Invalid input. Number should be positive.");
                                }
                                else
                                {
                                dataset.addValue(value, lines[0], category);
                                }
                            }
                        }
                    } 
                    catch (NumberFormatException ex) 
                    {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                    }
                }
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new savePanel(chart);
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataTextArea.setText("Delete me and add the category name HERE\nThen add the Name-Value pairs (Strictly separated by a comma (',') or a tab space ('	')) in every new line.");
                dataset.clear();
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
