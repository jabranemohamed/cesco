package com.adservio.cesco.web.controllers;

import com.adservio.cesco.domain.Station;
import com.adservio.cesco.services.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Stations", description = "REST Entry Point for Station controller")
public class StationController {

    @Autowired
    private StationService stationService;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    private static final Integer DEFAULT_PAGE_SIZE = 25;

    @ApiOperation(value = "Returns all stations resources")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "OK")
            }
    )
    @GetMapping(produces = {"application/json"}, path = "stations")
    public ResponseEntity getAllStations(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Page<Station> pageOfSation= stationService.findAll(PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(pageOfSation, HttpStatus.OK);
    }

    @DeleteMapping({"/{stationId}"})
    public ResponseEntity deleteStation(@PathVariable("stationId") Long stationId) {
        if (!stationService.exist(stationId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            stationService.deleteById(stationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
