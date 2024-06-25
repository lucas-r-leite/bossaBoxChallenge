package VUTTR.demo.dto;

import java.util.List;

public record ToolsDTO(Long id, String title, String link, String description, List<String> tags) {

}
