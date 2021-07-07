package coba.daily.you.controller.restapi;

import coba.daily.you.model.dto.AddToCartDto;
import coba.daily.you.model.dto.CartDto;
import coba.daily.you.model.dto.CartItemsDto;
import coba.daily.you.model.dto.UserDto;
import coba.daily.you.model.entity.Cart;
import coba.daily.you.model.entity.CartStatus;
import coba.daily.you.model.entity.OrderStatus;
import coba.daily.you.repository.CartRepository;
import coba.daily.you.repository.UserRepository;
import coba.daily.you.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class ApiCart {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartService cartService;
//
//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    private Long currentIdUser(){
//        String userNama = null;
////        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        Long idUser = userRepository.findIdByUserName(userNama);
//        return idUser;
//    }

    @GetMapping("/idUser")
    public String getIdUser(UserDto loginDto){
        return loginDto.getUserKeyId();
    }

    @GetMapping
    public List<CartDto> getAll() {
        List<Cart> cartList = cartRepository.findAllByIdUserAndStatus(1L, String.valueOf(CartStatus.ADD_TO_CART));
        List<CartDto> cartDtooList = cartList
                .stream()
                .map(cart -> mapToCartDto(cart))
                .collect(Collectors.toList());
        return cartDtooList;
    }

    @PostMapping()
    public List<AddToCartDto> saveCart(@RequestBody CartItemsDto cartItemsDto){
        List<AddToCartDto> addToCartDtoList = cartItemsDto.getAddToCart()
                .stream()
                .map(cart -> mapToCartDto(cart)).collect(Collectors.toList());
        return addToCartDtoList;
    }

    private AddToCartDto mapToCartDto(AddToCartDto addToCartDto){
//        AddToCartDto addToCartDto1 = modelMapper.map(addToCartDto, AddToCartDto.class);
        Cart cart = modelMapper.map(addToCartDto, Cart.class);
//        cart.setProductQuantity(addToCartDto.getProductQuantity());
        cart.setIdUser(1l);
        cartService.saveToCart(cart, addToCartDto);
        return cart;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        cartRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAll(){
        cartRepository.deleteAll();
    }


    private CartDto mapToCartDto(Cart cart){
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        cartDto.setProductName(cart.getProduct().getProductName());
        cartDto.setPrice(cart.getProduct().getPrice());
        cartDto.setSubTotalPrice(cart.getSubTotalPrice());
        return cartDto;
    }

}
