package com.stacksimplify.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {
	/*
	 * Deprecated
	 * @Autowired private UserRepository userRepository;
	 * 
	 * @Autowired private UserService userService;
	 * 
	 * // getUserById
	 * 
	 * @GetMapping("/{id}") public Resource<User>
	 * getUserById(@PathVariable("id") @Min(1) Long id) {
	 * 
	 * try { Optional<User> userOptional = userService.getUserById(id); User user =
	 * userOptional.get(); Long userid = user.getUserid(); Link selflink =
	 * ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
	 * user.add(selflink); Resource<User> finalResource = new Resource<User>(user);
	 * return finalResource;
	 * 
	 * } catch (UserNotFoundException ex) { throw new
	 * ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()); }
	 * 
	 * }
	 * 
	 * // getAllUsers Method
	 * 
	 * @GetMapping public Resources<User> getAllUsers() throws UserNotFoundException
	 * { List<User> allusers = userService.getAllUsers();
	 * 
	 * for(User user : allusers) { //Self Link Long userid = user.getUserid(); Link
	 * selflink =
	 * ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
	 * user.add(selflink);
	 * 
	 * //Relationship link with getAllOrders Resources<Order> orders =
	 * ControllerLinkBuilder.methodOn(OrderHateoasController.class)
	 * .getAllOrders(userid); Link orderslink =
	 * ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
	 * user.add(orderslink);
	 * 
	 * } //Self link for getAllUsers Link selflinkgetAllUsers =
	 * ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel(); Resources<User>
	 * finalResources = new Resources<User>(allusers, selflinkgetAllUsers); return
	 * finalResources;
	 * 
	 * }
	 * 
	 */
	/*
	 * | Old API | New API | | ----------------------- | ------------------------ |
	 * | `Resource<T>` | `EntityModel<T>` | | `Resources<T>` | `CollectionModel<T>`
	 * | | `ControllerLinkBuilder` | `WebMvcLinkBuilder` | | `ResourceSupport` |
	 * `RepresentationModel<T>` |
	 */
	    @Autowired
	    private final UserRepository userRepository;
	    @Autowired
	    private final UserService userService;

	    public UserHateoasController(UserRepository userRepository,
	                                 UserService userService) {
	        this.userRepository = userRepository;
	        this.userService = userService;
	    }

	    // Get User By Id
	    @GetMapping("/{id}")
	    public EntityModel<User> getUserById(
	            @PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

	        User user = userService.getUserById(id)
	                .orElseThrow(() ->
	                        new UserNotFoundException("User Not Found"));

	        Long userId = user.getId(); // changed from getUserid()

	        Link selfLink = linkTo(
	                methodOn(UserHateoasController.class)
	                        .getUserById(userId))
	                .withSelfRel();

	        Link ordersLink = linkTo(
	                methodOn(OrderHateoasController.class)
	                        .getAllOrders(userId))
	                .withRel("all-orders");

	        return EntityModel.of(user, selfLink, ordersLink);
	    }

	    // Get All Users
	    @GetMapping
	    public CollectionModel<EntityModel<User>> getAllUsers()
	            throws UserNotFoundException {

	        List<User> allUsers = userService.getAllUsers();

	        List<EntityModel<User>> users = allUsers.stream()
	                .map(user -> {

	                    Long userId = user.getId();

	                    Link selfLink = null;
						try {
							selfLink = linkTo(
							        methodOn(UserHateoasController.class)
							                .getUserById(userId))
							        .withSelfRel();
						} catch (UserNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	                    Link ordersLink = null;
						try {
							ordersLink = linkTo(
							        methodOn(OrderHateoasController.class)
							                .getAllOrders(userId))
							        .withRel("all-orders");
						} catch (UserNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	                    return EntityModel.of(
	                            user,
	                            selfLink,
	                            ordersLink
	                    );
	                })
	                .toList();

	        Link selfLink = linkTo(
	                methodOn(UserHateoasController.class)
	                        .getAllUsers())
	                .withSelfRel();

	        return CollectionModel.of(users, selfLink);
	    }
	}
