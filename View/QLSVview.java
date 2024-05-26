package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.border.EmptyBorder;
import Data.FileHandler;

import Model.SinhVien;

public class QLSVview extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private List<SinhVien> sinhVienList;

    public static void main(String[] args) {
        try {
            QLSVview frame = new QLSVview();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QLSVview() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 762, 465);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        mnFile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(mnFile);

        JMenuItem mntmNewMenuItem = new JMenuItem("Open File");
        mnFile.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Close File");
        mnFile.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Exit");
        mnFile.add(mntmNewMenuItem_2);

        JMenu them = new JMenu("Thêm sinh viên");
        them.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(them);

        JMenuItem menuItemThemSV = new JMenuItem("Thêm sinh viên");
        menuItemThemSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Them themView = new Them();
                themView.setVisible(true);
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
        them.add(menuItemThemSV);

        JMenu sua = new JMenu("Sửa thông tin sinh viên");
        sua.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(sua);

        JMenuItem menuItemSuaSV = new JMenuItem("Sửa sinh viên");
        menuItemSuaSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sua suaView = new Sua();
                suaView.setVisible(true);
                dispose();
            }
        });
        sua.add(menuItemSuaSV);

        JMenu mnNewMenu_4 = new JMenu("Xóa sinh viên");
        mnNewMenu_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(mnNewMenu_4);

        JMenu mnNewMenu_1 = new JMenu("Tìm sinh viên");
        mnNewMenu_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Tìm theo mã");
        mnNewMenu_1.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Tìm theo tên");
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Tìm theo quê quán");
        mnNewMenu_1.add(mntmNewMenuItem_5);

        JMenuItem menuItemShowSV = new JMenuItem("Hiển thị sinh viên");
        menuItemShowSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showSinhVienList();
                DanhSachSinhVienView danhSachView = new DanhSachSinhVienView(sinhVienList);
                danhSachView.setVisible(true);
                dispose();
            }
        });

        mnNewMenu_1.add(menuItemShowSV);

        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Mã sinh viên");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblNewLabel.setBounds(562, 42, 133, 27);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Tìm kiếm");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton.setBounds(10, 37, 142, 41);
        contentPane.add(btnNewButton);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textField.setBounds(428, 43, 108, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        // Thêm các thành phần tìm kiếm theo mã, theo tên, theo quê vào giao diện chính
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = textField.getText();
                // Hiển thị giao diện tìm kiếm tương ứng
                if (searchText.equals("theo mã")) {
                    TimTheoMaView timTheoMaView = new TimTheoMaView(sinhVienList);
                    timTheoMaView.setVisible(true);
                } else if (searchText.equals("theo tên")) {
                    TimTheoTenView timTheoTenView = new TimTheoTenView(sinhVienList);
                    timTheoTenView.setVisible(true);
                } else if (searchText.equals("theo quê")) {
                    TimTheoQueView timTheoQueView = new TimTheoQueView(sinhVienList);
                    timTheoQueView.setVisible(true);
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào các menu items
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimTheoMaView timTheoMaView = new TimTheoMaView(sinhVienList);
                timTheoMaView.setVisible(true);
            }
        });

        mntmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimTheoTenView timTheoTenView = new TimTheoTenView(sinhVienList);
                timTheoTenView.setVisible(true);
            }
        });

        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimTheoQueView timTheoQueView = new TimTheoQueView(sinhVienList);
                timTheoQueView.setVisible(true);
            }
        });

        this.setVisible(true);
    }

    // Phương th
    // Phương thức để hiển thị danh sách sinh viên
    private void showSinhVienList() {
        // Đọc danh sách sinh viên từ file
        sinhVienList = FileHandler.readFromFile();
        
        if (sinhVienList != null) {
            System.out.println("Danh sách sinh viên:");
            for (SinhVien sv : sinhVienList) {
                System.out.println(sv);
            }
        } else {
            System.out.println("Không thể đọc danh sách sinh viên từ file.");
        }
    }
}
