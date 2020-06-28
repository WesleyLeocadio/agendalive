package com.spring.resource;

import com.spring.domain.LiveDomain;
import com.spring.service.LiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@Api(value="API REST")
public class LiveResource {
    @Autowired
    LiveService liveService;


    @ApiOperation(value="Todas as Lives")
    @GetMapping("/lives")
    public ResponseEntity<Page<LiveDomain>> getAllLives(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                        @RequestParam(required = false) String flag){
        Page<LiveDomain> livePage = liveService.findAll(pageable, flag);
        if(livePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Page<LiveDomain>>(livePage, HttpStatus.OK);
        }
    }

    @ApiOperation(value="Live por ID")
    @GetMapping("/lives/{id}")
    public ResponseEntity<LiveDomain> getOneLive(@PathVariable(value="id") Integer id){
        Optional<LiveDomain> liveO = liveService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<LiveDomain>(liveO.get(), HttpStatus.OK);
        }
    }
    @ApiOperation(value="Adicionar live")
    @PostMapping("/lives")
    public ResponseEntity<LiveDomain> saveLive(@RequestBody @Valid LiveDomain live) {
        live.setRegistrationDate(new Date());

        return new ResponseEntity<LiveDomain>(liveService.save(live), HttpStatus.CREATED);
    }

    @ApiOperation(value="Deletar Live")
    @DeleteMapping("/lives/{id}")
    public ResponseEntity<?> deleteLive(@PathVariable(value="id") Integer id) {
        Optional<LiveDomain> liveO = liveService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            liveService.delete(liveO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value="Atualizar live")
    @PutMapping("/lives/{id}")
    public ResponseEntity<LiveDomain> updateLive(@PathVariable(value="id") Integer id,
                                                   @RequestBody @Valid LiveDomain liveDocument) {
        Optional<LiveDomain> liveO = liveService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            liveDocument.setId(liveO.get().getId());
            return new ResponseEntity<LiveDomain>(liveService.save(liveDocument), HttpStatus.OK);
        }
    }
}
