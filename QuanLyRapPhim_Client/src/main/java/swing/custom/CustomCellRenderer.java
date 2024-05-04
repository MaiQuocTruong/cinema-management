package swing.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
public class CustomCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
	public CustomCellRenderer() {
		setHorizontalAlignment(JLabel.LEFT); // Căn giữa văn bản trong tiêu đề
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Đặt màu nền cho tiêu đề cột
		component.setBackground(Color.WHITE); // Màu nền của tiêu đề cột (column header)

		Font font = component.getFont();
		font = font.deriveFont(Font.BOLD);
		component.setFont(font);
		
		return component;
	}
}
