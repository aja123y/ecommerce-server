package com.ram.service;

import org.springframework.stereotype.Service;

import com.ram.exception.ProductException;
import com.ram.model.Cart;
import com.ram.model.CartItem;
import com.ram.model.Product;
import com.ram.model.User;
import com.ram.repository.CartRepository;
import com.ram.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService
{
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	public CartServiceImplementation(CartRepository cartRepository,
			CartItemService cartItemService,
			ProductService productService) {
		this.cartRepository = cartRepository;
		this.cartItemService = cartItemService;
		this.productService = productService;
	}

	@Override
	public Cart createCart(User user) {
		
		Cart cart = new Cart();
		cart.setUser(user);
		
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart = cartRepository.findByUserId(userId);
		Product product  = productService.findProductById(req.getProductId());
		System.out.println("Status of product : "+product);
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		System.out.println("Status of CartItem : "+isPresent);
		if(isPresent == null)
		{
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price = req.getQuantity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItem().add(createdCartItem);
			System.out.println("Hello Cart ITem"+cart.getCartItem().add(createdCartItem));
		}
		return "Item add to Cart...";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart =  cartRepository.findByUserId(userId);
		System.out.println("Status of cart : "+cart);
		
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		
		for(CartItem cartItem : cart.getCartItem())
		{
			totalPrice = totalPrice + cartItem.getPrice();
			totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
			totalItem = totalItem + cartItem.getQuantity();
		}
		
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscounte(totalPrice - totalDiscountedPrice);
		
		
		return cartRepository.save(cart);
	}
	
}
