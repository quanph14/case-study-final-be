package com.codegym.webthuenha.controller.house;


import com.codegym.webthuenha.model.DTO.HouseDTO;
import com.codegym.webthuenha.model.EmailDetails;
import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.HouseStatus;
import com.codegym.webthuenha.model.Image;
import com.codegym.webthuenha.service.email.EmailService;
import com.codegym.webthuenha.service.house.IHouseService;
import com.codegym.webthuenha.service.housestatus.IHouseStatusService;
import com.codegym.webthuenha.service.image.IImageService;
import com.codegym.webthuenha.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseController {

    @Autowired
    EmailService emailService;

    @Autowired
    IHouseService houseService;

    @Autowired
    IImageService imageService;

    @Autowired
    IHouseStatusService houseStatusService;

    @Autowired
    IUserService userService;

    @Operation(summary = "Get all Houses", description = "Get all Houses", tags = {"house"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = House.class))))})
    @GetMapping("/list")
    public ResponseEntity<Iterable<House>> showAllHouse() {
        Iterable<House> houses = houseService.findAll();
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }
    @GetMapping("/list/{start}")
    public ResponseEntity<Iterable<House>> showAllHousePage9(@PathVariable Long start) {
        Iterable<House> houses = houseService.findAllPage9(start);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @Operation(summary = "Add a new house", description = "Add a new house", tags = {"house"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/xml", schema = @Schema(implementation = House.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = House.class))}), @ApiResponse(responseCode = "405", description = "Invalid input")})
    @PostMapping("/create/{id}")
    public ResponseEntity<House> createHouse(@PathVariable("id") Long id, @RequestBody HouseDTO houseDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        House house = new House();
        house.setId(houseDTO.getId());
        house.setHouseName(houseDTO.getHouseName());
        house.setHouseAddress(houseDTO.getHouseAddress());
        house.setBathrooms(houseDTO.getBathrooms());
        house.setBedrooms(houseDTO.getBedrooms());
        house.setDescription(houseDTO.getDescription());
        house.setRent(houseDTO.getRent());
        house.setStatus(houseStatusService.findById(Long.parseLong("2")).get());
        List<Image> imageList = new ArrayList<>();

        for (int i = 0; i < houseDTO.getListImage().size(); i++) {
            Image image = new Image(houseDTO.getListImage().get(i));
            imageService.save(image);
            imageList.add(imageService.findByName(image.getImageName()).get());
            house.setImage(imageList);


        }
        house.setUser(userService.findById(id).get());
        houseService.save(house);
        return new ResponseEntity<>(houseService.save(house), HttpStatus.OK);
    }

    @Operation(summary = "Update an status house", description = "Update an status house by Id", tags = {"house"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/xml", schema = @Schema(implementation = House.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = House.class))}), @ApiResponse(responseCode = "400", description = "Invalid ID supplied"), @ApiResponse(responseCode = "404", description = "House not found"), @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/updateStatus/{id}/{idStatus}")
    public ResponseEntity<House> updateStatusOfHouse(@PathVariable("id") Long id, @PathVariable("idStatus") Long idStatus) {
        if (!houseService.findById(id).isPresent() || !houseStatusService.findById(idStatus).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        House house = houseService.findById(id).get();
        HouseStatus status = houseStatusService.findById(idStatus).get();
        house.setStatus(status);
        houseService.save(house);
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @Operation(summary = "Find house by ID", description = "Returns a single house", tags = {"house"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = House.class))), @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content), @ApiResponse(responseCode = "404", description = "House not found", content = @Content)})
    @GetMapping("imageString/{id}")
    public ResponseEntity<House> getOneHouse(@PathVariable Long id) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        House house = optionalHouse.get();
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @Operation(summary = "Get 5 houses", description = "Get 5 house", tags = {"house"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = House.class))))})
    @GetMapping("/list5house")
    public ResponseEntity<Iterable<House>> show5HouseByRent() {
        Iterable<House> house = houseService.get5HouseByRent();
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @Operation(summary = "Send mail", description = "Send mail", tags = {"mail"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = House.class))))})
    @PostMapping("/sendMail")
    public ResponseEntity<EmailDetails> sendMail(@RequestBody EmailDetails details, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String status = emailService.sendSimpleMail(details);
        System.out.println("ddasdiasbt:" + status);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("/searchAllHouse")
    public ResponseEntity<Iterable<House>> searchAllHouse(@PathVariable int bath, int bed, String address, Date startTime, Date endTime, Long maxRent, Long minRent) {
        Iterable<House> houses = houseService.searchAllHouse(bath, bed, address, startTime, endTime, maxRent, minRent);
        return new ResponseEntity<>(houses, HttpStatus.OK);

    }

    @GetMapping("/findByUser/{id}")
    public ResponseEntity<Iterable<House>> findByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(houseService.findByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<House>> findHouseByAll(@RequestParam(name = "bedrooms") String bedrooms,
                                                          @RequestParam(name = "bathrooms") String bathrooms,
                                                          @RequestParam(name = "address") String address,
                                                          @RequestParam(name = "rentMin") long rentMin,
                                                          @RequestParam(name = "rentMax") long rentMax,
                                                          @RequestParam(name = "endTime") String endTime,
                                                          @RequestParam(name = "startTime") String startTime){
        return new ResponseEntity<>(
                houseService.findHouseByAll(bedrooms, bathrooms, address, rentMin, rentMax, endTime, startTime),
                HttpStatus.OK);
    }
}
