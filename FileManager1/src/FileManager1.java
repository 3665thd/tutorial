import java.awt.*;  
import java.awt.event.*;  
import java.io.*;  
import java.awt.datatransfer.*;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Vector;  
import javax.swing.*;  
import javax.swing.table.DefaultTableModel;  

	 public class FileManager1 implements ActionListener, ClipboardOwner {  
	 JPanel Pa = new JPanel(new BorderLayout());  
     JTextField TF = new JTextField("C:\\");  
     JTable Ta = new JTable();  
     String[] file = {"ÇÑ±Û", "English"};  
     JComboBox CB1 = new JComboBox(file);  
     JList<String> Li1 = new JList<>();  
     JScrollPane Sp1 = new JScrollPane();  
     JScrollPane Sp2 = new JScrollPane();  
     JLabel La = new JLabel("File Manager");  
     JPanel Pa1 = new JPanel(new BorderLayout());  
     JPanel Pa2 = new JPanel(new BorderLayout());  
     File name;  
    
     String path = "C:\\";  
     String[][] data;   
     
     String[] Name_List;  
  
         public FileManager1() {  
         JFrame f = new JFrame("FileManager");  
         f.setLayout(new BorderLayout());  
       
         Ta.setBackground(Color.white);  
         Ta.getTableHeader().setReorderingAllowed(false);  
         Sp1.setPreferredSize(new Dimension(200, -1));  
         TF.setText("C:\\");  
          

           
         getJList();  

          for (int i = 0; i < 4; i++) {  


        }  
          		Sp1.setViewportView(Li1);;  
          		Sp2.setViewportView(Ta);  
          		Pa1.add(La,BorderLayout.WEST);  
          		Pa1.add(CB1,BorderLayout.EAST);  
          		Pa2.add(Sp2, BorderLayout.CENTER);  
          		Pa2.add(Sp1,BorderLayout.WEST);  
          		Pa.add(TF, BorderLayout.NORTH);  
          		Pa.add(Pa1, BorderLayout.SOUTH);  
          		Pa.add(Pa2, BorderLayout.CENTER);  
        
          		f.setSize(750, 600);  
          		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
          		f.add(Pa);  
          		f.setVisible(true);     
     }  
 
    		@Override  
     		public void lostOwnership(Clipboard Clipboard, Transferable Contents) {  
          }  
   
   			public void actionPerformed(ActionEvent e) {}  
   				private void getJList() {  
        
   						File[] list;  
   						File[] file_list;  
   						name = new File(path);  
  
   						list = name.listFiles(File::isDirectory);  
   						file_list = name.listFiles(File::isFile);  
 
   						Name_List = new String[0];  
   				if (list != null) {  
   					Name_List = new String[list.length + 1];  
   				for (int i = -1; i < list.length; i++) {  
   					String back = "..";  
   				if (i == -1) Name_List[0] = back;  
                else {  
                if (list[i].getName().contains("$") ||  
                    list[i].getName().contains("Recovery") ||  
                    list[i].getName().contains("System") ||  
                    list[i].getName().contains("Temp") ||  
                    list[i].getName().contains("PerfLogs") ||  
                    list[i].getName().contains("Documents and Settings") ||  
                   !list[i].canRead()) continue;  
  
                   Name_List[i + 1] = list[i].getName();  
                 }  
           }  
         }  

        Li1.setListData(Name_List);  
         if (Li1.getVisibleRowCount() != 0)  
             data = new String[0][3];  
         if (file_list != null) {  
             {  
                data = new String[file_list.length][3];  
                for (int i = 0; i < file_list.length; i++) {  
                    data[i][0] = file_list[i].getName();  
                     String file_size;  
                     long size = file_list[i].length();}}}  
                    
   
      
    }  
  
    public static void main(String[] args) {  
         new FileManager1();  
     }  
}  