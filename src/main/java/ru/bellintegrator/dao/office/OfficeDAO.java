package ru.bellintegrator.dao.office;

import ru.bellintegrator.dto.OfficeDTO;
import ru.bellintegrator.entity.Office;

import java.util.List;

public interface OfficeDAO {

    public List<Office> getOffices();

    public Office getOffice(Long id);

    public void updateOffice(OfficeDTO officeDTO);

    public void saveOffice(OfficeDTO officeDTO);

}
