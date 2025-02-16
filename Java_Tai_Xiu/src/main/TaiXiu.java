package main;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/*
 * 1 người chơi sẽ có tài khoản. Người chơi có quyền đặt cược số tiền ít hơn hoặc bằng số tiền họ đang có.
 * Luật chơi như sau:
 * Có 3 viên xúc xắc. Mỗi viên xúc xắc có 6 mặt, mỗi mặt có giá trị từ 1 đến 6. Mỗi lần lắc ra 1 kết quả. 
 * Tổng = giá trị xx1 + giá trị xx2 + giá trị xx3
 * 1. Nếu tổng = 3 hoặc 18 => Cái ăn hết, người chơi thua
 * 2. Nếu tổng =  4 -> 10 => Người chơi đặt xỉu thắng, người chơi đặt tài thua
 * 3. Nếu tổng = 11 -> 17 => Người chơi đặt tài thắng, người chơi đặt xỉu thua
 */
public class TaiXiu {
	public static void main(String[] args) {
		double taiKhoanNguoiChoi = 5000;
		
		// Khởi tại scanner
		Scanner scanner = new Scanner(System.in);
		@SuppressWarnings("deprecation")
		
		// Khởi tạo numberFormat
		Locale locale = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		
		int luaChon = 1;
		do {
			System.out.println("Chọn (1) để tiếp tục chơi.");
			System.out.println("Chọn số bất kỳ khác 1 để thoát.");
			System.out.println("---------------------------");
			luaChon = scanner.nextInt();
			
			if (luaChon == 1) {
				System.out.println("BẮT ĐẦU CHƠI");
				System.out.println("- Tài khoản của bạn là: " + numberFormat.format(taiKhoanNguoiChoi) + ", bạn muốn cược bao nhiêu?");
				
				// Số tiền đặt cược				
				double datCuoc = scanner.nextDouble();
				while (datCuoc <= 0 || datCuoc > taiKhoanNguoiChoi) {
					System.out.print("Số tiền cược cần <= " + numberFormat.format(taiKhoanNguoiChoi) + " và lớn hơn 0. Vui nhập lại số tiền cược: ");
					datCuoc = scanner.nextDouble();
				}
				System.out.println("- Ban đã đặt cược: " + numberFormat.format(datCuoc));
				System.out.println("---------------------------");
				
				// Lựa chọn Tài hay Xỉu				
				System.out.println("- Lựa chọn của bạn là: ");
				System.out.println("Nhấn (1) để chọn Tài: ");
				System.out.println("Nhấn (2) để chọn Xỉu: ");
				int luaChonTaiXiu = scanner.nextInt();
				while (luaChonTaiXiu != 1 && luaChonTaiXiu != 2) {
					System.out.print("Lựa chọn không hợp. Vui lòng chọn lại: ");
				}
				System.out.println("Bạn đã chọn: " + (luaChonTaiXiu == 1 ? "Tài" : "Xỉu"));
				System.out.println("---------------------------");
				
				// Tung xúc xắc		
				Random xucXac1 = new Random();
				Random xucXac2 = new Random();
				Random xucXac3 = new Random();
				
				int giaTriXucXac1 = xucXac1.nextInt(6) + 1;
				int giaTriXucXac2 = xucXac2.nextInt(6) + 1;
				int giaTriXucXac3 = xucXac3.nextInt(6) + 1;
				
				// Tổng
				int tong = giaTriXucXac1 + giaTriXucXac2 + giaTriXucXac3;
				System.out.println("Xúc xắc 1: " + giaTriXucXac1);
				System.out.println("Xúc xắc 2: " + giaTriXucXac2);
				System.out.println("Xúc xắc 3: " + giaTriXucXac3);
				System.out.println("Tổng điểm là: " + tong);
				System.out.println("---------------------------");
				
				// Phân định thắng thua cho người chơi
				if (tong == 3 || tong == 18) {
					System.out.println("Nhà cái thắng. Bạn mất " + numberFormat.format(datCuoc) + " tiền cược");
					taiKhoanNguoiChoi -= datCuoc;
				} else if (tong >= 11 && tong <= 17) {
					if (luaChonTaiXiu == 1) {
						taiKhoanNguoiChoi += datCuoc;
						System.out.println("Bạn thắng. Bạn nhận thêm " + numberFormat.format(datCuoc) + " tiền cược");
					} else {
						taiKhoanNguoiChoi -= datCuoc;
						System.out.println("Bạn thua. Bạn mất " + numberFormat.format(datCuoc) + " tiền cược");
					}
				} else {
					if (luaChonTaiXiu == 2) {
						taiKhoanNguoiChoi += datCuoc;
						System.out.println("Bạn thắng. Bạn nhận thêm " + numberFormat.format(datCuoc) + " tiền cược");
					} else {
						taiKhoanNguoiChoi -= datCuoc;
						System.out.println("Bạn thua. Bạn mất " + numberFormat.format(datCuoc) + " tiền cược");
					}
				}
				
				System.out.println("Số tiền của bạn hiện tại là: " + numberFormat.format(taiKhoanNguoiChoi));
				System.out.println("---------------------------");
			}
		} while (luaChon == 1 && taiKhoanNguoiChoi > 0);
		System.out.println("Kết thúc trò chơi.");
		scanner.close();
		return;
	}
}





