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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/tools")
public class ToolsController {

    @Autowired
    private ToolsService toolsService;

 @GetMapping
 @Operation(summary = "Get all tools or tools by tag", description = "Retrieve a list of all tools or filter by tag if provided")
@Parameter(name = "tag", description = "Filter tools by tag (optional)", required = false)
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
    @Operation(summary = "Create a new tool", description = "Create a new tool")
    public ResponseEntity<?> createTool(@RequestBody Tools body){
       
        try{
            Tools tools = toolsService.save(body);
            return new ResponseEntity<>(tools, HttpStatusCode.valueOf(201));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a tool", description = "Update an existing tool")
    public ResponseEntity<?> updateTool(@PathVariable Long id, @RequestBody Tools body){
        try{
            Tools tools = toolsService.updateTools(body);
            return new ResponseEntity<>(tools, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/{id}")
     @Operation(summary = "Delete a tool", description = "Delete a tool by its ID")
    public ResponseEntity<?> deleteTool(@PathVariable Long id){

        try{
            toolsService.deleteTools(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

}
