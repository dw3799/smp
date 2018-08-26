package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.PictureMapper;
import com.alipapa.smp.product.mapper.ProductCategoryMapper;
import com.alipapa.smp.product.pojo.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    private static Logger logger = LoggerFactory.getLogger(PictureService.class);


    @Autowired
    private PictureMapper pictureMapper;


    /**
     * @param picture
     * @return
     */
    public boolean save(Picture picture) {
        pictureMapper.insert(picture);
        return true;
    }
}
