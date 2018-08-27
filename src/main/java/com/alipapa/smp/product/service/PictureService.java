package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.PictureMapper;
import com.alipapa.smp.product.mapper.ProductCategoryMapper;
import com.alipapa.smp.product.pojo.Picture;
import com.alipapa.smp.product.pojo.PictureExample;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.pojo.ProductPictureExample;
import com.alipapa.smp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    /**
     * @param picture
     * @return
     */
    public boolean delete(Picture picture) {
        pictureMapper.deleteByPrimaryKey(picture.getId());
        return true;
    }


    /**
     * @param picNo
     * @return
     */
    public Picture getPictureByPicNo(String picNo) {
        if (StringUtil.isEmptyString(picNo)) {
            return null;
        }

        logger.info("getPictureByPicNo,picNo=" + picNo);
        PictureExample example = new PictureExample();
        PictureExample.Criteria criteria = example.createCriteria();
        criteria.andPicNoEqualTo(picNo);
        List<Picture> pictureList = pictureMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(pictureList)) {
            return pictureList.get(0);
        }
        return null;
    }


}
