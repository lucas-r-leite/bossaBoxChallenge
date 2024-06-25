package VUTTR.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import VUTTR.demo.dto.ToolsDTO;
import VUTTR.demo.entity.Tools;
import VUTTR.demo.service.ToolsService;

@RestController
@RequestMapping("/tools")
public class ToolsController {

    @Autowired
    private ToolsService toolsService;

 @GetMapping
public ResponseEntity<List<ToolsDTO>> findAll(@RequestParam(required = false) String tag){
    if (tag!= null) {
        List<ToolsDTO> tools = toolsService.findByTag(tag);
        return new ResponseEntity<>(tools, HttpStatusCode.valueOf(200));
    } else {
        List<ToolsDTO> tools = toolsService.findAll();
        return new ResponseEntity<>(tools, HttpStatusCode.valueOf(200));
    }
}
    @PostMapping
    public ResponseEntity<?> createTool(@RequestBody Tools body){
       
        try{
            Tools tools = toolsService.save(body);
            return new ResponseEntity<>(tools, HttpStatusCode.valueOf(201));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTool(@PathVariable Long id, @RequestBody Tools body){
        try{
            Tools tools = toolsService.updateTools(body);
            return new ResponseEntity<>(tools, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTool(@PathVariable Long id){

        try{
            toolsService.deleteTools(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

}
