package ru.bellintegrator.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.dao.office.OfficeDAO;
import ru.bellintegrator.dto.OfficeDTO;
import ru.bellintegrator.dto.mapper.Mapper;
import ru.bellintegrator.dto.mapper.OfficeMapper;
import ru.bellintegrator.entity.Office;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDAO officeDAO;

    private final OfficeMapper officeMapper;

    @Autowired
    public OfficeServiceImpl(OfficeDAO officeDAO, OfficeMapper officeMapper) {
        this.officeDAO = officeDAO;
        this.officeMapper = officeMapper;
    }

    @Override
    @Transactional
    public List<OfficeDTO> getOffices() {
        List<Office> offices = officeDAO.getOffices();
        return officeMapper.toDTOList(offices);
    }

    @Override
    @Transactional
    public OfficeDTO getOffice(Long id) {
        Office office = officeDAO.getOffice(id);
        return officeMapper.toDTO(office);
    }

    @Override
    @Transactional
    public void updateOffice(OfficeDTO officeDTO) {
        // ModelMapper использует прокси и таким образом перехватывает exception и нужное сообщение и возвращает свой
        // exception. Поэтому вручную вызываем метод проверки
        Mapper.throwExceptionIfNotTrueOrFalse(officeDTO.getIsActive());
        Office office = officeMapper.toEntity(officeDTO);
        officeDAO.updateOffice(office, officeDTO.getOrgId());
    }

    @Override
    @Transactional
    public void saveOffice(OfficeDTO officeDTO) {
        // ModelMapper использует прокси и таким образом перехватывает exception и нужное сообщение и возвращает свой
        // exception. Поэтому вручную вызываем метод проверки
        Mapper.throwExceptionIfNotTrueOrFalse(officeDTO.getIsActive());
        officeDTO.setId(null);
        Office office = officeMapper.toEntity(officeDTO);
        officeDAO.saveOffice(office, officeDTO.getOrgId());
    }
}
