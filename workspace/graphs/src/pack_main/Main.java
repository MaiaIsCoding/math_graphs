package pack_main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class Main
{
	 public static final String PATTERN2 = "#,##0.000;-#,##0.000";
	static String xStrPath;
	static String [][] myArray;
	static String[] InArray;
	static String [][] myTable;
	
	
	public static JPanel setUpMyTable()
	{  
		myTable = new String [18] [7];
		Scanner scanIn = null;
		int count = 1;
		int Rowc = 0;
		double fxk = 0;
		double fac = 0;
		double fac_fxk = 0;
		double medx = 0;
		DecimalFormat df2 = new DecimalFormat("#.000", DecimalFormatSymbols.getInstance(Locale.US));
		df2.applyPattern(PATTERN2);
		String InputLine = "";
		String yfileLocation;
		yfileLocation = "C:\\Users\\Lucas\\Downloads\\workspace\\graphs\\Pasta1.csv";
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(0, 7));
		JLabel clsLabel;
		try
		{
			scanIn = new Scanner(new BufferedReader(new FileReader(yfileLocation)));
			
			while (Rowc < 17)
			{
				InputLine = scanIn.nextLine();
				String[] InArray = InputLine.split(";");
				
				for (int y = 0; y < InArray.length; y++)
				{
					myTable[Rowc][y] = InArray[y];
					if(Rowc < 8)
					{
						if((Rowc > 0) && (y == 3))
						{
								fxk = Double.parseDouble(myTable[Rowc][1]) * Double.parseDouble(myTable[Rowc][2]);
								String ffxk = df2.format(fxk);
								myTable [Rowc][y] = ffxk;
								fac_fxk += Double.parseDouble(myTable[Rowc][3]);
						}
						if((Rowc > 0) && (y == 4))
						{
								fac += Double.parseDouble(myTable[Rowc][1]);
								myTable [Rowc][y]= Double.toString(fac);
						}
						}
						if((Rowc >= 10) && (y == 0))
						{
							medx = fac_fxk / fac;
							String fss = df2.format(Double.parseDouble(myTable[count][2]) - medx);
							myTable [Rowc][y] = fss;
							count++;
						}
						
						if((Rowc >= 10) && (y == 1))
						{
							double ss2 = Math.pow(Double.parseDouble(myTable[Rowc][0]), 2);
							String fs2 = df2.format(ss2);
							myTable [Rowc][y] = fs2;
						}
						
						if((Rowc == 10) && (y == 2))
						{
							myTable [Rowc][y] = myTable[1][0];
						}
						
						if((Rowc == 10) && (y == 3))
						{
							String fmedx = df2.format(medx);
							myTable [Rowc][y] = fmedx;
						}
						
						if((Rowc == 10) && (y == 4))
						{
							double mediana = (0 + (45 - 0)*6.29)/67;
							String fmediana = df2.format(mediana);
							myTable [Rowc][y] = fmediana;
						}
						
						if((Rowc == 10) && (y == 5))
						{
							double nss2 = (2673.615)/90-1;
							String fnss2 = df2.format(nss2);
							myTable [Rowc][y] = fnss2;
						}
						
						if((Rowc == 10) && (y == 6))
						{
							double desp = Math.sqrt(Double.parseDouble(myTable[10][5]));
							String fdesp = df2.format(desp);
							myTable [Rowc][y] = fdesp;
						}
					
						clsLabel = new JLabel();
						clsLabel.setText(myTable[Rowc][y]);
						panel3.add(clsLabel);
				}
				Rowc++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return panel3;
	}
	
	public static JPanel setUpMyCSVArray()
	{
		myArray = new String [32] [8];
		Scanner scanIn = null;
		int Rowc = 0;
		String InputLine = "";
		String xfileLocation;
		xfileLocation = "C:\\Users\\Lucas\\Downloads\\workspace\\graphs\\JanMar18.csv";
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 8));
		JLabel clsLabel;
		try
		{
			scanIn = new Scanner(new BufferedReader(new FileReader(xfileLocation)));
			while (scanIn.hasNextLine())
			{
				InputLine = scanIn.nextLine();
				String[] InArray = InputLine.split(";");
				for (int x = 0; x < InArray.length; x++)
				{
					myArray[Rowc] [x] = InArray[x];
					clsLabel = new JLabel();
					clsLabel.setText(InArray[x]);
					panel2.add(clsLabel);
				}
				Rowc++;
				System.out.println("");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return panel2;
	}
	public static JPanel makeBarChart()
	{
		JPanel panelBarChart = new JPanel();
		DefaultCategoryDataset  barChartData = new DefaultCategoryDataset();
		final String bar1 = "Precipitação acumulada em mililitros";
		final String bar2 = "Variância";
		String amp1 = "0-06.29";
		String amp2 = "6.29-12.58";
		String amp3 = "12.58-18.87";
		String amp4 = "18.87-25.16";
		String amp5 = "25.16-31.45";
		String amp6 = "31.45-37.74";
		String amp7 = "37.74-44.0";
		
		barChartData.addValue( 67.0, bar1, amp1);
		barChartData.addValue( 06.0, bar1, amp2);   
		barChartData.addValue( 09.0, bar1, amp3);   
		barChartData.addValue( 03.0, bar1, amp4);   
		barChartData.addValue( 03.0, bar1, amp5);   
		barChartData.addValue( 01.0, bar1, amp6);   
		barChartData.addValue( 01.0, bar1, amp7);
		
		barChartData.addValue( -3.914, bar2, amp1);
		barChartData.addValue( 2.376, bar2, amp2);   
		barChartData.addValue( 8.666, bar2, amp3);   
		barChartData.addValue( 14.956, bar2, amp4);   
		barChartData.addValue( 21.246, bar2, amp5);   
		barChartData.addValue( 27.536, bar2, amp6);   
		barChartData.addValue( 33.926, bar2, amp7);
		
		JFreeChart barChart = ChartFactory.createBarChart3D(
				"Dados da Represa Jaguari/Jacareí de Janeiro-Março 2018",
				"Amplitude em mílilitros", 
				"Dias com valores em respectivas amplitudes", 
				barChartData, 
				PlotOrientation.VERTICAL, 
				true, true, false);
		
		barChart.setBorderPaint(Color.RED);
		barChart.setBackgroundPaint(Color.WHITE);
		barChart.getTitle().setPaint(Color.BLACK);
		CategoryPlot barchrt = barChart.getCategoryPlot();
		barchrt.setRangeGridlinePaint(Color.WHITE);	
		ChartPanel barPanel = new ChartPanel(barChart);
		barPanel.setPreferredSize(new Dimension(800, 500));
		
		panelBarChart.setVisible(true);
		panelBarChart.removeAll();
		panelBarChart.add(barPanel, BorderLayout.CENTER);
		panelBarChart.validate();
		return panelBarChart;
	}
	
	public static JPanel makeLineChart()
	{
		JPanel panelLineChart = new JPanel();
		DefaultCategoryDataset  lineChartData = new DefaultCategoryDataset();
		final String line1 = "Precipitação acumulada em mililitros";
		final String line2 = "Variância";
		
		lineChartData.addValue( 67.0, line1, "0-06.29");
		lineChartData.addValue( 06.0, line1, "6.29-12.58");   
		lineChartData.addValue( 09.0, line1, "12.58-18.87");   
		lineChartData.addValue( 03.0, line1, "18.87-25.16");   
		lineChartData.addValue( 03.0, line1, "25.16-31.45");   
		lineChartData.addValue( 01.0, line1, "31.45-37.74");   
		lineChartData.addValue( 01.0, line1, "37.74-44.0");
		
		lineChartData.addValue( -3.914, line2, "0-06.29");
		lineChartData.addValue( 2.376, line2, "6.29-12.58");   
		lineChartData.addValue( 8.666, line2, "12.58-18.87");   
		lineChartData.addValue( 14.956, line2, "18.87-25.16");   
		lineChartData.addValue( 21.246, line2, "25.16-31.45");   
		lineChartData.addValue( 27.536, line2, "31.45-37.74");   
		lineChartData.addValue(	33.926, line2, "37.74-44.0");
		
		JFreeChart lineChart = ChartFactory.createLineChart(
				"Dados da Represa Jaguari/Jacareí de Janeiro-Março 2018", 
				"Amplitude em mílilitros", 
				"Dias com valores em respectivas amplitudes", 
				lineChartData, 
				PlotOrientation.VERTICAL,
				true, true, false);
		
		lineChart.setBorderPaint(Color.RED);
		lineChart.setBackgroundPaint(Color.WHITE);
		lineChart.getTitle().setPaint(Color.BLACK);
		CategoryPlot linChr = lineChart.getCategoryPlot();
		linChr.setRangeGridlinePaint(Color.WHITE);	
		ChartPanel linePanel = new ChartPanel(lineChart);
		linePanel.setPreferredSize(new Dimension(800, 500));
		
		panelLineChart.setVisible(true);
		panelLineChart.removeAll();
		panelLineChart.add(linePanel, BorderLayout.CENTER);
		panelLineChart.validate();
		return panelLineChart;
	}
	public static JTabbedPane initialize() {
		
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 825, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(new Dimension(925, 650));
		frame.setVisible(true);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 875, 600);
		frame.getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Bar Chart", null, makeBarChart(), null);
		
		tabbedPane.addTab("Line Chart", null, makeLineChart(), null);
		
		tabbedPane.addTab("Tables(Jan-Mar)", null, setUpMyCSVArray(), null);
		
		tabbedPane.addTab("Overall Table", null, setUpMyTable(), null);
		
		return tabbedPane;
	}
	public static void main(String[] args)
	{
		initialize();
	}
}

