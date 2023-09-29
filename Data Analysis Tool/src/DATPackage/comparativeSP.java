package DATPackage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class comparativeSP extends JFrame {
	
	private static final long serialVersionUID = -7884563429485630467L;
	
	private XYSeries dataSeries;
    private XYSeriesCollection dataset;
    private JTextArea dataTextArea;
    private boolean showShapes = true;

    public comparativeSP(String xAxis, String yAxis, String graphTitle, String maxX, String maxY, String minX, String minY, String formatComboValue) {
        super("Scatter Plot for " + graphTitle);
          
        dataset = new XYSeriesCollection();        
        JFreeChart chart = ChartFactory.createScatterPlot(
            graphTitle,
            xAxis,
            yAxis,
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer shapeRenderer = new XYLineAndShapeRenderer(false, true);
        XYItemRenderer lineRenderer = new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(shapeRenderer);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        JPanel controlsPanel = new JPanel(new GridLayout(1, 1));
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
        JPanel subButtonsPanel = new JPanel(new GridLayout(1, 3));
        JPanel paddingPanel = new JPanel(new GridLayout(1, 2));
        paddingPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        dataTextArea = new JTextArea(10, 15);
        dataTextArea.setText("FORMAT: 'Example 1,2' to plot coordinates 1,2 for category named Example");
        JScrollPane dataScrollPane = new JScrollPane(dataTextArea);
        JButton plotButton = new JButton("Plot Data");
        JButton clearButton = new JButton("Clear Data");
        JButton lineButton = new JButton("Toggle Line View");
        JButton downloadButton = new JButton("Download Graph");

        if (formatComboValue.equals("INTEGER")) {
        	int maxXValue = Integer.parseInt(maxX);
            int maxYValue = Integer.parseInt(maxY);
            int minXValue = Integer.parseInt(minX);
            int minYValue = Integer.parseInt(minY);
            NumberAxis X = (NumberAxis) plot.getDomainAxis();
            NumberAxis Y = (NumberAxis) plot.getRangeAxis();
            plot.getDomainAxis().setRange(minXValue, maxXValue);
            plot.getRangeAxis().setRange(minYValue, maxYValue);
            X.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            Y.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            X.setVerticalTickLabels(true);
        } 
        else if (formatComboValue.equals("FLOAT"))
        {
            double maxXValue = Double.parseDouble(maxX);
            double maxYValue = Double.parseDouble(maxY);
            double minXValue = Double.parseDouble(minX);
            double minYValue = Double.parseDouble(minY);
            plot.getDomainAxis().setRange(minXValue, maxXValue);
            plot.getRangeAxis().setRange(minYValue, maxYValue);  
            NumberAxis X = (NumberAxis) plot.getDomainAxis();
            X.setVerticalTickLabels(true);
        }

        plotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dataset.removeAllSeries();
            	if (dataSeries != null)
            	{
            		dataSeries.clear();
            	}
                if (formatComboValue.equals("INTEGER")) {
                	String input = dataTextArea.getText();
                    String[] lines = input.split("\n");
                    dataset.removeAllSeries();

                    for (String line : lines) {
                        String[] parts = line.split(" ");
                        if (parts.length >= 2) {
                            String category = parts[0];
                            XYSeries series = new XYSeries(category);

                            for (int i = 1; i < parts.length; i++) {
                                String[] coordinates = parts[i].split(",");
                                if (coordinates.length == 2) {
                                    try {
                                        int x = Integer.parseInt(coordinates[0]);
                                        int y = Integer.parseInt(coordinates[1]);
                                        series.add(x, y);
                                    } catch (NumberFormatException ex) {
                                    	JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                                    }
                                }
                            }

                            dataset.addSeries(series);
                        }
                    }
                }
                else if (formatComboValue.equals("FLOAT")) 
                {
                	String input = dataTextArea.getText();
                    String[] lines = input.split("\n");
                    dataset.removeAllSeries();

                    for (String line : lines) {
                        String[] parts = line.split(" ");
                        if (parts.length >= 2) {
                            String category = parts[0];
                            XYSeries series = new XYSeries(category);

                            for (int i = 1; i < parts.length; i++) {
                                String[] coordinates = parts[i].split(",");
                                if (coordinates.length == 2) {
                                    try {
                                        double x = Double.parseDouble(coordinates[0]);
                                        double y = Double.parseDouble(coordinates[1]);
                                        series.add(x, y);
                                    } catch (NumberFormatException ex) {
                                    	JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                                    }
                                }
                            }

                            dataset.addSeries(series);
                        }
                    }
                }
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePanel p = new savePanel(chart);
                p.setVisible(true);
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dataTextArea.setText("FORMAT: 'Example 1,2' to plot coordinates 1,2 for category named Example");
                dataset.removeAllSeries();
                plot.setRenderer(shapeRenderer);
            }
        });
        
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showShapes = !showShapes;
                if (showShapes) 
                {
                    plot.setRenderer(lineRenderer);
                } 
                else 
                {
                	plot.setRenderer(shapeRenderer);;
                }
            }
        });
        
        ChartPanel chartPanel1 = new ChartPanel(chart);

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
        subButtonsPanel.add(lineButton);
        buttonsPanel.add(subButtonsPanel);
        buttonsPanel.add(downloadButton);

        inputPanel.add(controlsPanel, BorderLayout.NORTH);
        inputPanel.add(buttonsPanel, BorderLayout.SOUTH);
        paddingPanel.add(chartPanel1, BorderLayout.CENTER);
        paddingPanel.add(inputPanel, BorderLayout.SOUTH);
        add (paddingPanel);
        setSize(1420, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}