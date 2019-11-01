package com.adservio.cesco.web.controllers;

import com.adservio.cesco.domain.OriginMission;
import com.adservio.cesco.services.OriginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Origin Mission Resource
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Origin Mission", description = "REST Entry Point for Origin controller")
public class OriginController {

    @Autowired
    private final OriginService originService;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    private static final Integer DEFAULT_PAGE_SIZE = 25;

    @ApiOperation(value = "Returns all origin missions resources")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "OK")
            }
    )
    @GetMapping(produces = {"application/json"}, path = "origines")
    public ResponseEntity getAllOriginMission(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Page<OriginMission> pageOfOrigin = originService.findAll(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);

    }


    @ApiOperation(value = "Create a new origin missions resources")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "OK")
            }
    )
    @PostMapping("/origines")
    public ResponseEntity saveOriginMission(@RequestBody @Validated OriginMission OriginMission) {
        return new ResponseEntity<>(originService.createOriginMission(OriginMission), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Create or update a origin missions resources")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "OK")
            }
    )
    @PutMapping("/origines")
    public ResponseEntity updateOriginMission(@RequestBody @Validated OriginMission OriginMission) {
        return new ResponseEntity<>(originService.updateOriginMission(OriginMission), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a origin missions resources")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "OK")
            }
    )
    @DeleteMapping({"/{originMissionId}"})
    public ResponseEntity deleteOriginMission(@PathVariable("originMissionId") Long originMissionId) {
        if (!originService.exist(originMissionId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            originService.deleteById(originMissionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
