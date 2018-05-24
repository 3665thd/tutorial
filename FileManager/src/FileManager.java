import java.awt.*;  
import java.awt.event.*;  
import java.io.*;  
import java.awt.datatransfer.*;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Vector;  
import javax.swing.*;  
import javax.swing.table.DefaultTableModel;  

	 public class FileManager implements ActionListener, ClipboardOwner {  
	 JPanel Pa = new JPanel(new BorderLayout());  
     JTextField TF = new JTextField("C:\\");  
     JTable Ta = new JTable();  
     String[] file = {"한글", "English"};  
     JComboBox CB1 = new JComboBox(file);  
     JList<String> Li1 = new JList<>();  
     JScrollPane Sp1 = new JScrollPane();  
     JScrollPane Sp2 = new JScrollPane();  
     JLabel La = new JLabel("File Manager");  
     JPanel Pa1 = new JPanel(new BorderLayout());  
     JPanel Pa2 = new JPanel(new BorderLayout());  
     File name;  
     File getBack;  
     String path = "C:\\";  
     String[][] data;  
     JMenuItem[] Korean = new JMenuItem[6];  
     JMenuItem[] American_language = new JMenuItem[6];  
     DefaultTableModel Tm;  
     int[] copy;  
     String[] Korean1 = {"이름", "크기", "수정한 날짜"};  
     String[] English = {"Name", "Size", "Modified"};  
     Vector<String> File12;  
     String[] Name_List;  
  
         public FileManager() {  
         JFrame f = new JFrame("FileManager");  
         f.setLayout(new BorderLayout());  
         Korean[0] = new JMenuItem("폴더 보기");  
         Korean[1] = new JMenuItem("복사하기");  
         Korean[2] = new JMenuItem("붙여넣기");  
         Korean[3] = new JMenuItem("삭제");  
         American_language[0] = new JMenuItem("Show Item in the Folder");  
         American_language[1] = new JMenuItem("Copy");  
         American_language[2] = new JMenuItem("Paste");  
         American_language[3] = new JMenuItem("Delete");  
         Ta.setBackground(Color.white);  
         Ta.getTableHeader().setReorderingAllowed(false);  
         Sp1.setPreferredSize(new Dimension(200, -1));  
         TF.setText("C:\\");  
         Korean[2].setEnabled(false);  
         American_language[2].setEnabled(false);  
           
         getJList();  
  
         Li1.addMouseListener(new MouseAdapter() {  
         @Override  
         public void mouseClicked(MouseEvent e) {  
         try {  
              String clicked;  
              getBack = new File(path, "..");  
              clicked = Li1.getSelectedValue();  
         if (clicked.equals("..")) {  
         try { path = getBack.getCanonicalPath();  
       } catch (Exception ignored) {  }  
         TF.setText(path);  
                        
          getJList();  
                   
        } else {  path = name.getPath() + File.separator + clicked;  
          if (path.contains("C:\\\\"))  
              path = name.getPath() + clicked;  
          TF.setText(path);  
              
              getJList();  
                   }  
          } catch (NullPointerException aee) {  }  
          }  
           });  
            Sp2.addMouseListener(new MouseAdapter() {  
          @Override  
            public void mouseClicked(MouseEvent e) {  
            if (SwingUtilities.isRightMouseButton(e)) {  
                JPopupMenu PopMenu = new JPopupMenu();  
            if (CB1.getSelectedItem() == "한글") {  
            for (int i = 0; i < 4; i++) {  
            if (i == 1) PopMenu.addSeparator();  
            if (i == 1 || i == 3) continue;  
                PopMenu.add(Korean[i]);  }  
          } else {  
            for (int i = 0; i < 4; i++) {  
            if (i == 1) PopMenu.addSeparator();  
            if (i == 1 || i == 3) continue;  
                PopMenu.add(American_language[i]);  
                    } }  
                PopMenu.show(Ta, e.getX(), e.getY());  
                PopMenu.setVisible(true);  
              }  
           }  
       });  
 
            Ta.addMouseListener(new MouseAdapter() {  
            @Override  
           public void mouseClicked(MouseEvent e) {  
           if (SwingUtilities.isRightMouseButton(e)) {  
               JPopupMenu PopMenu = new JPopupMenu();  
           if (CB1.getSelectedItem() == "한글") {  
           for (int i = 0; i < 4; i++) {  
           if (i == 1 || i == 3) PopMenu.addSeparator();  
               PopMenu.add(Korean[i]);  
                        }  
         } else {  
           for (int i = 0; i < 4; i++) {  
           if (i == 1 || i == 3) PopMenu.addSeparator();  
           PopMenu.add(American_language[i]);  
              }  
           }  
           PopMenu.show(Ta, e.getX(), e.getY());  
           PopMenu.setVisible(true);  
        }  
      }  
       });  
          for (int i = 0; i < 4; i++) {  
        	  American_language[i].addActionListener(this);  
        	  Korean[i].addActionListener(this);  
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
        
          		CB1.addActionListener(e -> setTable());  
     }  
 
    		@Override  
     		public void lostOwnership(Clipboard aClipboard, Transferable aContents) {  
          }  
   
   			public void actionPerformed(ActionEvent e) {  
   				if (e.getSource() == Korean[3] || e.getSource() == American_language[3]) {  
   				int[] columns = Ta.getSelectedRows();  
   				for (int column : columns) {  
   					System.out.println(column);  
   					System.out.println(path + File.separator + Ta.getValueAt(column, 0));  }  
   				for (int column : columns) {  
   					File delete = new File(path + File.separator + Ta.getValueAt(column, 0));  
   					delete.delete();  }  
   				for (int i = 0; i < columns.length; i++)  
   					Tm.removeRow(columns[i] - i);  
   					Tm.fireTableDataChanged();  
   					Ta.updateUI();   }  
   
   				if (e.getSource() == Korean[0] || e.getSource() == American_language[0]) {  
   					File open_Directory = new File(path);  
   				try {  
   					Desktop.getDesktop().open(open_Directory);  
            } catch (IOException ignored) {  
            }  
        }  
   
   				if (e.getSource() == Korean[1] || e.getSource() == American_language[1]) {  
   					Korean[2].setEnabled(true);  
   					American_language[2].setEnabled(true);  
   					copy = Ta.getSelectedRows();  
   					File12 = new Vector<>(Ta.getRowCount());  
   				for (int i = 0; i < copy.length; i++) {  
   					File12.add(i, (path + "\\" + Ta.getValueAt(copy[i], 0)));  
            }  
         }  
   
   				if (e.getSource() == Korean[2] || e.getSource() == American_language[2]) {  
   					String tmp = path;  
   					int count = 0;  
   				for (int i = 0; i < copy.length; i++) {  
   					String command = "cmd /c copy \"" + File12.get(i) + "\"" + " \"" 
   					+ tmp + "\" /y";  
                try {  
                	Process child = Runtime.getRuntime().exec(command);  
                    InputStreamReader in = new InputStreamReader(child.getInputStream(),
                   "MS949");  
                    int c;  
                    StringBuilder result;  
                    result = new StringBuilder();  
                while ((c = in.read()) != -1) {  
                    result.append((char) c);  
                    }  
                if (result.toString().contains("0개의 파일이 복사 됬어요.")) {  
                if (CB1.getSelectedItem() == "한글")  
                    JOptionPane.showMessageDialog(null, 
                    "액세스 권한이 없어요.", "에러",
                    JOptionPane.ERROR_MESSAGE);  
               else  
                    JOptionPane.showMessageDialog(null,
                    "You don't have access", 
                    "Error", JOptionPane.ERROR_MESSAGE);  
                    in.close();  
                    break;   }  
                    in.close();  
                    count++;  
             } catch (Exception ee) {  
                    ee.printStackTrace();  
              }  
           }  
   					getJList();  
   				if (count == copy.length) {  
   					TF.setText(path);  
   					getJList();  
           }  
      }  
     }  
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
                     long size = file_list[i].length();  
                    if (size < 1024) {  
                         file_size = String.format("%d B", size);  
                   } else if (size < 1024 * 1024) {  
                        file_size = String.format("%.2f KB", size / 1024.0);  
                    } else if (size < 1024 * 1024 * 1024) {  
                        file_size = String.format("%.2f MB", size / 1048576.0);  
                    } else {  
                         file_size = String.format("%.2f GB", size / 1073741824.0);  
                  }  
                     data[i][1] = file_size; 
                     Date dt = new Date(file_list[i].lastModified());  
                    SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy HH:mm:ss");  
                     String date = formatter.format(dt);  
                    data[i][2] = String.valueOf(date);  
                }  
            }  
   
            setTable();  
        } else {  
           if (CB1.getSelectedItem() == "한글")  
            JOptionPane.showMessageDialog(null, "액세스 권한이 없어요.",
            		"에러", JOptionPane.ERROR_MESSAGE);  
           else JOptionPane.showMessageDialog(null, "You don't have access",
        		   "Error", JOptionPane.ERROR_MESSAGE);  
           getBack = new File(path, "..");  
            try {  
                path = getBack.getCanonicalPath();  
            } catch (Exception ignored) {  
   
           }  
            TF.setText(path);  
            getJList();  
 
        }  
    }  
    private void setTable() {  
        if (CB1.getSelectedItem() == "한글") {  
        	Tm = new DefaultTableModel(data, Korean1);  
             Ta.setModel(Tm);  
         La.setText("파일매니저");  
         }  
         if (CB1.getSelectedItem() == "English") {  
        	 Tm = new DefaultTableModel(data, English);  
             Ta.setModel(Tm);  
             La.setText("File Manager");  
       }  
    }  
  
    public static void main(String[] args) {  
         new FileManager();  
     }  
}  