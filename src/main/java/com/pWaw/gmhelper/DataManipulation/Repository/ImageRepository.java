package com.pWaw.gmhelper.DataManipulation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pWaw.gmhelper.DataManipulation.Model.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByIdIn(List<Long> ids);
}
