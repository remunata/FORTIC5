package com.pplbo.fortic5;

import com.pplbo.fortic5.model.product.Category;
import com.pplbo.fortic5.model.product.Kondisi;
import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.user.Role;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.service.product.ProductService;
import com.pplbo.fortic5.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Fortic5Application implements CommandLineRunner {

	private final UserService userService;
	private final ProductService productService;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Fortic5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var remunata = User.builder()
				.fullName("Mahendra Dinata")
				.username("remunata")
				.email("remdinata@gmail.com")
				.password(passwordEncoder.encode("qwerty123"))
				.role(Role.CUSTOMER)
				.address("Komplek Persada")
				.build();

		var reiiye = User.builder()
				.fullName("Widya Fitriani")
				.username("reiiye")
				.email("wdyftr@gmail.com")
				.password(passwordEncoder.encode("qwerty123"))
				.role(Role.SELLER)
				.address("Kertapati")
				.build();

		userService.save(remunata);
		reiiye = userService.save(reiiye);

		var mouseLogitech = Product.builder()
				.name("Mouse Logitech Gaming")
				.price(1500000)
				.description("""
						Pilih koneksi wireless yang andal sekarang juga dengan Logitech M171 Wireless Mouse.
						Mouse komputer yang harganya terjangkau dan mudah digunakan ini memiliki
						daya tahan baterai 12 bulan(1) dan kompatibel dengan sistem operasi
						Windows, macOS, Linux, ChromeOS, ipadOS, dan Android.
						""")
				.rating(5)
				.stock(89)
				.brand("Logitech")
				.kondisi(Kondisi.BARU)
				.category(Category.AKSESORIS)
				.seller(reiiye)
				.build();

		var keyboard = Product.builder()
				.name("Keychron K2 RGB Backlight")
				.price(1285000)
				.description("""
						INI SUDAH K2 VERSION 2 ... BUKAN HOT-SWAPPBLE, switch TIDAK bisa Dicopot
						      
						Bisa dipake di MacOS, Windows, Linux, Android, IOS
						Bisa koneksi bluetooth ke 3 perangkat bergantian
						Klo pake Bluetooth MOBO sering bermasalah, jadi beli BT Receiver terpisah ya
						      
						What's new on K2v2
						Bluetooth 5.1
						Inclined bottom frame.
						Added dedicated caps lock indicator light
						""")
				.rating(4)
				.stock(31)
				.brand("Keychron")
				.kondisi(Kondisi.BARU)
				.category(Category.AKSESORIS)
				.seller(reiiye)
				.build();

		List<Product> products = List.of(mouseLogitech, keyboard);
		productService.saveAll(products);

	}
}
