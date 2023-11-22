package DAT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainPanel extends JFrame {
	
	private Color customColor = new Color(51, 51, 51);
    Font customFont = new Font("Bahnschrift", Font.BOLD, 30);
    JPanel primaryPanel;
    JPanel mainPanel;
    JPanel sidebarPanel;
    JLabel about;
    JLabel team;
    JLabel scatterPlot;
    JLabel barChart;
    JLabel pieChart;
    
    private void updateAbout() {
        primaryPanel.removeAll();
        JTextArea descriptionTextArea = new JTextArea();
        JPanel p = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("About GraphCraft");
        title.setFont(new Font("Century Gothic", Font.BOLD, 30));
        titlePanel.add(title);
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(Color.WHITE);
        imageLabel.setIcon(new ImageIcon(new ImageIcon("DAT/GRAPHCRAFT.png").getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        descriptionTextArea.setText("GraftCraft is an innovative and user-friendly application that transforms the graph creation process. With a few simple clicks, users can effortlessly generate visually appealing and precise graphs that effectively represent their data. This powerful tool eliminates the need for complicated and time-consuming manual graph-making techniques, and the costly online internet alternatives by providing users with a streamlined and efficient solution. One standout feature of GraftCraft is its capability to analyze information and display them. Whether it be numerical data, statistical figures, or categorical information, this application seamlessly processes input and generates an accurate graph based on specifications as per user requirement. \n\nThe intuitive interface of GraftCraft simplifies the task of graph creation, from the process of choosing the graph to creating the graph, even for those with limited technological expertise, afterall it is the conclusions driven from the graph that is important, not the process itself. The minimalist design and straightforward navigation allow users to focus on their data rather than becoming overwhelmed by complex software functionalities. Although there currently are only three types of graphs that the user can choose from, there are still more to come.");
        descriptionTextArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        scrollPane.setBackground(Color.WHITE);
        imagePanel.add(imageLabel);
        p.add(titlePanel, BorderLayout.NORTH);
        p.add(imagePanel, BorderLayout.CENTER);
        p.add(scrollPane, BorderLayout.SOUTH);
        JScrollPane mainPane = new JScrollPane(p);
        mainPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPane.setBackground(Color.WHITE);
        primaryPanel.add(mainPane);
        primaryPanel.revalidate();
    }
    
    private void updateTeam() {
    	primaryPanel.removeAll();
        JPanel p = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel infoPanel = new JPanel(new GridLayout(3,1));
        titlePanel.setBackground(Color.WHITE);
        infoPanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("Meet The Developers");
        JLabel n1 = new JLabel("Aditya Chandrasekar - E22BCAU0047");
        JLabel n2 = new JLabel("Kanika Bhatt - E22BCAU0004");
        JLabel n3 = new JLabel("Vidit Kulshrestha - E22BCAU0034");
        title.setFont(new Font("Century Gothic", Font.BOLD, 30));
        n1.setFont(new Font("Century Gothic", Font.ITALIC, 30));
        n2.setFont(new Font("Century Gothic", Font.ITALIC, 30));
        n3.setFont(new Font("Century Gothic", Font.ITALIC, 30));
        titlePanel.add(title);
        infoPanel.add(n1);
        infoPanel.add(n2); 
        infoPanel.add(n3);
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBackground(Color.WHITE);
        p.add(titlePanel, BorderLayout.NORTH);
        p.add(scrollPane, BorderLayout.CENTER);
        JScrollPane mainPane = new JScrollPane(p);
        mainPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPane.setBackground(Color.WHITE);
        primaryPanel.add(mainPane);
        primaryPanel.revalidate();
    }
    
    private void PieChartInitializationPanel() {
    	primaryPanel.removeAll();
    	JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("Pie Chart Initialization Panel");
        title.setFont(new Font("Century Gothic", Font.BOLD, 30));
        titlePanel.add(title);
    	primaryPanel.removeAll();
        JLabel titleLabel = new JLabel("Graph Title:");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField graphTitleField = new JTextField(20);
        graphTitleField.setFont(new Font("Century Gothic", Font.ITALIC, 20));
        
        JLabel formatLabel1 = new JLabel("Format:");
        formatLabel1.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JComboBox < String > formatComboBox1 = new JComboBox < > (new String[] {
            "INTEGER",
            "FLOAT"
        });
        formatComboBox1.setBackground(Color.WHITE);
        formatComboBox1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel zAxisLabel = new JLabel("Z Axis Value:");
        zAxisLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField zAxisField = new JTextField(20);
        zAxisField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Century Gothic", Font.BOLD, 15));

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String graphTitle = graphTitleField.getText();
                String zAxisValue = zAxisField.getText();
                String formatComboValue = (String) formatComboBox1.getSelectedItem();
                try
                {
                	new pieChart(graphTitle, zAxisValue, formatComboValue);
                }
                catch (NumberFormatException ex)
                {
        			JOptionPane.showMessageDialog(null, "Parameters Empty. Please fill all of the given parameters.");
                }
                    
            }
         });

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBackground(Color.WHITE);
        JPanel enter = new JPanel(new GridLayout(1, 1));
        enter.setBackground(Color.WHITE);
        JPanel pack = new JPanel(new BorderLayout());
        pack.setBorder(new EmptyBorder(10, 30, 10, 30));
        pack.setBackground(Color.WHITE);
        panel.add(new JLabel("")); panel.add(new JLabel(""));
        panel.add(titleLabel);
        panel.add(graphTitleField);
        panel.add(zAxisLabel);
        panel.add(zAxisField);
        panel.add(formatLabel1);
        panel.add(formatComboBox1);
        enter.add(enterButton);
        pack.add(titlePanel, BorderLayout.NORTH);
        pack.add(panel, BorderLayout.CENTER);
        pack.add(enter, BorderLayout.SOUTH);
        primaryPanel.add(pack);
        primaryPanel.revalidate();
    }
    
    private void ScatterPlotInitializationPanel() {
        primaryPanel.removeAll();
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("Scatter Plot Initialization Panel");
        title.setFont(new Font("Century Gothic", Font.BOLD, 30));
        titlePanel.add(title);
        JLabel graphType = new JLabel("Type of Graph:");
        graphType.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JComboBox < String > graphSelection = new JComboBox < > (new String[] {
                "ANALYTICAL",
                "COMPARATIVE"
            });
        graphSelection.setBackground(Color.WHITE);
        graphSelection.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel xAxisLabel = new JLabel("X-Axis Name:");
        xAxisLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField xAxisNameField = new JTextField(20);
        xAxisNameField.setFont(new Font("Century Gothic", Font.ITALIC, 20));

        JLabel yAxisLabel = new JLabel("Y-Axis Name:");
        yAxisLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField yAxisNameField = new JTextField(20);
        yAxisNameField.setFont(new Font("Century Gothic", Font.ITALIC, 20));

        JLabel titleLabel = new JLabel("Graph Title:");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField graphTitleField = new JTextField(20);
        graphTitleField.setFont(new Font("Century Gothic", Font.ITALIC, 20));
        
        JLabel formatLabel1 = new JLabel("Format:");
        formatLabel1.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JComboBox < String > formatComboBox1 = new JComboBox < > (new String[] {
            "INTEGER",
            "FLOAT"
        });
        formatComboBox1.setBackground(Color.WHITE);
        formatComboBox1.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel minDomainLabel = new JLabel("Min Domain Value:");
        minDomainLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField maxDomainValueField = new JTextField(20);
        maxDomainValueField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel maxDomainLabel = new JLabel("Max Domain Value:");
        maxDomainLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField minDomainValueField = new JTextField(20);
        minDomainValueField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel minRangeLabel = new JLabel("Min Range Value:");
        minRangeLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField minRangeValueField = new JTextField(20);
        minRangeValueField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel maxRangeLabel = new JLabel("Max Range Value:");
        maxRangeLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField maxRangeValueField = new JTextField(20);
        maxRangeValueField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Century Gothic", Font.BOLD, 15));

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String xAxisName = xAxisNameField.getText();
                String yAxisName = yAxisNameField.getText();
                String graphTitle = graphTitleField.getText();
                String maxDomainValue = maxDomainValueField.getText();
                String maxRangeValue = maxRangeValueField.getText();
                String minDomainValue = minDomainValueField.getText();
                String minRangeValue = minRangeValueField.getText();
                String formatComboValue = (String) formatComboBox1.getSelectedItem();
                String graphTypeValue = (String) graphSelection.getSelectedItem();

                try
                {
                	if (formatComboValue.equals("INTEGER"))
                	{
                		if (graphTypeValue.equals("ANALYTICAL"))
                		{
                			int indexX = maxDomainValue.indexOf(".");
                			int indexY = maxRangeValue.indexOf(".");
                			if (indexX == -1 && indexY == -1)
                			{
                				new analyticalSP(xAxisName, yAxisName, graphTitle, maxDomainValue, maxRangeValue, minDomainValue, minRangeValue, formatComboValue);
                			}
                			else
                        	{
                        		JOptionPane.showMessageDialog(null, "Invalid Data Type. Please enter numbers according to the number type selected.");
                        	}
                		}
                		else
                		{
                			int indexX = maxDomainValue.indexOf(".");
                			int indexY = maxRangeValue.indexOf(".");
                			if (indexX == -1 && indexY == -1)
                			{
                				new comparativeSP(xAxisName, yAxisName, graphTitle, maxDomainValue, maxRangeValue, minDomainValue, minRangeValue, formatComboValue);
                			}
                			else
                        	{
                        		JOptionPane.showMessageDialog(null, "Invalid Data Type. Please enter numbers according to the number type selected.");
                        	}
                		}
                	}

                	if (formatComboValue.equals("FLOAT"))
                	{
                		if (graphTypeValue.equals("ANALYTICAL"))
                		{
            				new analyticalSP(xAxisName, yAxisName, graphTitle, maxDomainValue, maxRangeValue, minDomainValue, minRangeValue, formatComboValue);
                		}
                		else
                        {
            				new comparativeSP(xAxisName, yAxisName, graphTitle, maxDomainValue, maxRangeValue, minDomainValue, minRangeValue, formatComboValue);
                        }
                	}
                }
                catch (NumberFormatException ex)
                {
        			JOptionPane.showMessageDialog(null, "Parameters Empty. Please fill all of the given parameters.");
                }
                    
            }
         });

        JPanel panel = new JPanel(new GridLayout(11, 2));
        panel.setBackground(Color.WHITE);
        JPanel enter = new JPanel(new GridLayout(1, 1));
        enter.setBackground(Color.WHITE);
        JPanel pack = new JPanel(new BorderLayout());
        pack.setBorder(new EmptyBorder(10, 30, 10, 30));
        pack.setBackground(Color.WHITE);
        panel.add(new JLabel("")); panel.add(new JLabel(""));
        panel.add(graphType);
        panel.add(graphSelection);
        panel.add(xAxisLabel);
        panel.add(xAxisNameField);
        panel.add(yAxisLabel);
        panel.add(yAxisNameField);
        panel.add(titleLabel);
        panel.add(graphTitleField);
        panel.add(minDomainLabel);
        panel.add(minDomainValueField);
        panel.add(maxDomainLabel);
        panel.add(maxDomainValueField);
        panel.add(minRangeLabel);
        panel.add(minRangeValueField);
        panel.add(maxRangeLabel);
        panel.add(maxRangeValueField);
        panel.add(formatLabel1);
        panel.add(formatComboBox1);
        enter.add(enterButton);
        pack.add(titlePanel, BorderLayout.NORTH);
        pack.add(panel, BorderLayout.CENTER);
        pack.add(enter, BorderLayout.SOUTH);
        primaryPanel.add(pack);
        primaryPanel.revalidate();
    }
    
    private void BarGraphInitializationPanel() {
        primaryPanel.removeAll();
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("Bar Graph Initialization Panel");
        title.setFont(new Font("Century Gothic", Font.BOLD, 30));
        titlePanel.add(title);
        JLabel graphType = new JLabel("Type of Graph:");
        graphType.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JComboBox < String > graphSelection = new JComboBox < > (new String[] {
                "ANALYTICAL",
                "COMPARATIVE"
            });
        graphSelection.setBackground(Color.WHITE);
        graphSelection.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel xAxisLabel = new JLabel("X-Axis Name:");
        xAxisLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField xAxisNameField = new JTextField(20);
        xAxisNameField.setFont(new Font("Century Gothic", Font.ITALIC, 20));

        JLabel yAxisLabel = new JLabel("Y-Axis Name:");
        yAxisLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField yAxisNameField = new JTextField(20);
        yAxisNameField.setFont(new Font("Century Gothic", Font.ITALIC, 20));

        JLabel titleLabel = new JLabel("Graph Title:");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField graphTitleField = new JTextField(20);
        graphTitleField.setFont(new Font("Century Gothic", Font.ITALIC, 20));
        
        JLabel formatLabel1 = new JLabel("Format:");
        formatLabel1.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JComboBox < String > formatComboBox1 = new JComboBox < > (new String[] {
            "INTEGER",
            "FLOAT"
        });
        formatComboBox1.setBackground(Color.WHITE);
        formatComboBox1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel minRangeLabel = new JLabel("Min Range Value:");
        minRangeLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField minRangeValueField = new JTextField(20);
        minRangeValueField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JLabel maxRangeLabel = new JLabel("Max Range Value:");
        maxRangeLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField maxRangeValueField = new JTextField(20);
        maxRangeValueField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        
        JLabel zAxisLabel = new JLabel("Z Axis Value:");
        zAxisLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        JTextField zAxisField = new JTextField(20);
        zAxisField.setFont(new Font("Century Gothic", Font.PLAIN, 20));

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Century Gothic", Font.BOLD, 15));

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String xAxisName = xAxisNameField.getText();
                String yAxisName = yAxisNameField.getText();
                String graphTitle = graphTitleField.getText();
                String maxRangeValue = maxRangeValueField.getText();
                String minRangeValue = minRangeValueField.getText();
                String zAxisValue = zAxisField.getText();
                String formatComboValue = (String) formatComboBox1.getSelectedItem();
                String graphTypeValue = (String) graphSelection.getSelectedItem();
                int miny = Integer.parseInt(minRangeValue);

                try
                {
                	if (formatComboValue.equals("INTEGER"))
                	{
                		if (graphTypeValue.equals("ANALYTICAL"))
                		{
                			int indexY = maxRangeValue.indexOf(".");
                			if (indexY == -1 && miny >= 0)
                			{
                				new analyticalBG(xAxisName, yAxisName, graphTitle, maxRangeValue, minRangeValue, formatComboValue, zAxisValue);
                			}
                			else
                        	{
                        		JOptionPane.showMessageDialog(null, "Invalid Data Type. Please enter numbers according to the number type selected and/or ensure the minimum range is above 0.");
                        	}
                		}
                		else
                		{
                			int indexY = maxRangeValue.indexOf(".");
                			if (indexY == -1 && miny >= 0)
                			{
                				new comparativeBG(xAxisName, yAxisName, graphTitle, maxRangeValue, minRangeValue, formatComboValue, zAxisValue);
                			}
                			else
                        	{
                        		JOptionPane.showMessageDialog(null, "Invalid Data Type. Please enter numbers according to the number type selected and/or ensure the minimum range is above 0.");
                        	}
                		}
                	}

                	if (formatComboValue.equals("FLOAT"))
                	{
                		if (graphTypeValue.equals("ANALYTICAL"))
                		{
                			if (miny >= 0)
                			{
                				new analyticalBG(xAxisName, yAxisName, graphTitle, maxRangeValue, minRangeValue, formatComboValue, zAxisValue);
                			}
                			else
                			{
                        		JOptionPane.showMessageDialog(null, "Invalid Data Type. Please enter numbers according to the number type selected and/or ensure the minimum range is above 0.");
                			}
                		}
                		else
                        {
                			if (miny >= 0)
                			{
                				new comparativeBG(xAxisName, yAxisName, graphTitle, maxRangeValue, minRangeValue, formatComboValue, zAxisValue);
                			}
                			else
                			{
                        		JOptionPane.showMessageDialog(null, "Invalid Data Type. Please enter numbers according to the number type selected and/or ensure the minimum range is above 0.");
                			}
                        }
                	}
                }
                catch (NumberFormatException ex)
                {
        			JOptionPane.showMessageDialog(null, "Parameters Empty. Please fill all of the given parameters.");
                }
                    
            }
         });

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.setBackground(Color.WHITE);
        JPanel enter = new JPanel(new GridLayout(1, 1));
        enter.setBackground(Color.WHITE);
        JPanel pack = new JPanel(new BorderLayout());
        pack.setBorder(new EmptyBorder(10, 30, 10, 30));
        pack.setBackground(Color.WHITE);
        panel.add(new JLabel("")); panel.add(new JLabel(""));
        panel.add(graphType);
        panel.add(graphSelection);
        panel.add(xAxisLabel);
        panel.add(xAxisNameField);
        panel.add(yAxisLabel);
        panel.add(yAxisNameField);
        panel.add(titleLabel);
        panel.add(graphTitleField);
        panel.add(zAxisLabel);
        panel.add(zAxisField);
        panel.add(minRangeLabel);
        panel.add(minRangeValueField);
        panel.add(maxRangeLabel);
        panel.add(maxRangeValueField);
        panel.add(formatLabel1);
        panel.add(formatComboBox1);
        enter.add(enterButton);
        pack.add(titlePanel, BorderLayout.NORTH);
        pack.add(panel, BorderLayout.CENTER);
        pack.add(enter, BorderLayout.SOUTH);
        primaryPanel.add(pack);
        primaryPanel.revalidate();
    }

    public mainPanel() {
        super("GraphCraft");
        setSize(1440, 600);

        mainPanel = new JPanel(new BorderLayout());

        sidebarPanel = new JPanel(new GridLayout(5, 1, 30, 30));
        sidebarPanel.setBorder(new EmptyBorder(50, 50, 50, 30));
        sidebarPanel.setPreferredSize(new Dimension(300, 600));
        sidebarPanel.setBackground(customColor);
        about = new JLabel("About");
        about.setForeground(Color.WHITE);
        about.setFont(customFont);
        team = new JLabel("Team");
        team.setForeground(Color.WHITE);
        team.setFont(customFont);
        scatterPlot = new JLabel("Scatter Plot");
        scatterPlot.setForeground(Color.WHITE);
        scatterPlot.setFont(customFont);
        barChart = new JLabel("Bar Chart");
        barChart.setForeground(Color.WHITE);
        barChart.setFont(customFont);
        pieChart = new JLabel("Pie Chart");
        pieChart.setForeground(Color.WHITE);
        pieChart.setFont(customFont);
        
        about.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scatterPlot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        barChart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pieChart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        about.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	updateAbout();
            }
        });
        team.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	updateTeam();
            }
        });
        scatterPlot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	ScatterPlotInitializationPanel();
            }
        });
        barChart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	BarGraphInitializationPanel();
            }
        });
        pieChart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PieChartInitializationPanel();
            }
        });

        sidebarPanel.add(about);
        sidebarPanel.add(team);
        sidebarPanel.add(scatterPlot);
        sidebarPanel.add(barChart);
        sidebarPanel.add(pieChart);

        primaryPanel = new JPanel(new GridLayout(1, 1));
        primaryPanel.setPreferredSize(new Dimension(1440, 600));
        primaryPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        primaryPanel.setBackground(Color.white);
        mainPanel.add(primaryPanel, BorderLayout.CENTER);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        
        updateAbout();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new mainPanel();
        });
    }
}