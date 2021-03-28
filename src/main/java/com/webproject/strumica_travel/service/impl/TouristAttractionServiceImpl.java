package com.webproject.strumica_travel.service.impl;


import com.webproject.strumica_travel.model.TouristAttraction;
import com.webproject.strumica_travel.model.enumeration.AttractionType;
import com.webproject.strumica_travel.model.exception.TouristAttractionNotFoundException;
import com.webproject.strumica_travel.repository.TouristAttractionRepository;
import com.webproject.strumica_travel.service.TouristAttractionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TouristAttractionServiceImpl implements TouristAttractionService {
    private final TouristAttractionRepository touristAttractionRepository;

    public TouristAttractionServiceImpl(TouristAttractionRepository touristAttractionRepository) {
        this.touristAttractionRepository = touristAttractionRepository;
    }

    @Override
    public List<TouristAttraction> findAll() {
        return touristAttractionRepository.findAll();
    }

    @Override
    public Page<TouristAttraction> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TouristAttraction> list;

        if (this.findAll().size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, this.findAll().size());
            list = this.findAll().subList(startItem, toIndex);
        }

        Page<TouristAttraction> touristAttractionPage
                = new PageImpl<TouristAttraction>(list, PageRequest.of(currentPage, pageSize), this.findAll().size());

        return touristAttractionPage;
    }

    @Override
    public Page<TouristAttraction> findPaginated(Pageable pageable, AttractionType type) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TouristAttraction> list;

            if(this.searchByType(type).size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, this.searchByType(type).size());
                list = this.searchByType(type).subList(startItem, toIndex);
            }


        Page<TouristAttraction> touristAttractionPage
                = new PageImpl<TouristAttraction>(list, PageRequest.of(currentPage, pageSize), this.searchByType(type).size());

        return touristAttractionPage;

    }

    @Override
    public Page<TouristAttraction> findPaginated(Pageable pageable, String nameSearch) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TouristAttraction> list;
        if (this.searchByName(nameSearch).size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, this.searchByName(nameSearch).size());
            list = this.searchByName(nameSearch).subList(startItem, toIndex);
        }

        Page<TouristAttraction> touristAttractionPage
                = new PageImpl<TouristAttraction>(list, PageRequest.of(currentPage, pageSize), this.searchByName(nameSearch).size());

        return touristAttractionPage;
    }

    @Override
    public Optional<TouristAttraction> findById(Long id) {
        return touristAttractionRepository.findById(id);
    }

    @Override
    public Optional<TouristAttraction> findByName(String name) {
        return touristAttractionRepository.findByName(name);
    }

    @Override
    public Optional<TouristAttraction> save(String name, String location,Double latitude,Double longitude, String description, String mainPicture, String pictures, AttractionType type) {
        if(name==null || name.isEmpty() || location==null || location.isEmpty() || description==null || description.isEmpty() || pictures==null || pictures.isEmpty() || type==null)
        {
            throw new IllegalArgumentException();
        }
        TouristAttraction touristAttraction=new TouristAttraction(name,location,latitude,longitude,description,mainPicture,pictures,type);
        return Optional.of(touristAttractionRepository.save(touristAttraction));
    }

    @Override
    public Optional<TouristAttraction> edit(Long id, String name, String location,Double latitude,Double longitude, String description, String mainPicture,String pictures,AttractionType type) {
        TouristAttraction touristAttraction=touristAttractionRepository.findById(id).orElseThrow(()->new TouristAttractionNotFoundException(id));
        touristAttraction.setName(name);
        touristAttraction.setLocation(location);
        touristAttraction.setLatitude(latitude);
        touristAttraction.setLongitude(longitude);
        touristAttraction.setDescription(description);
        touristAttraction.setMainPicture(mainPicture);
        touristAttraction.setPictures(pictures);
        touristAttraction.setType(type);
        return Optional.of(this.touristAttractionRepository.save(touristAttraction));
    }

    @Override
    public List<TouristAttraction> searchByName(String name) {
        if(name!=null && !name.isEmpty())
        {
            return touristAttractionRepository.findAllByNameLike("%"+name+"%");

        }
        else {
            return this.findAll();
        }
    }
    @Override
    public List<TouristAttraction> searchByType(AttractionType type) {
        if(type!=null)
        {
            return touristAttractionRepository.findAllByType(type);
        }
        else{
            return this.findAll();
        }
    }
    @Override
    public void deleteById(Long id) {
        TouristAttraction touristAttraction=touristAttractionRepository.findById(id).orElseThrow(()->new TouristAttractionNotFoundException(id));
        touristAttractionRepository.delete(touristAttraction);
    }
}
