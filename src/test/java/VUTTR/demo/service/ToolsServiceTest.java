package VUTTR.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import VUTTR.demo.entity.Tools;
import VUTTR.demo.repository.ToolsRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ToolsServiceTest {

    @Mock
    private ToolsRepository toolsRepository;

    @InjectMocks
    private ToolsService toolsService;

    private Tools tools;

    @BeforeEach
    void setup() {
        tools = Tools.builder()
                .title("title")
                .link("link")
                .description("description")
                .tags(List.of("tag"))
                .build();
    }

    @Test
    void saveTools() throws Exception {
        // Arrange
        when(toolsRepository.findByTitle("title")).thenReturn(Optional.empty());
        when(toolsRepository.save(any(Tools.class))).thenReturn(tools);

        // Act
        Tools savedTools = toolsService.save(tools);

        // Assert
        assertEquals("title", savedTools.getTitle());
        assertEquals("link", savedTools.getLink());
    }

    @Test
    void saveToolsThrowsExceptionWhenToolsIsNull() {
        // Act and Assert
        assertThrows(Exception.class, () -> toolsService.save(null));
    }

    @Test
    void saveToolsThrowsExceptionWhenToolWithSameTitleOrLinkAlreadyExists() {
        // Arrange
        when(toolsRepository.findByTitle("title")).thenReturn(Optional.of(tools));
        when(toolsRepository.findByLink("link")).thenReturn(Optional.of(tools));

        // Act and Assert
        assertThrows(Exception.class, () -> toolsService.save(tools));
    }
}
