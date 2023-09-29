package DATPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScatterPlotInitializationPanel extends JFrame {
	
    private static final long serialVersionUID = 2515770266173535595L;
    
	private JTextField xAxisNameField;
    private JTextField yAxisNameField;
    private JTextField graphTitleField;
    private JTextField maxDomainValueField;
    private JTextField maxRangeValueField;
    private JTextField minDomainValueField;
    private JTextField minRangeValueField;

    public ScatterPlotInitializationPanel(String title) {
        super(title);
        
        JLabel graphType = new JLabel("Type of Graph:");
        JComboBox < String > graphSelection = new JComboBox < > (new String[] {
                "Analytical",
                "Comparative"
            });
        
        JLabel xAxisLabel = new JLabel("X-Axis Name:");
        xAxisNameField = new JTextField(20);

        JLabel yAxisLabel = new JLabel("Y-Axis Name:");
        yAxisNameField = new JTextField(20);

        JLabel titleLabel = new JLabel("Graph Title:");
        graphTitleField = new JTextField(20);
        
        JLabel formatLabel1 = new JLabel("Format:");
        JComboBox < String > formatComboBox1 = new JComboBox < > (new String[] {
            "INTEGER",
            "FLOAT"
        });

        JLabel minDomainLabel = new JLabel("Min Domain Value:");
        maxDomainValueField = new JTextField(20);
        
        JLabel maxDomainLabel = new JLabel("Max Domain Value:");
        minDomainValueField = new JTextField(20);
        
        JLabel minRangeLabel = new JLabel("Min Range Value:");
        minRangeValueField = new JTextField(20);

        JLabel maxRangeLabel = new JLabel("Max Range Value:");
        maxRangeValueField = new JTextField(20);

        JButton enterButton = new JButton("Enter");

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
                		if (graphTypeValue.equals("Analytical"))
                		{
                			int indexX = maxDomainValue.indexOf(".");
                			int indexY = maxRangeValue.indexOf(".");
                			if (indexX == -1 && indexY == -1)
                			{
                				new analyticalSP(xAxisName, yAxisName, graphTitle, maxDomainValue, maxRangeValue, minDomainValue, minRangeValue, formatComboValue);
                				dispose();
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
                				dispose();
                			}
                			else
                        	{
                        		JOptionPane.showMessageDialog(null, "Invalid Data Type. Please enter numbers according to the number type selected.");
                        	}
                		}
                	}

                	if (formatComboValue.equals("FLOAT"))
                	{
                		if (graphTypeValue.equals("Analytical"))
                		{
            				new comparativeSP(xAxisName, yAxisName, graphTitle, maxDomainValue, maxRangeValue, minDomainValue, minRangeValue, formatComboValue);
                			dispose();
                		}
                		else
                        {
            				new comparativeSP(xAxisName, yAxisName, graphTitle, maxDomainValue, maxRangeValue, minDomainValue, minRangeValue, formatComboValue);
                			dispose();
                        }
                	}
                }
                catch (NumberFormatException ex)
                {
        			JOptionPane.showMessageDialog(null, "Parameters Empty. Please fill all of the given parameters.");
                }
                    
            }
         });

        JPanel panel = new JPanel(new GridLayout(9, 2));
        JPanel enter = new JPanel(new GridLayout(1, 1));
        JPanel pack = new JPanel(new BorderLayout());
        pack.setBorder(new EmptyBorder(10, 30, 10, 30));
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
        pack.add(panel, BorderLayout.CENTER);
        pack.add(enter, BorderLayout.SOUTH);
        add(pack);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ScatterPlotInitializationPanel panel = new ScatterPlotInitializationPanel("Scatter Plot Initialization Panel");
                panel.setVisible(true);
            }
        });
    }
}