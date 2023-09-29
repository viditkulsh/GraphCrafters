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

public class analyticalSP extends JFrame {
	
    private static final long serialVersionUID = -8084587743696862674L;
    
	private XYSeries dataSeries;
    private XYSeriesCollection dataset;
    private JTextArea dataTextArea;
    private String lines2;
    private String lines3;
    private boolean showShapes = true;

    public analyticalSP(String xAxis, String yAxis, String graphTitle, String maxX, String maxY, String minX, String minY, String formatComboValue) {
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
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));
        JPanel subButtonsPanel = new JPanel(new GridLayout(1, 3));
        JPanel paddingPanel = new JPanel(new GridLayout(1, 2));
        paddingPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        dataTextArea = new JTextArea(10, 15);
        dataTextArea.setText("Delete me and add the category name in the first line and then"
        		+ " the X and Y values in a new line, separated by a tab ('	') or a comma (',')");
        JScrollPane dataScrollPane = new JScrollPane(dataTextArea);
        JButton plotButton = new JButton("Plot Data");
        JButton clearButton = new JButton("Clear Data");
        JButton lineButton = new JButton("Toggle Line View");
        JButton viewButton = new JButton("View Analysis");
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
                    try {
                    	lines2 = "";
                    	lines3 = "";
                    	int i = 0;
                        String data = dataTextArea.getText();
                        String[] lines = data.split("\n");
                        dataSeries = new XYSeries(lines[0]);
                        dataset.addSeries(dataSeries);
                            
                        for (int j = 1; i < lines.length-1; j++) {
                            String[] values = lines[j].split("[,\t]");
                            if (values.length >= 2) {
                                int x = Integer.parseInt(values[0]);
                                int y = Integer.parseInt(values[1]);
                                dataSeries.add(x, y);
                                if (i < lines.length)
                                {
                                	lines2 += values[1] + ",";
                                	lines3 += values[0] + ",";
                                }
                                else
                                {
                                	lines2 += values[1];
                                	lines3 += values[0];
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Incorrect format. Please input both the X and Y "
                                		+ "values in every new line, separated by a tab or a comma.");
                                lines2 = "";
                                lines3 = "";
                                break;
                            }
                            i++;
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
                    	lines2 = "";
                    	lines3 = "";
                    	int i = 0;
                        String data = dataTextArea.getText();
                        String[] lines = data.split("\n");
                        dataSeries = new XYSeries(lines[0]);
                        dataset.addSeries(dataSeries);
                            
                        for (int j = 1; i < lines.length-1; j++) {
                            String[] values = lines[j].split("[,\t]");
                            if (values.length >= 2) {
                                double x = Double.parseDouble(values[0]);
                                double y = Double.parseDouble(values[1]);
                                dataSeries.add(x, y);
                                if (i < lines.length)
                                {
                                	lines2 += values[1] + ",";
                                	lines3 += values[0] + ",";
                                }
                                else
                                {
                                	lines2 += values[1];
                                	lines3 += values[0];
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Incorrect format. Please input both the X and Y "
                                		+ "values in every new line, separated by a tab or a comma.");
                                lines2 = "";
                                lines3 = "";
                                break;
                            }
                            i++;
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
            	dataTextArea.setText("Delete me and add the category name in the first line and then"
                		+ " the X and Y values in a new line, separated by a tab ('	') or a comma (',')");
                dataSeries.clear();
                dataset.removeAllSeries();
                lines2 = "";
                lines3 = "";
                		
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
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (lines2 != "" & lines3 != "")
            	{
            	new statsPanel(lines2, lines3);
            	}
            	else
            	{
                    JOptionPane.showMessageDialog(null, "Empty Dataset. Please enter valid X and Y data to compute.");
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
        buttonsPanel.add(viewButton);
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