package VUTTR.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VUTTR.demo.dto.ToolsDTO;
import VUTTR.demo.entity.Tools;
import VUTTR.demo.repository.ToolsRepository;
import jakarta.transaction.Transactional;

@Service
public class ToolsService {

    @Autowired
    private ToolsRepository toolsRepository;

    @Transactional
    public Tools save(Tools tools) throws Exception{

        if(tools== null){
            throw new Exception("Tools can't be null");
        }
        return toolsRepository.save(tools);

    }

    private ToolsDTO convertToDTO(Tools tools){
        return new ToolsDTO(tools.getId(), tools.getTitle(), tools.getLink(), tools.getDescription(), tools.getTags());
    }

    public List<ToolsDTO> findAll(){
        List<Tools> tools = toolsRepository.findAll();
        return tools.stream().map(this::convertToDTO).toList();
    }

    public List<ToolsDTO> findByTag(String tag){

        List<Tools> tools = toolsRepository.findByTags(tag);
        return tools.stream().map(this::convertToDTO).toList();

    }

    public Tools updateTools(Tools updatedTools) throws Exception{

        if(updatedTools == null){
            throw new Exception("Tools can't be null");
        }

        Tools tools = toolsRepository.findById(updatedTools.getId()).orElse(null);
        if(tools == null){
            throw new Exception("Tools not found");
        }

        tools.setTitle(updatedTools.getTitle());
        tools.setLink(updatedTools.getLink());
        tools.setDescription(updatedTools.getDescription());
        tools.setTags(updatedTools.getTags());

        return toolsRepository.save(updatedTools);
    }

    public void deleteTools(Long id) throws Exception{
        Tools tools = toolsRepository.findById(id).orElse(null);
        if(tools == null){
            throw new Exception("Tools not found");
        }
        toolsRepository.delete(tools);
    }


}
