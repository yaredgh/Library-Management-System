package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Controllers;


import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Book;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Role;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.User;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.UserShipping;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.UserRepository;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.BookService;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.UserService;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.ServiceImp.BookStoreUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;

@Controller
public class HomeController {
    private static final Logger log = LogManager.getLogger(HomeController.class);
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/onlinebookstore/public/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/onlinebookstore/public/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "userLogin";
    }

//    @RequestMapping("/welcome")
//    public String hours() {
//        return "/common/header";
//    }

//	@RequestMapping("/faq")
//	public String faq() {
//		return "faq";
//	}

    @RequestMapping("/bookshelf")
    public String bookshelf(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        model.addAttribute("activeAll", true);

        return "bookshelf";
    }

    @RequestMapping("/bookDetail")
    public String bookDetail(
            @PathParam("id") Long id, Model model, Principal principal
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        Optional<Book> book = bookService.findOne(id);

        model.addAttribute("book", book);

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "bookDetail";
    }

    @RequestMapping("/myprofile")
    public String myProfile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "myProfile";
    }

    @PostMapping("/onlinebookstore/public/newaccount")
    public String saveAccount(@Valid User user, BindingResult result, Model model) throws Exception {

        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "redirect:/onlinebookstore/public/login";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailExists", true);

            return "redirect:/onlinebookstore/public/login";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("emailExists", true);

            return "redirect:/onlinebookstore/public/login";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return "redirect:/onlinebookstore/public/login";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute  User user){

        return "userLogin";
    }

//	@RequestMapping("/orderDetail")
//	public String orderDetail(
//			@RequestParam("id") Long orderId,
//			Principal principal, Model model
//			){
//		User user = userService.findByUsername(principal.getName());
//		Order order = orderService.findOne(orderId);
//
//		if(order.getUser().getId()!=user.getId()) {
//			return "badRequestPage";
//		} else {
//			List<CartItem> cartItemList = cartItemService.findByOrder(order);
//			model.addAttribute("cartItemList", cartItemList);
//			model.addAttribute("user", user);
//			model.addAttribute("order", order);
//
//			model.addAttribute("userPaymentList", user.getUserPaymentList());
//			model.addAttribute("userShippingList", user.getUserShippingList());
//			model.addAttribute("orderList", user.getOrderList());
//
//			UserShipping userShipping = new UserShipping();
//			model.addAttribute("userShipping", userShipping);
//
//			List<String> stateList = USConstants.listOfUSStatesCode;
//			Collections.sort(stateList);
//			model.addAttribute("stateList", stateList);
//
//			model.addAttribute("listOfShippingAddresses", true);
//			model.addAttribute("classActiveOrders", true);
//			model.addAttribute("listOfCreditCards", true);
//			model.addAttribute("displayOrderDetail", true);
//
//			return "myProfile";
//		}
}
