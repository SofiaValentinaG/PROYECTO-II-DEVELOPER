package co.edu.uptc.developer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.domain.Developer;
import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.enums.StatusEnum;
import co.edu.uptc.developer.service.DeveloperService;

public class DeveloperServiceTest {

    private DeveloperService developerService;

    @BeforeEach
    void setUp() {
        developerService = new DeveloperService();
    }

    @Test
    void testCreateDeveloperSuccess() {
    	
    	
        Developer dev = new Developer(1233L, "Sofía", "Gomzalez",  "Java", 5, 3000.0, "sofiagonzalez@mail.com",
        new Project(1233L,"Taller",new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra"),
        new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512));
        boolean result = developerService.createDeveloper(dev);
        assertTrue(result, "El developer debería crearse correctamente");
    }

    @Test
    void testCreateDeveloperDuplicate() {
        Developer dev1 =new Developer(1233L, "Sofía", "Gomzalez",  "Java", 5, 3000.0, "sofiagonzalez@mail.com",
                new Project(1233L,"Taller",new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra"),
                new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512));
        Developer dev2 =new Developer(1233L, "Sofía", "Gomzalez",  "Java", 5, 3000.0, "sofiagonzalez@mail.com",
                new Project(1233L,"Taller",new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra"),
                new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512));

        developerService.createDeveloper(dev1);
        boolean result = developerService.createDeveloper(dev2);


        assertFalse(result, "No debería permitir crear un developer duplicado");
    }

    @Test
    void testFindDeveloperById() {
        Developer dev = new Developer(1233L, "Angelica", "Beltran",  "Java", 5, 3000.0, "Angelica@mail.com",
                new Project(1233L,"Taller",new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra 1"),
                new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512));
        developerService.createDeveloper(dev);

        Developer found = developerService.findDeveloperById(1233L, "Angelica");
        assertNotNull(found, "El developer debería encontrarse");
        assertEquals("Angelica", found.getName());
    }

    @Test
    void testUpdateDeveloper() {
        Developer dev = new Developer(3L, "Ana", "Gomzalez", "Java", 5, 3000.0, "anagonzalez@mail.com",
                new Project(1233L, "Taller", new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra"),
                new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512));
        developerService.createDeveloper(dev);

        
        Developer updated = new Developer(3L, "Ana", "Gomzalez", "Java", 5, 3500.0, "anagonzalez@mail.com",
                new Project(1233L, "Taller", new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra"),
                new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512));
        boolean result = developerService.updateDeveloper(updated);

        assertTrue(result, "El developer debería actualizarse correctamente");
        Developer found = developerService.findDeveloperById(3L, "Ana");
        assertEquals(3500.0, found.getSalary(), "El salario debería haberse actualizado");
    }


    @Test
    void testDeleteDeveloper() {
        Developer dev = new Developer(4L, "Laura", "Gomzalez",  "Java", 5, 3000.0, "lauragonzalez@mail.com",
                new Project(1233L,"Taller",new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra"),
                new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512));
        developerService.createDeveloper(dev);

        boolean result = developerService.deleteDveloperByIdAndName(4L, "Laura");
        assertTrue(result, "El developer debería eliminarse correctamente");

        Developer found = developerService.findDeveloperById(4L, "Laura");
        assertNull(found, "El developer ya no debería existir");
    }
}
